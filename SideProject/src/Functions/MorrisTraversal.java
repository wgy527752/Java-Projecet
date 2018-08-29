package Functions;

import HelpClass.TreeNode;

public class MorrisTraversal {
	public TreeNode root;
	
    /* A utility function to insert a new node with
       given key in BST */
	public TreeNode insert(TreeNode node, int key){
        /* If the tree is empty, return a new node */
        if (node == null) 
            return new TreeNode(key);
  
        /* Otherwise, recur down the tree */
        if (key < node.val) { 
            node.left = insert(node.left, key);
            node.left.parent = node;
        }else if (key > node.val){
            node.right = insert(node.right, key);
            node.right.parent = node;
        }
          
        /* return the (unchanged) node pointer */
        return node;
    }
  
    // Function to print inorder traversal using parent pointer
    public static void inorder(TreeNode root){
        boolean leftdone = false;
        // Start traversal from root
        while (root != null){
            // If left child is not traversed, find the
            // leftmost child
            if (!leftdone){
                while (root.left != null){
                    root = root.left;
                }
            }
            // Print root's data
            System.out.print(root.val + " ");
            // Mark left as done
            leftdone = true;
            // If right child exists
            if (root.right != null){
                leftdone = false;
                root = root.right;
            }
            // If right child doesn't exist, move to parent
            else{
                // If this node is right child of its parent,
                // visit parent's parent first
                while (root.parent != null && root == root.parent.right) 
                    root = root.parent;
                //one level more up
                root = root.parent;
            } 
        }
    }
  
}
