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
        
        // exit program if EOF (end of file) is reached
        if (input == null) {
            continueExecution = false;
            return; 
        }
        
        // showing the prompt again if no input was given
        if (input.isBlank()) {
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
            
        case "trie":
            System.out.println(trie);
            break;
            
        case "add":
            addToTrie(tokenizedCommand);
            break;
            
        default:
            printError("Unknown command \"" + cmd + "\"");
            break;
        }
    }

    private static void addToTrie(String[] tokenizedCommand) {
        if (tokenizedCommand.length < 3) {
            printError("Missing parameter(s). Key and Value required.");
            return;
        }
        
        String key = tokenizedCommand[1];
        String num = tokenizedCommand[2];
        if (isValidKey(key) && isValidNumber(num)) {
            Integer parsedNumber = Integer.valueOf(num);
            // show error message if adding fails.
            if (!trie.add(key, parsedNumber)) {
                printError("The key \"" + key + "\" already has a value assigned.");
            }
        }
    }

    private static boolean isValidKey(String key) {
        // check each character individually
        for (char ch : key.toCharArray()) {
            // check value because `Character.isLowerCase(ch)` returns true for
            // characters outside the supported range (e.g.: �, �, ...).
            if (ch < 'a' || ch > 'z') {
                printError("The key must contain lowercase letters only.");
                return false;
            }
        }
        return true;
    }
    
    private static boolean isValidNumber(String val) {
        try {
            Integer.valueOf(val);
        } catch (NumberFormatException e){
            printError("The value has to be an integer.");
            return false;
        }
        return true;
    }

    private static void printError(String msg) {
        System.out.println("Error! " + msg);
        System.out.println("Enter 'help' to display the syntax.");
    }

    private static void printHelp() {
        System.out.println(
              "Trie enables you to store integers in a tree data structure "
            + "using strings as keys.\n\n"
            + "Available commands:\n"
            + "add <key> <value>     Inserts the value for a new key into "
            + "the trie. Fails if the key already has a value assigned.\n"
            + "change <key> <value>  Changes the value for the given key. "
            + "Fails if no value is associated with the key.\n"
            + "delete <key>          Removes a data element. Fails if no "
            + "value is associated with the key.\n"
            + "points <key>          Prints the value of the specified key. "
            + "Fails if no value is associated with the key.\n"
            + "trie   Prints the structure of the current trie.\n"
            + "help   Shows this help text.\n"
            + "new    Creates a new trie and discards the old data structure.\n"
            + "quit   Exits the program.\n\n"
            + "Note that the key must contain lowercase letters only."
        );
    }


}
