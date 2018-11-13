package training.sort;

import training.TestTrains;

import java.util.Arrays;

/**
 * Created by lhy on 2018/10/31.
 */
public class HeapSort {
	private static MaxHeap maxHeap;

	private HeapSort(){}

	public static void sort(Comparable[] arr) {
//		maxHeap = new MaxHeap(arr.length);
//		for (int i = 0; i < arr.length; i++) {
//			maxHeap.add(arr[i]);
//		}
		//O(n) heapify
		maxHeap = new MaxHeap(arr.length, arr);

		for (int i = arr.length - 1; i >= 0; i--) {
			arr[i] = maxHeap.remove();
		}
	}


	public static void main(String[] args) {
		Integer[] num = TestTrains.generateRandomIntegers(10);
		System.out.println(Arrays.toString(num));
		sort(num);
		System.out.println(Arrays.toString(num));
	}
}
