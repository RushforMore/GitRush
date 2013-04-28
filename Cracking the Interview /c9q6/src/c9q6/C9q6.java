package c9q6;
import java.util.*;
public class C9q6 {
	
	public static void main(String[] args){
		new C9q6().generateParem(2);
	}
	
	public void generateParem(int count){
		char[] str = new char[count * 2];
		ArrayList<String> list = new ArrayList<String>();
		getParem(list, count, count, str, 0);
		for(String s: list){
			System.out.println(s);
		}
	}
	
	public void getParem(ArrayList<String> list, int leftRem, int rightRem, char[] str, int count){
		if(leftRem < 0 || rightRem < leftRem){
			return;
		}
		if(leftRem == 0 && rightRem == 0){
			list.add(new String(str));
			return;
		}
		if(leftRem > 0){
			str[count] = '(';
			getParem(list, leftRem - 1, rightRem, str, count + 1);
		}
		if(rightRem > leftRem){
			str[count] = ')';
			getParem(list, leftRem, rightRem - 1, str, count + 1);
		}

	}
}
