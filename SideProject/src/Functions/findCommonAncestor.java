package Functions;

import HelpClass.TreeNode;

public class findCommonAncestor {
	public static TreeNode findCommonAns(TreeNode root, TreeNode[] nodes){
		TreeNode start = nodes[0];
		for(int i = 1; i < nodes.length; i++) {
			TreeNode anc = findTwoAns(root, start, nodes[i]);
			start = anc;
		}
		return start;
	}

	public static TreeNode findTwoAns(TreeNode root, TreeNode p, TreeNode q){
		if(root == null) return root;
		if(root == p || root == q) {
			return root;
		}
		TreeNode left = findTwoAns(root.left, p, q);
		TreeNode right = findTwoAns(root.right, p, q);
		if(left != null && right != null) return root;
		return left != null ? left : right;
	}
}
