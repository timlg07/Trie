package de.tim_greller.trie.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.tim_greller.trie.model.Trie;

/**
 * The trie Shell enables direct user communication to perform operations 
 * on a trie. 
 */
public final class Shell {
    
    /** 
     * Holds the current information about whether the program should continue
     * execution or terminate after the current line of input is processed.
     */
    private static boolean continueExecution = true;
    
    /** The trie on which every operation gets performed. */
    private static Trie trie = new Trie();
    
    /** Private constructor to prevent instantiation. */
    private Shell() {}
    
    /**
     * The main method processes the input received on System.in (standard 
     * input).
     * @param args The arguments are ignored. All input must be sent per stdin.
     * @throws IOException If an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(
                new InputStreamReader(System.in));

        while (continueExecution) {
            processLine(stdin);
        }

        stdin.close();
    }

    /**
     * Reads the next input line and initiates the execution of it.
     * Sets the {@code continueExecution} to false if EOF is reached.
     * Performs no operation for blank lines.
     * 
     * @param stdin The BufferedReader for the standard input stream.
     * @throws IOException If an I/O error occurs.
     */
    private static void processLine(BufferedReader stdin) throws IOException {
        System.out.print("trie> ");
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

        String[] tokenizedInput = input.trim().split("\\s+");
        executeCommand(tokenizedInput);
    }

    /**
     * If the first token of the input is a valid command, it gets executed.
     * 
     * @param tokenizedInput The input containing command and parameters.
     */
    private static void executeCommand(String[] tokenizedInput) {
        // The array is never empty, because spaces get removed by trim and
        // splitting an empty string returns an array containing an empty
        // string.
        String cmd = tokenizedInput[0].toLowerCase();

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
            if (hasEnoughParameters(tokenizedInput, 2)) {
                addToTrie(tokenizedInput);
            }
            break;

        case "change":
            if (hasEnoughParameters(tokenizedInput, 2)) {
                changeValueInTrie(tokenizedInput);
            }
            break;

        case "delete":
            if (hasEnoughParameters(tokenizedInput, 1)) {
                removeFromTrie(tokenizedInput);
            }
            break;

        case "points":
            if (hasEnoughParameters(tokenizedInput, 1)) {
                printValueFromTrie(tokenizedInput);
            }
            break;

        default:
            printError("Unknown command \"" + cmd + "\"");
            break;
        }
    }
    
    /**
     * Checks if the input contains enough parameters. Prints an error message
     * if given less parameters than required.
     * 
     * @param tokenizedInput The input split in its tokens.
     * @param requiredParameters The amount of required parameters.
     * @return Whether the input contains enough parameters.
     */
    private static boolean hasEnoughParameters(String[] tokenizedInput, 
            int requiredParameters) {
        int givenParameters = tokenizedInput.length - 1 /* command token */ ;
        if (givenParameters < requiredParameters) {
            printError("Missing parameters. " + givenParameters + 
                       " recieved, but " + requiredParameters + " required.");
            return false;
        }
        return true;
    }

    /**
     * If the second token contains a valid key and the third a valid Integer,
     * the value gets added to the trie. 
     * 
     * @param tokenizedInput The input containing command and parameters.
     */
    private static void addToTrie(String[] tokenizedInput) {
        String key = tokenizedInput[1];
        String num = tokenizedInput[2];
        if (isValidKey(key) && isValidNumber(num)) {
            Integer parsedNumber = Integer.valueOf(num);
            boolean success = trie.add(key, parsedNumber);
            if (!success) {
                printError("The key \"" + key + 
                           "\" already has a value assigned.");
            }
        }
    }

    /**
     * If the second token contains a valid key and the third a valid Integer,
     * the value for the key in the trie gets updated.
     * 
     * @param tokenizedInput The input containing command and parameters.
     */
    private static void changeValueInTrie(String[] tokenizedInput) {
        String key = tokenizedInput[1];
        String num = tokenizedInput[2];
        if (isValidKey(key) && isValidNumber(num)) {
            Integer parsedNumber = Integer.valueOf(num);
            boolean success = trie.change(key, parsedNumber);
            if (!success) {
                printError("The key \"" + key + "\" does not hold a value.");
            }
        }
    }
    
    /**
     * If the second token contains a valid key the value at this position gets
     * removed from the trie.
     * 
     * @param tokenizedInput The input containing command and parameter.
     */
    private static void removeFromTrie(String[] tokenizedInput) {
        String key = tokenizedInput[1];
        if (isValidKey(key)) {
            boolean success = trie.remove(key);
            if (!success) {
                printError("The key \"" + key + "\" does not hold a value.");
            }
        }
    }

    /**
     * If the second token contains a valid key the value at this position in 
     * the trie gets printed out.
     * 
     * @param tokenizedInput The input containing command and parameter.
     */
    private static void printValueFromTrie(String[] tokenizedInput) {
        String key = tokenizedInput[1];
        if (isValidKey(key)) {
            Integer points = trie.points(key);
            if (points == null) {
                printError("The key \"" + key + "\" does not hold a value.");
            }
            System.out.println(points);
        }
    }

    /**
     * Checks if the given String consists of characters accepted by the trie as
     * valid key.
     * 
     * @param key The key which characters should get cheked.
     * @return Whether the key is valid or not.
     */
    private static boolean isValidKey(String key) {
        for (char c : key.toCharArray()) {
            // Check value because `Character.isLowerCase(ch)` returns true for
            // characters outside the supported range (e.g.: ö, ü, ...).
            if (c < 'a' || c > 'z') {
                printError("The key must contain lowercase letters only.");
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given String is a valid Integer representation.
     * 
     * @param val The String representation of the value.
     * @return Whether the value can be parsed to an Integer or not.
     */
    private static boolean isValidNumber(String val) {
        try {
            Integer.valueOf(val);
        } catch (NumberFormatException e) {
            printError("The value has to be an integer.");
            return false;
        }
        return true;
    }

    /**
     * Prints an error text accepted by the <i>Praktomat</i> containing the 
     * given message.
     * 
     * @param msg The message which should describe the error.
     */
    private static void printError(String msg) {
        System.out.println("Error! " + msg);
        System.out.println("Enter 'help' to display the syntax.");
    }

    /**
     * Prints a help text about the usage of the trie Shell including all
     * supported commands with their syntax and a description.
     */
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
