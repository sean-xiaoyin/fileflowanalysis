package edu.utsa.fileflow.compiler;

import java.util.ArrayList;
import java.util.Arrays;

public class Command {

	private CommandType type;
	private String command;
	private ArrayList<String> args;

	public Command(String command) {
		this.command = command;
		tokenize();
	}

	public String getArg(int x) {
		return args.get(x);
	}

	public String getCommand() {
		return command;
	}
	
	public int getSize() {
		return args.size();
	}
	
	public CommandType getType() {
		return type;
	}
	
	private void tokenize() {
		String[] argList = command.split("\\s+");
		args = new ArrayList<String>(Arrays.asList(argList));
		type = CommandType.valueOf(argList[0]);
	}
	
}
