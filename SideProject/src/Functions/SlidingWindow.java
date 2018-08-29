package Functions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SlidingWindow {
	private static PriorityQueue<Integer> min = new PriorityQueue<>();
	private static PriorityQueue<Integer> max = new PriorityQueue<>((a, b) -> (b - a));
	
	public static ArrayList<Integer> slidingWindowMax(int[] nums, int k){
		LinkedList<Integer> queue = new LinkedList<>();
		ArrayList<Integer> res = new ArrayList<>();
		for(int i = 0; i < nums.length; i++) {
			//remove head element if it is the i - k index
			if(!queue.isEmpty() && queue.peekFirst() == i - k) {
				queue.pollFirst();
			}
			//remove elements in queue which smaller than current adding element
			while(!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
				queue.removeLast();
			}
			//add element to top
			queue.offerLast(i);
			if(i - k + 1>= 0) res.add(nums[queue.peek()]);
		}
		return res;
	}
	
	public static ArrayList<Integer> slidingWindowMin(int[] nums, int k){
		LinkedList<Integer> queue = new LinkedList<>();
		ArrayList<Integer> res = new ArrayList<>();
		for(int i = 0; i < nums.length; i++) {
			if(!queue.isEmpty() && queue.peekFirst() == i - k) {
				queue.pollFirst();
			}
			while(!queue.isEmpty() && nums[queue.peekLast()] > nums[i]) {
				queue.removeLast();
			}
			queue.offerLast(i);
			if(i - k + 1>= 0) res.add(nums[queue.peek()]);
		}
		return res;
	}
	
	public static ArrayList<Integer> slidingWindowMedian(int[] nums, int k){
		int len = nums.length;
		ArrayList<Integer> res = new ArrayList<>();

		for(int i = 0; i < len; i++) {
			addNum(nums[i]);
			if(i >= k - 1) { 
				res.add(getMed());
				deleteNum(nums[i - k + 1]);
			}
		}
		return res;
	}
	
	private static void addNum(int num) {
		if(num < getMed()) {
			max.add(num);
		}else {
			min.add(num);
		}
		if(max.size() > min.size()) {
			min.add(max.poll());
		}
		if(min.size() > max.size() + 1) {
			max.add(min.poll());
		}
	}

	private static void deleteNum(int num) {
		if(num < getMed()) {
			max.remove(num);
		}else {
			min.remove(num);
		}
		if(max.size() > min.size()) {
			min.add(max.poll());
		}
		if(min.size() > max.size() + 1) {
			max.add(min.poll());
		}
	}
	private static int getMed() {
		if(max.isEmpty() && min.isEmpty()) return 0;
		if(max.size() == min.size()) {
			return (max.peek() + min.peek()) / 2;
		}else {
			return min.peek();
		}
		
	}
}

