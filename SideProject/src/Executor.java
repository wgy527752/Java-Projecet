import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Functions.CountSmallerNumberAfterSelf;
import Functions.MorrisTraversal;
import Functions.RandomizedCollection;
import Functions.SlidingWindow;
import Functions.SplitSQL;
import Functions.Sykline;
import Functions.WordSearch;
import Functions.findCommonAncestor;
import Functions.removeDuplicateFromString;
import HelpClass.TreeNode;

public class Executor {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		//TreeNode root = new TreeNode(-1);
		//root = root.generateTree(arr, root, 0);
//		TreeNode[] nodes = {root.left.left.left, root.left.left.right, root.left.right.left, root.left.right.right};
//		//System.out.print(findCommonAncestor.findCommonAns(root, nodes).val);
//		
//		int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
//		List<int[]> res = Sykline.getSkyline(buildings);
//		System.out.println(res.size());
//		for(int[] i : res) {
//			System.out.println(i[0] + " " + i[1]);
//		}
//		MorrisTraversal tree = new MorrisTraversal();
//        tree.root = tree.insert(tree.root, 24);
//        tree.root = tree.insert(tree.root, 27);
//        tree.root = tree.insert(tree.root, 29);
//        tree.root = tree.insert(tree.root, 34);
//        tree.root = tree.insert(tree.root, 14);
//        tree.root = tree.insert(tree.root, 4);
//        tree.root = tree.insert(tree.root, 10);
//        tree.root = tree.insert(tree.root, 22);
//        tree.root = tree.insert(tree.root, 13);
//        tree.root = tree.insert(tree.root, 3);
//        tree.root = tree.insert(tree.root, 2);
//        tree.root = tree.insert(tree.root, 6);
//  
//        System.out.println("Inorder traversal is ");
//        tree.inorder(tree.root);
//		
//		String[] words = {"oath","pea","eat","rain"};
//		
//		char[][] board = {{'o','a','a','n'},
//				{'e','t','a','e'},
//				{'i','h','k','r'},
//				{'i','f','l','v'}};
//		
//		ArrayList<String> res = WordSearch.findWords(board, words);
//		System.out.println(Arrays.toString(res.toArray()));
		
//		String sql = "SELECT * FROM table1; SELECT * FROM table2 \"\\;\"; SELECT * FROM table3 WHERE column1 = \";\\;;\"; SELECT * FROM ta\";b\" WHERE column1 = \\;";
//		
//		System.out.println(sql);
//
//		ArrayList<String> sqls= SplitSQL.parseSQL(sql);
//		for(String str : sqls) {
//			System.out.println(str);
//		}
//		System.out.println(Arrays.toString(sqls.toArray()));
		
//		int[] nums = {1,3,-1,-3,5,3,6,7};
//		int k = 3;
//		System.out.println(Arrays.toString(SlidingWindow.slidingWindowMax(nums, k).toArray()));
//		System.out.println(Arrays.toString(SlidingWindow.slidingWindowMin(nums, k).toArray()));
//		System.out.println(Arrays.toString(SlidingWindow.slidingWindowMedian(nums, k).toArray()));
//
//		RandomizedCollection rc = new RandomizedCollection();
//		rc.insert(1);
//		rc.insert(1);
//		System.out.println(Arrays.toString(rc.getMap().toArray()));
//		System.out.println(rc.getRandom());
//		rc.delete(1);
//		rc.insert(2);
//		rc.insert(3);
//		rc.delete(1);
//		rc.insert(4);
//		rc.insert(5);
//		System.out.println(Arrays.toString(rc.getMap().toArray()));
//		System.out.println(rc.getRandom());
//		rc.update(3, 5);
//		rc.update(4, 6);
//		System.out.println(rc.getRandom());
//		System.out.println(Arrays.toString(rc.getMap().toArray()));
		
//		String test1 = "cbacdcbc";
//		String testRes1 = removeDuplicateFromString.removeDuplicateLetters(test1);
//		System.out.println(testRes1);
		
		int[] test1 = {5,2,6,1};
		System.out.println(Arrays.toString(CountSmallerNumberAfterSelf.countSmaller(test1)));

		
	}
}
