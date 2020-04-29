package de.tim_greller.trie.model;

public class Trie<T> {
	
	Node root;
	
	public Trie() {
		root = new Node('+', null);
	}

}
