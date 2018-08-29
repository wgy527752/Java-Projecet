package Functions;

import java.util.ArrayList;

public class SplitSQL {
	public static ArrayList<String> parseSQL(String input){
	    ArrayList<String> res = new ArrayList<String>();
	    int pos = 0;
        boolean quote = false;
	    int size = input.length();
        StringBuilder tempString = new StringBuilder();
	    while(pos < size){
	        tempString = new StringBuilder();
	        quote = false;
	        while(pos < size && (input.charAt(pos) != ';' || quote)){
	        	tempString.append(input.charAt(pos));
	            if(input.charAt(pos) == '\\' && pos + 1 < size){
	                pos++;
	                tempString.append(input.charAt(pos));
	            }else if(input.charAt(pos) == '"'){
	            	quote = !quote;
	            }
	            pos++;
	        }
	        if(tempString.length() != 0){
	            res.add(tempString.toString().trim());
	        }
	        pos++;
	    }
	    return res;
	}
}
