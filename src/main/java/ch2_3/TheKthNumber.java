package ch2_3;

import org.apache.commons.lang3.ArrayUtils;
import tool.TestHelper;

/**
 * Created by lhy on 2018/10/18.
 */
public class TheKthNumber {
	public static int findTheKthNumberBrute(int[] arr, int k) {
		if (k > 0 && k <= arr.length) {
			QuickSortTwoWay.sort(arr);
			return arr[arr.length - k];
		} else {
			return -1;
		}
	}

	//找第n小的元素，k为元素下标（k = n-1）
	public static int findTheKthLeastLNumberQuickSort(int[] arr, int k, int l, int r) {

		//只剩一个元素考虑，肯定就是了，不需要再parttion
		if (l >= r) {
			return arr[l];
		}

		int p = partition(arr, l, r);
		if (p == k) {
			//找到了
			return arr[p];
		} else if (p > k) {
			//当前元素大于k元素, 考虑v的左边序列 arr[l...p-1]
			return findTheKthLeastLNumberQuickSort(arr, k, l, p - 1);
		} else {
			//注意：考虑右边的序列arr[p+1...r]时，传入的参数仍然是k，因为传入数组是arr，k的值也不应该改变（绝对位置是确定的）。
			return findTheKthLeastLNumberQuickSort(arr, k, p + 1, r);
		}
	}

	public static int partition(int[] arr, int l, int r) {
		int random = l + (int) (Math.random() * (r - l + 1));
		ArrayUtils.swap(arr, l, random);

		int v = arr[l];
		int i = l + 1;
		int j = r;
		while (true) {
			while (i <= r && arr[i] < v) i++;
			while (j >= l + 1 && arr[j] > v) j--;
			if (i >= j) {
				break;
			}
			ArrayUtils.swap(arr, i, j);
			i++;
			j--;
		}
		ArrayUtils.swap(arr, l, j);
		return j;
	}

	public static int findTheKthLeastLNumberQuickSort(int[] arr, int k) {
		return findTheKthLeastLNumberQuickSort(arr, k, 0, arr.length - 1);
	}

	public static void main(String[] args) {
		//int[] arr = new int[]{4, 3, 2, 1, 4, 6, 9};
		int n = 1000000;
		int[] arr = TestHelper.generateRandomIntArray(n, 0, n);
		int k = 13;
		long start = System.currentTimeMillis();
		System.out.println("the " + k + "th(large) number is " + findTheKthNumberBrute(arr, k));
		System.out.printf("time used: %.3f s\n", (double) (System.currentTimeMillis() - start) / 1000);

		start = System.currentTimeMillis();
		//第k大元素 == 第（arr.len - k + 1）小元素，对应的索引应该是arr.len - k
		System.out.println("the " + k + "th(large) number is " + findTheKthLeastLNumberQuickSort(arr, arr.length - k));
		System.out.printf("time used: %.3f s\n", (double) (System.currentTimeMillis() - start) / 1000);

	}
}
