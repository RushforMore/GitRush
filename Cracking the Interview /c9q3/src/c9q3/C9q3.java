package c9q3;
/**
 * do the follow ups
 * @author zhucai
 *
 */
public class C9q3 {
	public static void main(String[] args){
		int[] arr = {1,2,2,2,3,4,5,7,9};
		new C9q3().findMagic(arr, 0 , arr.length - 1 );
	}

	public void findMagic(int[] arr, int left, int right){
		if(left > right || left < 0 || right > arr.length) return;
		int mid = (left + right) / 2;
		if(arr[mid] == mid) {
			System.out.println(mid);
		}
		int leftInd = Math.min(mid - 1, arr[mid]);
		findMagic(arr, left, leftInd);
		int rightInd = Math.max(mid + 1, arr[mid]);
		findMagic(arr, rightInd, right);

	}
}
