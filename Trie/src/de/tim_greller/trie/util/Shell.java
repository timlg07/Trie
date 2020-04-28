package de.tim_greller.trie.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.tim_greller.trie.model.Trie;

public final class Shell {
	
	private static final String PROMPT = "trie> ";
	private static boolean continueExecution = true;
	private static Trie trie = new Trie();
	
	private Shell() {}
	
	public static void main(String[] args) throws IOException {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

		while (continueExecution) {
			readInput(stdin);
		}
	}

	private static void readInput(BufferedReader stdin) throws IOException {
		System.out.print(PROMPT);
		String input = stdin.readLine();
		
		// no more input
		if (input == null) {
			continueExecution = false;
			return; 
		}
		
		String[] tokenizedCommand = input.trim().split("\\s+");
		executeCommand(tokenizedCommand);
	}

	private static void executeCommand(String[] tokenizedCommand) {
		// The command-array is never empty, because spaces get removed by trim
		// and splitting an empty strings returns an array containing an empty string.
		String cmd = tokenizedCommand[0];
		
		switch (cmd) {
		case "new": 
			trie = new Trie(); 
			break;
		
		case "help":
			printHelp();
			break;
			
		case "quit":
			continueExecution = false;
			break;
			
		default:
			printError("unknown command \"" + cmd + "\"");
		}
	}

	private static void printError(String msg) {
		System.out.println("Error! " + msg);
	}

	private static void printHelp() {
	}


}
