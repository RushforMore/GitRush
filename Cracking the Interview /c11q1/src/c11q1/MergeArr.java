package c11q1;

public class MergeArr {
	public static void main(String[] args){
		int[] arrA = new int[10];
		arrA[0] = 1;
		arrA[1] = 3;
		arrA[2] = 5;
		arrA[3] = 7;
		int[] arrB = {2,4,6,8,10};
		new MergeArr().mergeArr(arrA, arrB, 4, 5);
		for(int num: arrA){
			System.out.println(num);
		}
		
	}
	
	public void mergeArr(int[] arrA, int[] arrB, int lastA, int lastB){
		int len = lastA + lastB  - 1;
		int countA = lastA - 1;
		int countB = lastB - 1;
		while(countA >= 0 && countB >= 0){
			if(arrA[countA] <= arrB[countB]){
				arrA[len--] = arrB[countB--];
			}
			else{
				arrA[len--] = arrA[countA--];
			}
		}
		while(countB >= 0){
			arrA[len--] = arrB[countB--];
		}
	}
}
