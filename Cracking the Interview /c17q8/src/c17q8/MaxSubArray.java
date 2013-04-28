package c17q8;
import java.util.*;
public class MaxSubArray {
	public static void main(String[] args){
		int[] arr = {-1,-2,1,-1,2,3,4,5,-2,-4,4};
		new MaxSubArray().getMax(arr);
	}
	
	public void getMax(int[] a){
		int maxSoFar = 0;
		int maxHere = 0;
		List<Integer> maxSub = new ArrayList<Integer>();
		for(int i = 0; i < a.length; i++){
			maxHere = Math.max(0, maxHere + a[i]);
			if(maxHere > maxSoFar){
				maxSub.add(a[i]);
			}
			maxSoFar = Math.max(maxHere, maxSoFar);
		}
		System.out.println("Max sum: " + maxSoFar);
		for(int i: maxSub){
			System.out.print(" " + i);
		}
		System.out.println();
	}
}
