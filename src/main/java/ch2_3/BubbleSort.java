package ch2_3;

import org.apache.commons.lang3.ArrayUtils;
import tool.SortTestHelper;

import java.util.Arrays;

/**
 * Created by lhy on 2018/10/15.
 */
public class BubbleSort {
	public static int[] sort(int[] arr) {
		int n = arr.length;
		int lastIndex = n - 1;
		while (lastIndex > 0) {
			int record = 0;
			//注意lastIndex处的元素是可以参与下一轮交换的！
			for (int i = 0; i < lastIndex; i++) {
				if (arr[i] > arr[i + 1]) {
					ArrayUtils.swap(arr, i, i + 1);
					record = i;
				}
			}
			lastIndex = record;
		}
		return arr;
	}

	public static void main(String[] args) {
		int[] arr = SortTestHelper.generateRandomIntArray(10, 4, 10);
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(sort(arr)));
	}
}
