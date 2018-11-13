package training;

import org.junit.Test;
import training.sort.HeapSort;
import training.sort.MergeSort;
import training.sort.QuickSort;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by lhy on 2018/10/30.
 */
public class TestTrains {
	@Test
	public void testSort() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Integer[] nums = generateRandomIntegers(1000000);
		Integer[] nums2 = Arrays.copyOf(nums, nums.length);
		Integer[] nums3 = Arrays.copyOf(nums, nums.length);
//		System.out.println(Arrays.toString(nums));
//		QuickSort.sort(nums);
//		System.out.println(Arrays.toString(nums));
		testSortPerformance(QuickSort.class.getName(), nums);
		testSortPerformance(MergeSort.class.getName(), nums2);
		testSortPerformance(HeapSort.class.getName(), nums3);
	}













	//[0...max)
	public static Integer[] generateRandomIntegers(int size, int max) {
		Integer[] arr = new Integer[size];
		for (int i = 0; i < size; i++) {
			arr[i] = (int) (Math.random() * max);
		}
		return arr;
	}

	public static Integer[] generateRandomIntegers(int size) {
		return generateRandomIntegers(size, size);
	}

	public static boolean isSorted(Comparable[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i].compareTo(arr[i + 1]) > 0) {
				return false;
			}
		}
		return true;
	}

	public void testSortPerformance(String className, Comparable[] arr) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Class clazz = Class.forName(className);
		Method method = clazz.getMethod("sort", Comparable[].class);
		long start = System.currentTimeMillis();
		method.invoke(null, new Object[]{arr});
		long end = System.currentTimeMillis();

		assert isSorted(arr);
		System.out.printf("[%s].sort(), time used: %.3fs\n", className, ((double) (end - start) / 1000));
	}

}