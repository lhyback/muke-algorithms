package tool;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by lhy on 2018/10/9.
 */
public class TestHelper {

	//element ~ [rangeR, rangeL]
	public static int[] generateRandomIntArray(int n, int rangeL, int rangeR) {

		assert rangeL <= rangeR;

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			int offset = (int) (Math.random() * (rangeR - rangeL + 1));  //[0, distance + 1)
			arr[i] = rangeL + offset;
		}
		return arr;
	}

	/**
	 * 先产生一个[0, n - 1]的有序数组，再随机交换swaps次。
	 * swaps越大，有序性越差
	 * @param n size
	 * @param swaps
	 */
	public static int[] generateNearlyOrderedIntArray(int n, int swaps) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i;
		}

		for (int i = 0; i < swaps; i++) {
			int j = (int) (Math.random() * n);
			int k = (int) (Math.random() * n);
			ArrayUtils.swap(arr, j, k);
		}
		return arr;
	}


	public static boolean isSorted(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				return false;
			}
		}
		return true;
	}

	public static boolean isSorted(Comparable[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i].compareTo(arr[i + 1]) > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 反射实现回调，完成排序并输出排序算法的性能. 由于回调函数是static的，不方便用接口去回调。
	 * @param sortClassName 排序工具类名
	 * @param arr 待排整型数组
	 */
	public static void testSortPerformance(String sortClassName, int[] arr) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Class clazz = Class.forName(sortClassName);
		Method method = clazz.getMethod("sort", int[].class);
		long startTime = System.currentTimeMillis();
		//invoke will return Object type
		int[] res = (int[]) method.invoke(null, arr);
		long endTime = System.currentTimeMillis();

		assert isSorted(res);
		System.out.printf("%.3f s\n", (double) (endTime - startTime) / 1000);
	}

	public static void testSortPerformance(String sortClassName, Comparable[] arr) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Class clazz = Class.forName(sortClassName);
		Method method = clazz.getMethod("sort", Comparable[].class);
		long startTime = System.currentTimeMillis();
		//invoke will return Object type
		Comparable[] res = (Comparable[]) method.invoke(null, arr);
		long endTime = System.currentTimeMillis();

		assert isSorted(res);
		System.out.printf("%.3f s\n", (double) (endTime - startTime) / 1000);
	}


	public static void main(String[] args) {
		int[] arrInt_1w_01 = TestHelper.generateRandomIntArray(10000, 0, 10000);
		int[] arrInt_10w_01 = TestHelper.generateRandomIntArray(100000, 0, 100000);
		int[] arrInt_ordered_01 = TestHelper.generateNearlyOrderedIntArray(10000, 10);
		int[] arrInt_100w_01 = TestHelper.generateRandomIntArray(1000000, 0, 1000000);

		int[] arrInt_1w_02 = Arrays.copyOf(arrInt_1w_01, arrInt_1w_01.length);
		int[] arrInt_1w_03 = Arrays.copyOf(arrInt_1w_01, arrInt_1w_01.length);
		int[] arrInt_1w_04 = Arrays.copyOf(arrInt_1w_01, arrInt_1w_01.length);
		int[] arrInt_1w_05 = Arrays.copyOf(arrInt_1w_01, arrInt_1w_01.length);
		int[] arrInt_1w_06 = Arrays.copyOf(arrInt_1w_01, arrInt_1w_01.length);
		int[] arrInt_1w_07 = Arrays.copyOf(arrInt_1w_01, arrInt_1w_01.length);
		int[] arrInt_1w_08 = Arrays.copyOf(arrInt_1w_01, arrInt_1w_01.length);
		int[] arrInt_1w_09 = Arrays.copyOf(arrInt_1w_01, arrInt_1w_01.length);

		int[] arrInt_10w_02 = Arrays.copyOf(arrInt_10w_01, arrInt_10w_01.length);
		int[] arrInt_10w_03 = Arrays.copyOf(arrInt_10w_01, arrInt_10w_01.length);
		int[] arrInt_10w_04 = Arrays.copyOf(arrInt_10w_01, arrInt_10w_01.length);
		int[] arrInt_10w_05 = Arrays.copyOf(arrInt_10w_01, arrInt_10w_01.length);
		int[] arrInt_10w_06 = Arrays.copyOf(arrInt_10w_01, arrInt_10w_01.length);

		int[] arrInt_ordered_02 = Arrays.copyOf(arrInt_ordered_01, arrInt_ordered_01.length);
		int[] arrInt_ordered_03 = Arrays.copyOf(arrInt_ordered_01, arrInt_ordered_01.length);
		int[] arrInt_ordered_04 = Arrays.copyOf(arrInt_ordered_01, arrInt_ordered_01.length);
		int[] arrInt_ordered_05 = Arrays.copyOf(arrInt_ordered_01, arrInt_ordered_01.length);
		int[] arrInt_ordered_06 = Arrays.copyOf(arrInt_ordered_01, arrInt_ordered_01.length);
		int[] arrInt_ordered_07 = Arrays.copyOf(arrInt_ordered_01, arrInt_ordered_01.length);

		int[] arrInt_100w_02 = Arrays.copyOf(arrInt_100w_01, arrInt_100w_01.length);
		int[] arrInt_100w_03 = Arrays.copyOf(arrInt_100w_01, arrInt_100w_01.length);

		//1. 测试性能，对比O(n^2)
		try {
			String sortClassName = "ch2_3.SelectionSort";
			System.out.print("SelectionSort(n=10000): ");
			TestHelper.testSortPerformance(sortClassName, arrInt_1w_01);
			System.out.print("SelectionSort(n=100000): ");
			TestHelper.testSortPerformance(sortClassName, arrInt_10w_01);
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}

		//2. 测试插入排序和选择排序性能对比
		try {
			System.out.print("SelectionSort(n=10000): ");
			TestHelper.testSortPerformance("ch2_3.SelectionSort", arrInt_1w_03);
			System.out.print("InsertSortBase(n=10000): ");
			TestHelper.testSortPerformance("ch2_3.InsertionSortBase", arrInt_1w_02);
			System.out.print("改进后, InsertionSort(n=10000): ");
			TestHelper.testSortPerformance("ch2_3.InsertionSort", arrInt_1w_04);
		} catch (Exception e) {
			e.printStackTrace();
		}


		//3. 测试冒泡排序及改进版
		try {
			System.out.print("BubbleSortBase(n=10000): ");
			TestHelper.testSortPerformance("ch2_3.BubbleSortBase", arrInt_1w_05);
			System.out.print("改进后, BubbleSort(n=10000): ");
			TestHelper.testSortPerformance("ch2_3.BubbleSort", arrInt_1w_06);
//			arrIntC2 = TestHelper.generateRandomIntArray(100000, 0, 100000);
//			arrIntC3 = Arrays.copyOf(arrIntC2, arrIntC2.length);
//			System.out.print("BubbleSort(n=100000): ");
//			TestHelper.testSortPerformance("ch2_3.BubbleSortBase", arrIntC2);
//			System.out.print("BubbleSort(n=100000): ");
//			TestHelper.testSortPerformance("ch2_3.BubbleSort", arrIntC3);
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}

		//5. 测试Ordered的待排序列
		try {
			System.out.print("SelectionSort(n=10000, swaps=10): ");
			TestHelper.testSortPerformance("ch2_3.SelectionSort", arrInt_ordered_01);
			System.out.print("InsertionSort(n=10000, swaps=10): ");
			TestHelper.testSortPerformance("ch2_3.InsertionSort", arrInt_ordered_02);
			System.out.print("BubbleSort(n=10000, swaps=10): ");
			TestHelper.testSortPerformance("ch2_3.BubbleSort", arrInt_ordered_03);
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}

		//6. 测试合并排序
		try {
			System.out.print("MergeSortBase(n=10000): ");
			TestHelper.testSortPerformance("ch2_3.MergeSortBase", arrInt_1w_07);
			System.out.print("MergeSort(n=10000): ");
			TestHelper.testSortPerformance("ch2_3.MergeSort", arrInt_1w_08);
			System.out.print("MergeSortBU(n=10000): ");
			TestHelper.testSortPerformance("ch2_3.MergeSortBU", arrInt_1w_09);
			System.out.print("InsertionSort(n=100000): ");
			TestHelper.testSortPerformance("ch2_3.InsertionSort", arrInt_10w_02);
			System.out.print("MergeSortBase(n=100000): ");
			TestHelper.testSortPerformance("ch2_3.MergeSortBase", arrInt_10w_03);
			System.out.print("MergeSort(n=100000): ");
			TestHelper.testSortPerformance("ch2_3.MergeSort", arrInt_10w_04);

			//Nearly Ordered
			System.out.print("InsertionSort(n=10000, swaps=10): ");
			TestHelper.testSortPerformance("ch2_3.InsertionSort", arrInt_ordered_04);
			System.out.print("MergeSort(n=10000, swaps=10): ");
			TestHelper.testSortPerformance("ch2_3.MergeSort", arrInt_ordered_05);
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}

		//7. 测试合并排序、快速排序、升级版快排（random，适用于基本有序）、
		//   二路快排（大量相等元素）、三路快排（大量相等元素最优化）
		try {
			System.out.print("MergeSortBase(n=1000000): ");
			TestHelper.testSortPerformance("ch2_3.MergeSortBase", arrInt_100w_01);
			System.out.print("QuickSortBase(n=1000000): ");
			TestHelper.testSortPerformance("ch2_3.QuickSortBase", arrInt_100w_02);
			System.out.print("QuickSortBase(n=10000, swaps=10): ");
			TestHelper.testSortPerformance("ch2_3.QuickSortBase", arrInt_ordered_06);
			System.out.print("QuickSort(n=10000, swaps=10): ");
			TestHelper.testSortPerformance("ch2_3.QuickSort", arrInt_ordered_07);

			//only two value
			int[] arrInt_manyDuplicated = generateRandomIntArray(1000000, 0, 10);
			int[] arrInt_manyDuplicated_copy = Arrays.copyOf(arrInt_manyDuplicated, arrInt_manyDuplicated.length);
			int[] arrInt_manyDuplicated_copy2 = Arrays.copyOf(arrInt_manyDuplicated, arrInt_manyDuplicated.length);
			int[] arrInt_manyDuplicated_copy3 = Arrays.copyOf(arrInt_manyDuplicated, arrInt_manyDuplicated.length);
//			System.out.print("QuickSort(manyDuplicated, n=100000): ");
//			TestHelper.testSortPerformance("ch2_3.QuickSort", arrInt_manyDuplicated);
			System.out.print("QuickSortTwoWay(manyDuplicated, n=1000000): ");
			TestHelper.testSortPerformance("ch2_3.QuickSortTwoWay", arrInt_manyDuplicated_copy);
			System.out.print("QuickSortThreeWay(manyDuplicated, n=1000000): ");
			TestHelper.testSortPerformance("ch2_3.QuickSortThreeWay", arrInt_manyDuplicated_copy2);
			System.out.print("MergeSort(manyDuplicated, n=1000000): ");
			TestHelper.testSortPerformance("ch2_3.MergeSort", arrInt_manyDuplicated_copy2);


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
