package de.tim_greller.trie.model;

public class Node<T> {

	char character;
	Node<T> parent;
	Node<T>[] children;
	T value;
	
	public Node(char character, Node<T> parent) {
		this.character = character;
		this.parent = parent;
		this.children = new Node[25];
	}

}
