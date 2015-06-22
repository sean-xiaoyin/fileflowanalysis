package edu.utsa.fileflow.compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.utsa.fileflow.filestructure.FileStruct;

public class Compiler {
	/**
	 * TODO: need a way to assert that files do not exist as required by the precondition.
	 * 
	 * 1. A possible way to do this would be to create a File Structure that will keep track of files that should not
	 * exist.
	 * 
	 * 2. Another way would be to implement a way to do this directly into the precondition file structure (requires
	 * modification to the FileStruct class).
	 * 
	 * 3. Create a Condition class that has multiple file structure objects that will serve different purposes. i.e. one
	 * file structure dictating what should exist and the other dictating what should not exist.
	 */
	
	private FileStruct pre;
	private FileStruct post;


	/**
	 * Parses the test script and will return a Directory Structure Object
	 * 
	 * @param file
	 * @return Precondition file structure
	 * @throws CompilerException
	 */
	public FileStruct compile(File file) throws CompilerException {
		// open the file for reading
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException fnfe) {
			throw new CompilerException(fnfe.getMessage());
		}

		// instantiate the precondition file structure
		pre = new FileStruct("root");

		// instantiate the postcondition file structure
		// current structure while we execute commands
		post = new FileStruct("root");

		// while we have more commands to read
		while (scanner.hasNext()) {
			// parse line to command object
			String line = scanner.nextLine();

			// skip line if it is a comment
			line = line.trim();
			if (line.startsWith("#"))
				continue;
			line = line.split("#")[0];
			if (line == null || line.length() == 0)
				continue;

			Command cmd = null;
			try {
				cmd = new Command(line);
			} catch (InvalidCommandException ice) {
				scanner.close();
				throw new CompilerException(ice.getMessage());
			}

			switch (cmd.getType()) {
			case COPY:
				handleCopy(pre, post, cmd);
				break;
			case DELETE:
				handleDelete(pre, post, cmd);
				break;
			case MOVE:
				handleMove(pre, post, cmd);
				break;
			case NEW:
				handleNew(pre, post, cmd);
				break;
			default:
				scanner.close();
				throw new CompilerException("Unknown command '" + cmd.getArg(0) + "'");
			}
		}

		scanner.close();
		return pre;
	}

	private void handleCopy(FileStruct pre, FileStruct post, Command cmd) throws CompilerException {
		// TODO: assert commands are legal
		// first argument must exist, so we add it to precondition file structure
		String arg1 = cmd.getArg(1);
		// check if exists in post
		if(!post.pathExists(arg1)){
			pre.insert(arg1);
			post.insert(arg1);
		}
		// the second argument should not exist, so if it does throw an exception
		String arg2 = cmd.getArg(2);
		String[] filepath = arg2.split("/");
		
		if (!pre.pathExists(filepath)) {
			throw new CompilerException(String.format(" '%s' No such file or directory.", arg2));
		}
		
		if (pre.pathExists(arg2)) {
			throw new CompilerException(String.format("'%s' File or directory already exists.", cmd.getCommand()));
		}
		post.insert(arg2);
		
	}

	private void handleMove(FileStruct pre, FileStruct post, Command cmd) throws CompilerException{
		// TODO: assert commands are legal
		// first argument must exist, so we add it to precondition file
		// structure
		String arg1 = cmd.getArg(1);
		if(!post.pathExists(arg1)){
			pre.insert(arg1);
			post.insert(arg1);
		}		
		// the second argument should not exist, so if it does throw an
		// exception
		String arg2 = cmd.getArg(2);
		String[] filepath = arg2.split("/");
		if (!pre.pathExists(filepath)) {
			throw new CompilerException(String.format(" '%s' No such file or directory.", arg2));
		}
		if (pre.pathExists(arg2)) {
			throw new CompilerException(String.format("'%s' File or directory already exists.", cmd.getCommand()));
		}
		// TODO:  cp home/bin/x.txt dir1/x.txt
		// need to add dir1 to precondition

		post.insert(arg2);
		// delete arg1 from file structure
		post.remove(arg1);
		
	}

	private void handleDelete(FileStruct pre, FileStruct post, Command cmd) throws CompilerException {
		// assert command is legal
		if (cmd.getSize() != 2) {
			throw new CompilerException(String.format("'%s'\n\t> Command '%s' expects only 1 argument", cmd.getCommand(), cmd.getType().getName()));
		}

		// first argument must exist so add it to precondition file structure if it isn't in post
		String arg1 = cmd.getArg(1);

		// TODO: check if arg1 exists in post
		// if it exists in post then it doesn't need to be in precondition
		// because it was already created by another command

		pre.insert(arg1);
	}

	private void handleNew(FileStruct pre, FileStruct post, Command cmd) throws CompilerException {
		// assert command is legal
		if (cmd.getSize() != 2) {
			throw new CompilerException(String.format("'%s'\n\t> Command '%s' expects only 1 argument", cmd.getCommand(), cmd.getType().getName()));
		}

		// arg1 should not exist in either pre or post
		// String arg1 = cmd.getArg(1);
		// TODO: assert arg1 does not exist
	}


	public FileStruct getPost() {
		return post;
	}

	public void setPost(FileStruct post) {
		this.post = post;
	}
}
