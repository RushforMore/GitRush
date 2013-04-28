
public class UniqueChar {
	public static void main(String[] args){
		System.out.println(new UniqueChar().isUnique("abcdeeefg"));
	}
	
	public boolean isUnique(String str){
		boolean[] charSet = new boolean[256];
		if(str.length() > 256){
			return false;
		}
		for(int i = 0; i < str.length(); i++){
			if(charSet[str.charAt(i)]){
				return false;
			}
			else{
				charSet[str.charAt(i)] = true;
			}
		}
		return true;
	}
}
