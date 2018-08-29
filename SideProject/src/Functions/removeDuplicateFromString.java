package Functions;

import java.util.HashMap;
import java.util.Map;

public class removeDuplicateFromString {
	
	public static String removeDuplicateLetters(String s) {
        if(s == null || s.length() == 0) return null;
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), i);
        }
        char[] result = new char[map.size()];
        char[] ch = s.toCharArray();
        int begin = 0, end = minLastOne(map);
        int idx = 0;
        while(true) {
        	char minChar = 'z';
        	for(int i = begin; i <= end; i++) {
        		if(map.containsKey(ch[i]) && ch[i] < minChar) {
        			minChar = ch[i];
        			begin = i + 1;
        		}
        	}
        	result[idx] = minChar;
    		System.out.println(minChar);
    		map.remove(result[idx]);
    		idx++;
    		if(idx == result.length) break;
    		if(ch[end] == minChar) end = minLastOne(map);
        }
        return new String(result);
    }
    
    public static int minLastOne(Map<Character, Integer> map){
        if (map == null || map.isEmpty()) return -1;
        int minLastPos = Integer.MAX_VALUE;
        for (int lastPos : map.values()) {
             minLastPos = Math.min(minLastPos, lastPos);
        }
        return minLastPos;
    }
}
