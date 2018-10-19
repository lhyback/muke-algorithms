package ch2_3;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by lhy on 2018/10/16.
 */
public class QuickSortTwoWay {
	public static int[] sort(int[] arr, int l, int r) {
		if (l >= r) {
			return arr;
		}
		int p = partition2(arr, l, r);
		sort(arr, l, p - 1);
		sort(arr, p + 1, r);
		return arr;
	}

	public static int[] sort(int[] arr) {
		return sort(arr, 0, arr.length - 1);
	}

	//arr[l...i-1] < v && arr[j+1...r] > v
	public static int partition2(int[] arr, int l, int r) {
		int random = l + (int) (Math.random() * (r - l + 1));
		ArrayUtils.swap(arr, l, random);

		int v = arr[l];
		int i = l + 1, j = r;
		while (true) {
			while (i <= r && arr[i] < v) i++;
			while (j >= l + 1 && arr[j] > v) j--;
			//经过上面扫描， i 可能已指过 j
			if (i >= j) {
				break;
			}
			ArrayUtils.swap(arr, i, j);
			i++;
			j--;
		}
		ArrayUtils.swap(arr, l, j);  //a[j] <= v
		return j;
	}
}
