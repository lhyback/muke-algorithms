package ch2_3;

import tool.SortTestHelper;

import java.util.Arrays;

/**
 * Created by lhy on 2018/10/18.
 */
public class InvertPairCount {
	private InvertPairCount(){}


	public static int invertPairCount(int[] arr, int l, int r) {
		int merge_count = 0;
		if (l >= r) {
			return merge_count;
		}

		int m = (l + r) / 2;
		int c1 = invertPairCount(arr, l, m);
		int c2 = invertPairCount(arr, m + 1, r);
		merge_count = mergeCount(arr, l, m, r) + c1 + c2;
		return merge_count;
	}

	public static int invertPairCount(int[] arr) {
		return invertPairCount(arr, 0, arr.length - 1);
	}

	public static int mergeCount(int[] arr, int l, int m, int r)  {
		int count = 0;
		int[] aux = new int[r - l + 1];
		int i = l;
		int j = m + 1;
		for (int k = 0; k < aux.length; k++) {
			if (i > m) {
				aux[k] = arr[j++];
			} else if (j > r) {
				aux[k] = arr[i++];
			} else if (arr[i] <= arr[j]){
				aux[k] = arr[i++];
			} else {
				//arr[i] > arr[j] 逆序对
				count += (m - i + 1);
				aux[k] = arr[j++];
			}
		}
		for (int k = 0; k < aux.length; k++) {
			arr[k + l] = aux[k];
		}
		return count;
	}

	public static int invertPairBruteCount(int[] arr) {
		int brute_count = 0;
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (arr[i] > arr[j]) {
					brute_count++;
				}
			}
		}
		return brute_count;
	}

	public static void main(String[] args) {
		int[] arr = SortTestHelper.generateRandomIntArray(10000, 0, 10);
		//int[] arr = new int[]{5, 2, 4, 3};
		int[] arr_copy = Arrays.copyOf(arr, arr.length);
		long start = System.currentTimeMillis();
		System.out.println("invert pair BruteCount: " + invertPairBruteCount(arr));
		System.out.printf("time used: %.3f s\n", (double)(System.currentTimeMillis() - start) / 1000);

		start = System.currentTimeMillis();
		System.out.println("invert pair MergeSortCount: " + invertPairCount(arr_copy));
		System.out.printf("time used: %.3f s\n", (double)(System.currentTimeMillis() - start) / 1000);

	}

}
