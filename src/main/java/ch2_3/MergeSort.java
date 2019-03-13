package ch2_3;

/**
 * Created by lhy on 2018/10/15.
 */
public class MergeSort {
	/**
	 * [begin, end]归并排序
	 * @param arr
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int[] sort(int[] arr, int begin, int end) {
		if (begin >= end) {
			return arr;
		}
		int mid = (begin + end) / 2;
		sort(arr, begin, mid);
		sort(arr, mid + 1, end);
		if (arr[mid] > arr[mid + 1]) {
			merge(arr, begin, mid, end);
		}
		return arr;
	}

	public static int[] sort(int[] arr) {
		return sort(arr, 0, arr.length - 1);
	}

	/**
	 * merge arr[begin, mid] and arr[mid + 1, end]
	 * @param arr
	 * @param begin
	 * @param mid
	 * @param end
	 */
	public static void merge(int[] arr, int begin, int mid, int end) {
		int[] aux = new int[end - begin + 1];  //辅助数组暂存合并结果
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
