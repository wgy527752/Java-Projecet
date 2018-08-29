package LRUCache;

import java.util.Hashtable;

public class LRU<K,V> {
	class LRUNode{
		LRUNode prev;
		LRUNode next;
		K key;
		V value;
		LRUNode(K key, V value){
			this.key = key;
			this.value = value;
			prev = null;
			next = null;
		}
	}
	
	private int size;
	private int currSize;
	private LRUNode first;
	private LRUNode last;
	private Hashtable<K, LRUNode> table;
	
	LRU(int size){
		this.size = size;
		currSize = 0;
		first.next = last;
		last.prev = first;
		table = new Hashtable<>();
	}
	
	public V get(K key) {
		LRUNode curr = table.get(key);
		if(curr != null) {
			removeNode(curr);
			addToHead(curr);
		}else {
			return null;
		}
		return curr.value;
	}
	
	public void clear() {
		first = null;
		last = null;
		table.clear();
	}
	
	public void put(K key, V value) {
		if(table.get(key) != null){
            LRUNode node = table.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
        }else{
        	LRUNode node = new LRUNode(key, value);
            table.put(key, node);
            if(currSize < size){
                currSize++;
                addToHead(node);
            }else{
                table.remove(last.prev.key);
                removeNode(last.prev);
                addToHead(node);
            }
        }
	}
	
	public void remove(K key) {
		LRUNode curr = table.get(key);
		if(curr != null) {
			removeNode(curr);
			currSize--;
		}
	}
	
	private void addToHead(LRUNode node) {
		if(node == first) {
			return;
		}
		node.next = first.next;
		node.next.prev = node;
		node.prev = first;
		first.next = node;
	}
	
	private void removeNode(LRUNode node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		
	}
}
