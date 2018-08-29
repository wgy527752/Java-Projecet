package Functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Random;

public class RandomizedCollection {
	private HashMap<Integer, LinkedHashSet> map;
	private ArrayList<Integer> nums;
	
	public RandomizedCollection() {
		map = new HashMap<>();
		nums = new ArrayList<>();
	}
	
	public boolean insert(int val) {
		if(map.containsKey(val)) {
			nums.add(val);
			map.get(val).add(nums.size() - 1);
			return false;
		}
		map.putIfAbsent(val, new LinkedHashSet<Integer>());
		nums.add(val);
		map.get(val).add(nums.size() - 1);
		return true;
	}
	
	public boolean delete(int val) {
		if(!map.containsKey(val)) {
			return false;
		}
		int pos = (int) map.get(val).iterator().next();
		map.get(val).remove(pos);
		if(pos < nums.size() - 1) {
			int lastVal = nums.get(nums.size() - 1);
			nums.set(pos, lastVal);
			map.get(lastVal).remove(nums.size() - 1);
			map.get(lastVal).add(pos);
		}
		nums.remove(nums.size() - 1);
		if(map.get(val).isEmpty()) map.remove(val);
		return true;
	}
	
	public boolean update(int val, int replace) {
		if(!map.containsKey(val)) {
			return false;
		}
		if(map.containsKey(replace)) {
			map.get(replace).addAll(map.get(val));
		}else {
			map.putIfAbsent(replace, new LinkedHashSet<>(map.get(val)));
		}
		map.remove(val);
		return true;
	}
	
	public int getRandom() {
		Random rand = new Random();
		return nums.get(rand.nextInt(nums.size()));
	}
	
	public ArrayList<Entry<Integer, LinkedHashSet<Integer>>> getMap(){
		ArrayList<Entry<Integer, LinkedHashSet<Integer>>> list = new ArrayList<Entry<Integer, LinkedHashSet<Integer>>>();
		for(Entry e : map.entrySet()) {
			list.add(e);
		}
		return list;
	}
}
