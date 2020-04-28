package de.tim_greller.trie.model;

public class Node {

	char character;
	Node parent;
	Node[] children;
	
	public Node(char character, Node parent) {
		this.character = character;
		this.parent = parent;
		this.children = new Node[25];
	}

}
