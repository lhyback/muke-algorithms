package training.sort;

import training.TestTrains;

import java.util.Arrays;

/**
 * Created by lhy on 2018/10/30.
 */
public class MergeSort {
	public static void sort(Comparable[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	//sort: arr[l...r]
	public static void sort(Comparable[] arr, int l, int r) {
		if (l >= r) {
			return;
		}
		int m = (l + r) / 2;
		sort(arr, l, m);
		sort(arr, m + 1, r);
		if (arr[m].compareTo(arr[m + 1]) > 0) {
			merge(arr, l, m, r);
		}

	}

	public static void merge(Comparable[] arr, int l, int m, int r) {
		int i = l;
		int j = m + 1;

		Comparable[] aux = new Comparable[r - l + 1];
		for (int k = 0; k < aux.length; k++) {
			if (i > m) {
				aux[k] = arr[j++];
			} else if (j > r) {
				aux[k] = arr[i++];
			} else if (arr[i].compareTo(arr[j]) < 0) {
				aux[k] = arr[i++];
			} else {
				aux[k] = arr[j++];
			}
		}
		for (int k = 0; k < aux.length; k++) {
			arr[l + k] = aux[k];
		}
		return;
	}

	public static void main(String[] args) {
		Integer[] nums = TestTrains.generateRandomIntegers(10);
		System.out.println(Arrays.toString(nums));
		sort(nums);
		System.out.println(Arrays.toString(nums));
	}
}
