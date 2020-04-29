package de.tim_greller.trie.model;

public class Trie {
	
	Node root;
	
	public Trie() {
		root = new Node('+', null);
	}

	public boolean add(String key, Integer value) {
		return true;
	}
	
	public boolean remove(String key) {
		return true;
	}
	
	public boolean change(String key, Integer newValue) {
		return true;
	}
	
	public Integer points(String key) {
		return root.value;
	}
	
	@Override
	public String toString() {
		return root.toString();
	}

}
