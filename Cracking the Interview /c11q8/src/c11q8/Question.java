package c11q8;

public class Question {
	public static RankNode root = null;
	
	public static void track(int num){
		if (root == null){
			root = new RankNode(num);
		}
		else{
			root.insert(num);
		}
	}
	
	public static int getRank(int num){
		return root.getRank(num);
	}
	
	public static void main(String[] args){
		track(1);
		track(2);
		track(3);
		track(4);
		System.out.println(getRank(4));
	}
}
