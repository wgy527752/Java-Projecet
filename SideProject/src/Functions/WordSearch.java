package Functions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import HelpClass.Trie;

public class WordSearch {
	public static List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
		for(String str : words) {
			trie.insert(str);
		}
		int row = board.length;
		int col = board[0].length;
		HashSet<String> set = new HashSet<>();
		boolean[][] visited = new boolean[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				findWordsHelp(board, visited, new String(), i, j, trie, set);
			}
		}
		return new ArrayList<String>(set);
	}
	
	private static void findWordsHelp(char[][] board, boolean[][] visited, String word, int x, int y, Trie trie, HashSet<String> set) {
		if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][ y]) return;
		word += board[x] [y];
		if(!trie.startWith(word)) return;
		if(trie.search(word)) set.add(word);
		visited[x][y] = true;
		findWordsHelp(board, visited, word, x + 1, y, trie, set);
		findWordsHelp(board, visited, word, x - 1, y, trie, set);
		findWordsHelp(board, visited, word, x, y + 1, trie, set);
		findWordsHelp(board, visited, word, x, y - 1, trie, set);
		visited[x][y] = false;
	}
	
	public static boolean findWord(char[][] board, String words) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j] == words.charAt(0) && findWordHelp(board, words, i, j, 0)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static boolean findWordHelp(char[][] board, String word, int x, int y, int pos) {
		if(pos == word.length()) return true;
		if(x < 0 || y < 0 || x >= board.length || y >= board[0].length || word.charAt(pos) != board[x][y] || board[x][y] == '#') {
			return false;
		}
		char temp = board[x][y];
		board[x][y] = '#';
		boolean res = findWordHelp(board, word, x + 1, y, pos + 1) ||
				findWordHelp(board, word, x - 1, y, pos + 1) ||
				findWordHelp(board, word, x, y + 1, pos + 1) ||
				findWordHelp(board, word, x, y - 1, pos + 1);
		board[x][y] = temp;
		return res;
	}
 
}
