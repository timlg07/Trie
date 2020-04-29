package de.tim_greller.trie.model;

public class Trie {
	
	Node root;
	
	public Trie() {
		root = new Node();
	}

	public boolean add(String key, Integer points) {
		Node iterator = root;
		for (char ch : key.toCharArray()) {
			Node child = iterator.getChild(ch);
			if (child == null) {
				child = new Node(ch, iterator);
				iterator.setChild(ch, child);
			}
			iterator = child;
		}
		if (iterator.getPoints() != null) {
			return false;
		}
		iterator.setPoints(points);
		return true;
	}
	
	public boolean remove(String key) {
		return true;
	}
	
	public boolean change(String key, Integer points) {
		return true;
	}
	
	public Integer points(String key) {
		return root.getPoints();
	}
	
	@Override
	public String toString() {
		return root.toString();
	}

}
