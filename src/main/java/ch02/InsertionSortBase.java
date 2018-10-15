package ch02;

import org.apache.commons.lang3.ArrayUtils;
import tool.TestHelper;

import java.util.Arrays;

/**
 * Created by lhy on 2018/10/10.
 */
public class InsertionSortBase {
	public static int[] sort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					ArrayUtils.swap(arr, j, j - 1);
				} else {
					break;
				}
			}
		}
		return arr;
	}

	public static void main(String[] args) {
		int[] num = TestHelper.generateRandomIntArray(10, 4, 10);
		System.out.println(Arrays.toString(num));
		System.out.println(Arrays.toString(sort(num)));
	}
}
