
public class SpaceSubstitution {
	public static void main(String[] args){
		System.out.println(new SpaceSubstitution().sub("ab cd e  ff "));
	}
	
	public String sub(String str){
		if(str.length() == 0 || str == null){
			return null;
		}
		int space = 0;
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) == ' '){
				space++;
			}
		}
		int newLen = space * 2 + str.length();
		char[] charSet = new char[newLen];
		for(int i = str.length() - 1; i >=0; i--){
			if(str.charAt(i) == ' '){
				charSet[newLen - 1] = '0';
				charSet[newLen - 2] = '2';
				charSet[newLen - 3] = '%';
				newLen -= 3;
			}
			else{
				charSet[newLen - 1] = str.charAt(i);
				newLen--;
			}
		}
		return new String(charSet);
	}
}
