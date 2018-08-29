package Functions;

import java.util.ArrayList;
import java.util.List;

public class CountSmallerNumberAfterSelf {
	public static int[] countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
		int[] res = new int[nums.length];
		List<Integer> readed = new ArrayList<>();
		for (int i = nums.length - 1; i >= 0; i--){
			int smaller = countSmaller(readed, nums[i]);
			res[i] = smaller;
		}
		return res;
	}
	//use binary search to decide how many readed number smaller than target, and 
	//add that number into readed list	
	private static int countSmaller(List<Integer> readed, int target) {
		if (readed.size() == 0) {
			readed.add(target);
			return 0;
		}
		int left = 0;
		int right = readed.size() - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (readed.get(mid) < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		if (readed.get(right) < target) {
			//if target is bigger than all the thing readed
			readed.add(target);
			return right + 1;
		}
		readed.add(right, target);
		return right;
	}
}
