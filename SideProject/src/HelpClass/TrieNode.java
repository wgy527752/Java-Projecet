package HelpClass;

public class TrieNode{
	char val;
	public String word;
	boolean isWord;
	public TrieNode[] child = new TrieNode[26];
    public TrieNode(){}
	public TrieNode(char ch) {
		this.val = ch;
	}
}