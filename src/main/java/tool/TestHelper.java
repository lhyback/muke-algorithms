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
		int n = 10000;
		int[] arrInt = TestHelper.generateRandomIntArray(n, 0, n);
		//copy(n = 10000)
		int[] arrIntC1 = Arrays.copyOf(arrInt, arrInt.length);

		//测试性能，对比O(n^2)
		String sortClassName = "ch02.SelectionSort";
		System.out.print("SelectionSort(n=10000): ");
		try {
			TestHelper.testSortPerformance(sortClassName, arrInt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.print("SelectionSort(n=100000): ");
		n = 100000;
		arrInt = TestHelper.generateRandomIntArray(n, 0, n);
		try {
			TestHelper.testSortPerformance(sortClassName, arrInt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("");

		//1. 测试插入排序和选择排序性能对比
		sortClassName = "ch02.InsertionSortBase";
		System.out.print("InsertSortBase(n=10000): ");
		try {
			TestHelper.testSortPerformance(sortClassName, arrIntC1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//2. 测试插入排序改进版、选择排序性能对比
		n = 10000;
		arrInt = TestHelper.generateRandomIntArray(n, 0, n);
		arrIntC1 = Arrays.copyOf(arrInt, arrInt.length);
		int[] arrIntC2 = Arrays.copyOf(arrInt, arrInt.length);
		int[] arrIntC3 = Arrays.copyOf(arrInt, arrInt.length);
		try {
			System.out.print("SelectionSort(n=10000): ");
			TestHelper.testSortPerformance("ch02.SelectionSort", arrInt);
			System.out.print("改进后, InsertionSort(n=10000): ");
			TestHelper.testSortPerformance("ch02.InsertionSort", arrIntC1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		//3. 测试冒泡排序及改进版
		try {
			System.out.print("BubbleSortBase(n=10000): ");
			TestHelper.testSortPerformance("ch02.BubbleSortBase", arrIntC2);
			System.out.print("改进后, BubbleSort(n=10000): ");
			TestHelper.testSortPerformance("ch02.BubbleSort", arrIntC3);
//			arrIntC2 = TestHelper.generateRandomIntArray(100000, 0, 100000);
//			arrIntC3 = Arrays.copyOf(arrIntC2, arrIntC2.length);
//			System.out.print("BubbleSort(n=100000): ");
//			TestHelper.testSortPerformance("ch02.BubbleSortBase", arrIntC2);
//			System.out.print("BubbleSort(n=100000): ");
//			TestHelper.testSortPerformance("ch02.BubbleSort", arrIntC3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("");

		//4. 测试基本有序的待排序列--插入排序
		arrInt = TestHelper.generateNearlyOrderedIntArray(n, 2);
		arrIntC1 = Arrays.copyOf(arrInt, arrInt.length);
		arrIntC2 = Arrays.copyOf(arrInt, arrInt.length);
		try {
			System.out.print("SelectionSort(n=10000, swaps=10): ");
			TestHelper.testSortPerformance("ch02.SelectionSort", arrInt);
			System.out.print("InsertionSort(n=10000, swaps=10): ");
			TestHelper.testSortPerformance("ch02.InsertionSort", arrIntC1);
			System.out.print("BubbleSort(n=10000, swaps=10): ");
			TestHelper.testSortPerformance("ch02.BubbleSort", arrIntC2);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
