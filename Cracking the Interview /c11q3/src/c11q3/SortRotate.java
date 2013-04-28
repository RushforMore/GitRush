package c11q3;

public class SortRotate {
	public static void main(String[] args){
		int[] arr = {50, 10, 20, 30, 40};
		System.out.println(new SortRotate().search(arr, 0, arr.length - 1, 40));
		
	}
	
	public int search(int[] arr, int left, int right, int x){
		if(left > right) return -1;
		int mid = (left + right) / 2;
		if(arr[mid] == x){
			return mid;
		}
		else{
			if(arr[mid] > arr[left]){
				if(x >= arr[left] && x < arr[mid]){
					return search(arr, left, mid - 1, x);
				}
				else{
					return search(arr, mid + 1, right, x);
				}
			}
			else if(arr[mid] < arr[right]){
				if(x <= arr[right] && x > arr[mid]){
					return search(arr, mid + 1, right, x);
				}
				else{
					return search(arr, left, mid - 1, x);
				}
			}
			else if(arr[left] == arr[mid]){
				if(arr[mid] != arr[right]){
					return search(arr, mid + 1, right, x);
				}
				else{
					int result =  search(arr, left, mid - 1, x);
					if(result == -1){
						result = search(arr, mid + 1, right, x);
					}
					return result;
				}
			}
		}
		return  -1;
	}
}
