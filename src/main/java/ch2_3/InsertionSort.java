package ch2_3;

/**
 * Created by lhy on 2018/10/10.
 */
public class InsertionSort {
	public static int[] sort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i];
			int j = i;
			for (; j > 0 && temp < arr[j - 1]; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = temp;
		}
		return arr;
	}
}
