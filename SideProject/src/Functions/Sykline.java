package Functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Sykline {
    public static List<int[]> getSkyline(int[][] buildings) {
    	List<int[]> height = new ArrayList<>();
    	List<int[]> res = new ArrayList<>();
    	for(int[] building : buildings) {    	
    		height.add(new int[] {building[0], -building[2]});
    		height.add(new int[] {building[1], building[2]});
    	}
    	Collections.sort(height, (a, b) -> {
            if(a[0] != b[0]){
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
    	Queue<Integer> pq = new PriorityQueue<Integer>((a, b) -> (b - a));
    	pq.offer(0);
    	int prev = 0;
    	for(int[] h : height) {
    		if(h[1] < 0) {
    			pq.offer(-h[1]);
    		}else {
    			pq.remove(h[1]);
    		}
    		int curr = pq.peek();
    		if(curr != prev){
    			res.add(new int[]{h[0], curr});
    			prev = curr;
    		}
    	}
    	return res;
    }
}
