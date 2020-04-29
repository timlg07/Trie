package de.tim_greller.trie.model;

public class Trie<T> {
	
	Node<T> root;
	
	public Trie() {
		root = new Node<T>('+', null);
	}

	public boolean add(String key, T val) {
		return true;
	}
	
	public boolean remove(String key) {
		return true;
	}
	
	public boolean change(String key, T newValue) {
		return true;
	}
	
	public T getValue(String key) {
		return root.value;
	}
	
	@Override
	public String toString() {
		return root.toString();
	}

}
