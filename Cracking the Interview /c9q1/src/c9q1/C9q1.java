package c9q1;

public class C9q1 {
	
	static int[] map = new int[100];
	
	public static void main(String[] args){
		System.out.println(new C9q1().count(99, map));
	}
	
	public int count(int n, int[] map){
		if(n < 0){
			return 0;
		}
		if(n == 0){
			return 1;
		}
		if(map[n] != 0){
			return map[n];
		}
		else{
			map[n] = count(n - 1, map) + count(n - 2, map) + count(n - 3, map);
			return map[n];
		}
		
	}
}
