package c9q8;

public class MakingChange {
	public int makingChange(int n, int denom){
		int next_denom = 0;
		switch(denom){
		case(25):
			next_denom = 10;
		break;
		case(10):
			next_denom = 5;
		break;
		case(5):
			next_denom = 1;
		break;
		case(1):
			return 1;
		}
		int way = 0;
		for (int i = 0; n - i * denom > 0; i++){
			way += makingChange(n - i * denom, next_denom);
		}
		return way;
	}
	
	public static void main(String[] args){
		System.out.println(new MakingChange().makingChange(2, 25));
	}
}
