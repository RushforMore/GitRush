package c17q6;

public class FindUnsorted {
	
	public static void main(String[] args){
		int[] arr = {1,2,3,4,5,6,4,8,3,5,16,17,19};
		new FindUnsorted().findUnsorted(arr);
	}
	
	public int findLeftEnd(int[] arr){
		for(int i = 0; i < arr.length - 1; i++){
			if(arr[i] > arr[i + 1]){
				return i;
			}
		}
		return arr.length - 1;
	}
	
	public int findRightStart(int[] arr){
		for(int i = arr.length - 1; i >= 1; i--){
			if(arr[i] < arr[i - 1]){
				return i;
			}
		}
		return 0;
	}
	
	public int shrinkLeft(int[] arr, int minIndex, int start){
		int comp = arr[minIndex];
		for(int i = start; i >= 0; i--){
			if(arr[i] <= comp){
				return i + 1;
			}
		}
		return 0;
	}
	
	public int shrinkRight(int[] arr, int maxIndex, int start){
		int comp = arr[maxIndex];
		for(int i = start; i < arr.length; i++){
			if(arr[i] >= comp){
				return i - 1;
			}
		}
		return arr.length - 1;
	}
	
	public void findUnsorted(int[] arr){
		int leftEnd = findLeftEnd(arr);
		int rightStart = findRightStart(arr);
		int minIndex = leftEnd + 1;
		int maxIndex = rightStart - 1;
		if(minIndex >= arr.length) return;
		for(int i = leftEnd; i <= rightStart; i++){//<= if the most important!
			if(arr[i] < arr[minIndex]){
				minIndex = i;
			}
			if(arr[i] > maxIndex){
				maxIndex = i;
			}
		}
		int left = shrinkLeft(arr, minIndex, leftEnd);
		int right = shrinkRight(arr, maxIndex, rightStart);
		System.out.println(left + " " + right);
	}
}
