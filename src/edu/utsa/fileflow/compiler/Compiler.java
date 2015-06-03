package edu.utsa.fileflow.compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.utsa.fileflow.filestructure.Directory;

public class Compiler {
	private Scanner scanner;

	/**
	 * initializes the parser object
	 * @param script - file name of the script to open for reading
	 */
	public Compiler(String script) {
		try {
			this.scanner = new Scanner(new File(script));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Parses the test script and will return a Directory Structure Object
	 */
	public void compile() {
		int line = 0;
		
		Directory root = new Directory("root", 0);
		// while we have more commands to read
		while (scanner.hasNext()) {
			// parse line to command object
			Command cmd = new Command(scanner.nextLine());
			
			// switch (cmd.getType) {
			// case cp:
			handleCP(root, cmd);
			
			// case new:
			// case mv:
			// case rm:
			
			
			System.out.printf("cmd %d: %d\n", line, cmd.getSize());
			line++;
		}
	}
	
	private void handleCP(Directory dir, Command cmd) {
		// TODO: assert commands are legal
		String arg1 = cmd.getArg(1);
		Directory src = new Directory(arg1, 0);
		String[] tokens = arg1.split("/");
		
	}

}