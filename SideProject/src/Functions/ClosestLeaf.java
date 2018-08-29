package Functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import HelpClass.TreeNode;

public class ClosestLeaf {
    public int findClosestLeaf(TreeNode root, int k) {
    	if(root.left == null && root.right == null) return root.val;
    	HashMap<TreeNode, List<TreeNode>> graph = new HashMap<TreeNode, List<TreeNode>>();
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	TreeNode startNode = null;
    	while(!queue.isEmpty()) {
    		TreeNode node = queue.poll();
    		if(node.val == k) startNode = node;
    		if(!graph.containsKey(node)) {
    			graph.put(node, new ArrayList<TreeNode>());
    		}
    		if(node.left != null) {
    			graph.get(node).add(node.left);
    			graph.putIfAbsent(node.left, new ArrayList<>());
    			graph.get(node.left).add(node);
    			queue.offer(node.left);
    		}
    		if(node.right != null) {
    			graph.get(node).add(node.right);
    			graph.putIfAbsent(node.right, new ArrayList<>());
    			graph.get(node.right).add(node);
    			queue.offer(node.right);
    		}
    	}
    	queue.clear();
    	HashSet<TreeNode> visited = new HashSet<>();
    	queue.offer(startNode);
    	visited.add(startNode);
    	while(!queue.isEmpty()) {
    		TreeNode curr = queue.poll();
    		if(curr.left == null && curr.right == null && curr != root) {
    			return curr.val;
    		}
    		List<TreeNode> list = graph.get(curr);
    		for(TreeNode nei : list) {
    			if(visited.contains(nei)) continue;
    			if(nei.left == null && nei.right == null && nei != root) {
        			return nei.val;
        		}
    			visited.add(nei);
    			queue.offer(nei);
    			
    		}
    	}
    	return root.val;
    }
}
