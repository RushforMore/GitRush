package c11q8;

public class RankNode {
	int data;
	RankNode left, right;
	int leftSize;
	
	public RankNode(int d){
		data = d;
		leftSize = 0;
	}
	
	public void insert(int d){
		if (d <= data){
			if(left == null){
				left = new RankNode(d);
			}
			else{
				left.insert(d);
			}
			leftSize++;
		}
		else{
			if(right == null){
				right = new RankNode(d);
			}
			else{
				right.insert(d);
			}
		}
	}
	
	public int getRank(int d){
		if(data == d){
			return leftSize;
		}
		else if(data > d){
			if(left == null){
				return  -1;
			}
			else{
				return left.getRank(d);
			}
		}
		else if(data < d){
			int rightSize = (right == null)?-1: right.getRank(d);
			if(rightSize == -1) return -1;
			return leftSize + 1 + rightSize;
		}
		return -1;
	}
}
