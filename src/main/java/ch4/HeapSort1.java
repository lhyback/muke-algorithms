package ch4;

import tool.SortTestHelper;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Created by lhy on 2018/10/19.
 */
public class HeapSort1 {
	public static Comparable[] sort(Comparable[] arr) {
		MaxHeap<Comparable> maxHeap = new MaxHeap<>(arr.length);
		for (int i = 0; i < arr.length; i++) {
			maxHeap.insert(arr[i]);
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i] = maxHeap.peek();
		}
		return arr;
	}

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		int n = 5000000;
		int[] arr = SortTestHelper.generateRandomIntArray(n, 0, n);
		Integer[] arrInteger = new Integer[n];
		for (int i = 0; i < n; i++) {
			arrInteger[i] = arr[i];
		}
		Integer[] arrInteger_c1 = Arrays.copyOf(arrInteger, arrInteger.length);

		SortTestHelper.testSortPerformance("ch2_3.QuickSortTwoWay", arr);
		SortTestHelper.testSortPerformance("ch4.HeapSort1", arrInteger);
		SortTestHelper.testSortPerformance("ch4.HeapSort2", arrInteger_c1);

	}

}
