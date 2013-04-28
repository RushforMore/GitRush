
public class StringCompress {
	public static void main(String[] args){
		System.out.println(new StringCompress().compress("aabbcccccccccccde"));
	}
	
	public String compress(String str){
		if(str.length() == 0 || str == null){
			return null;
		}
		char last = str.charAt(0);
		int count = 1;
		String newStr = "";
		for(int i = 1; i < str.length(); i++){
			if(str.charAt(i) == last){
				count++;
			}
			else{
				newStr = newStr + last + count;
				last = str.charAt(i);
				count = 1;
			}
		}
		newStr = newStr + last + count;
		return (newStr.length() <= str.length())?newStr:str;
	}
}
