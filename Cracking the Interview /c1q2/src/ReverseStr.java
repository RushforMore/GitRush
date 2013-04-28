
public class ReverseStr {

	public static void main(String[] args){
		System.out.println(new ReverseStr().reverseStr("abcde"));
	}
	
	public String reverseStr(String str){
		if(str == null || str.length() == 0){
			return null;
		}
		char[] charSet = str.toCharArray();
		for(int i = 0; i < charSet.length / 2; i++){
			char temp = charSet[i];
			charSet[i] = charSet[charSet.length - 1 - i];
			charSet[charSet.length - 1 - i] = temp;
		}
		return new String(charSet);
	}
}
