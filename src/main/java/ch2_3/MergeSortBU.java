package ch2_3;

/**
 * Created by lhy on 2018/10/15.
 */
public class MergeSortBU {
	public static int[] sort(int[] arr) {
		int n = arr.length;
		for (int size = 1; size < n; size += size) {
			for (int i = 0; i + size < n; i += size * 2) {
				merge(arr, i, i + size - 1, Math.min(i + size * 2 - 1, n - 1));
			}
		}
		return arr;
	}

	public static void merge(int[] arr, int begin, int mid, int end) {
		int[] aux = new int[end - begin + 1];  //辅助数组
		int i = begin;
		int j = mid + 1;
		for (int k = 0; k < aux.length; k++) {
			if (i > mid) {
				aux[k] = arr[j++];
			} else if (j > end) {
				aux[k] = arr[i++];
			} else if (arr[i] < arr[j]) {
				aux[k] = arr[i++];
			} else {
				aux[k] = arr[j++];
			}
		}
		for (int p = begin; p <= end; p++) {
			arr[p] = aux[p - begin];
		}
		return;
	}
}
