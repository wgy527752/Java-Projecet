package HelpClass;

public class TreeNode {
	public TreeNode left;
	public TreeNode right;
	public TreeNode parent;
	public final int val;
	
	public TreeNode(int val) {
		this.val = val;
	}
	
	public TreeNode generateTree(int[] arr, TreeNode root, int i){
		// Base case for recursion
		if (i < arr.length) {
			TreeNode temp = new TreeNode(arr[i]);
			root = temp;
			// insert left child
			root.left = generateTree(arr, root.left, 2 * i + 1);
			// insert right child
			root.right = generateTree(arr, root.right, 2 * i + 2);
		}
		return root;
	}
	
}


