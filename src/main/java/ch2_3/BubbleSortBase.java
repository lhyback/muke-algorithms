package ch2_3;

import org.apache.commons.lang3.ArrayUtils;
import tool.TestHelper;

import java.util.Arrays;

/**
 * Created by lhy on 2018/10/10.
 */
public class BubbleSortBase {
	public static int[] sort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					ArrayUtils.swap(arr, j, j + 1);
				}
			}
		}
		return arr;
	}

	public static void main(String[] args) {
		int[] arr = TestHelper.generateRandomIntArray(100, 4, 10);
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(sort(arr)));
	}
}
