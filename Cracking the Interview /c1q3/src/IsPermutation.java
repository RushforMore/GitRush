import java.util.Arrays;


public class IsPermutation {

	public static void main(String[] args){
		System.out.println(new IsPermutation().isPerm("abcd", "dbbb"));
	}
	
	public boolean isPerm(String s1, String s2){
		if(s1.length() != s2.length()){
			return false;
		}
		else{
			return sort(s1).equals(sort(s2));
		}
	}
	
	public String sort(String str){
		char[] charSet = str.toCharArray();
		Arrays.sort(charSet);
		return new String(charSet);
	}

}
