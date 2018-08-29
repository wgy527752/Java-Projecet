package Functions;

import java.util.Stack;

import HelpClass.DoubleLinkedListNode;
import HelpClass.TreeNode;

public class FlatternBST {
	public static DoubleLinkedListNode flatternBSTInorder(TreeNode root) {
		DoubleLinkedListNode head = new DoubleLinkedListNode(-1);
		DoubleLinkedListNode tail = new DoubleLinkedListNode(-1);
		head.next = tail;
		tail.prev = head;
		DoubleLinkedListNode move = head;
		
		
		Stack<TreeNode> stack = new Stack<>();
		TreeNode curr = root;
		while(curr != null || !stack.empty()) {
			while(curr != null) {
				stack.push(curr);
				curr = curr.left;
			}
			curr = stack.pop();
			move.next = new DoubleLinkedListNode(curr.val);
			move.next.prev = move;
			move.next.next = tail;
			tail.prev = move.next;
			move = move.next;
			curr = curr.right;
		}
		return head.next;
	}
	
	public static DoubleLinkedListNode flatternBSTPreorder(TreeNode root) {
		DoubleLinkedListNode head = new DoubleLinkedListNode(-1);
		DoubleLinkedListNode move = head;
		
		Stack<TreeNode> stack = new Stack<>();
		TreeNode curr = root;
		while(curr != null) {
			move.next = new DoubleLinkedListNode(curr.val);
			move.next.prev = head;
			move = move.next;
			if(curr.right != null) {
				stack.push(curr.right);
			}
			curr = curr.left;
			if(curr == null && !stack.empty()) {
				curr = stack.pop();
			}
		}
		return head.next;
	}
	
}
