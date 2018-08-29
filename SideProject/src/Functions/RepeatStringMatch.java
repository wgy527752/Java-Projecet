package Functions;

public class RepeatStringMatch {
	public int repeatCount(String s1, String s2) {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		while(sb.indexOf(s2) < 0) {
			if(sb.length() - s1.length() > s2.length()) {
				return -1;
			}
			sb.append(s1);
			count++;
		}
		return count;
	}
}
