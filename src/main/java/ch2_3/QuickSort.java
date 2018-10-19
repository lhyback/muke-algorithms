package ch2_3;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by lhy on 2018/10/16.
 */
public class QuickSort {
	public static int[] sort(int[] arr, int begin, int end) {
		//递归出口
		if (begin >= end) {
			return arr;
		}

		int p = partition(arr, begin, end);
		sort(arr, begin, p - 1);
		sort(arr, p + 1, end);
		return arr;
	}

	public static int[] sort(int[] arr) {
		return sort(arr, 0, arr.length - 1);
	}

	/**
	 *
	 * @param arr
	 * @param begin
	 * @param end
	 * @return int p: p means the final index of the privote element(the random element)
	 */
	public static int partition(int[] arr, int begin, int end) {
		int random = begin + (int) (Math.random() * (end - begin + 1));
		int v = arr[random];
		ArrayUtils.swap(arr, begin, random);  //随机的元素交换到首位置

		int j = begin;  //arr[begin...j] < v && arr[j+1...i-1] >= v
		for (int i = begin + 1; i <= end; i++) {
			if (arr[i] < v) {
				ArrayUtils.swap(arr, i, j + 1);
				j++;
			}
		}
		ArrayUtils.swap(arr, j, begin);  //枢轴元素最终落位
		return j;
	}
}
