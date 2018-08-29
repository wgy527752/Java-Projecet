package HelpClass;

public class Trie {
	public TrieNode root;
	public Trie() {
		root = new TrieNode(' ');
	}
	
	public void insert(String word) {
		TrieNode node = root;
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(node.child[c - 'a'] == null) {
				node.child[c - 'a'] = new TrieNode(c);
			}
			node = node.child[c - 'a'];
		}
		node.isWord = true;
		node.word = word;
	}
	
	public boolean search(String word) {
		TrieNode node = root;
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(node.child[c - 'a'] == null) return false;
			node = node.child[c - 'a'];
		}
		if(node.isWord && node.word.equals(word))
			return true;
		return false;
	}
	
	public boolean startWith(String prefix) {
		TrieNode ws = root; 
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(ws.child[c - 'a'] == null) return false;
            ws = ws.child[c - 'a'];
        }
        return true;
	}
}

