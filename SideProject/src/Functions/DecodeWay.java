package Functions;

public class DecodeWay {
	public static int decodeString(String s) {
		if(s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[len] = 1;
        dp[len - 1] = s.charAt(len - 1) == '0' ? 0 : 1;
        for(int i = len - 2; i >= 0; i--){
            int first = Integer.valueOf(s.substring(i, i + 1));
            int sec = Integer.valueOf(s.substring(i, i + 2));
            if(first > 0 && first < 10){
                dp[i] += dp[i + 1];
            }
            if(sec >= 10 && sec <= 26){
                dp[i] += dp[i + 2];
            }
        }
        return dp[0];
	}
}
