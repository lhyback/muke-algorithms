package ch2_3;

import org.apache.commons.lang3.ArrayUtils;
import tool.SortTestHelper;

import java.util.Arrays;
import java.util.Comparator;



/**
 * Created by lhy on 2018/10/9.
 */
public class SelectionSort {
	private SelectionSort(){}

	public static int[] sort(int[] nums) {
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				minIndex = nums[j] < nums[minIndex] ? j : minIndex;
			}

			ArrayUtils.swap(nums, i, minIndex);
		}
		return nums;
	}

	//多态并不适用于primitive类型，该特性只能应用于对象
	public static Comparable[] sort(Comparable[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				minIndex = arr[j].compareTo(arr[minIndex]) < 0 ? j : minIndex;
			}

			ArrayUtils.swap(arr, i, minIndex);
		}
		return arr;
	}

	public static <T> T[] sort(T[] arr, Comparator<T> comparator) {
		for (int i = 0; i < arr.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				minIndex = comparator.compare(arr[j], arr[minIndex]) < 0 ? j : minIndex;
			}

			ArrayUtils.swap(arr, i, minIndex);
		}
		return arr;
	}

	public static void main(String[] args) {
		//测试int（转为Integer才能用。也可重载一个sort(int [])方法）
		int[] arrInt = SortTestHelper.generateRandomIntArray(10, 4, 10);
		Integer[] arrInteger = new Integer[arrInt.length];
		for (int i = 0; i < arrInteger.length; i++) {
			arrInteger[i] = arrInt[i];
		}
		System.out.println(Arrays.toString(arrInteger));
		System.out.println(Arrays.toString(SelectionSort.sort(arrInteger)));

//		//测试Double
//		Double[] arrDouble = new Double[]{3.0, 2.9, 3.1, 0.4, -99D};
//		System.out.println(Arrays.toString(arrDouble));
//		SelectionSort.sort(arrDouble);
//		System.out.println(Arrays.toString(arrDouble));
//
//		//测试String
//		String[] arrString = new String[]{"hello", "world", "apple", "applez", "he"};
//		System.out.println(Arrays.toString(arrString));
//		SelectionSort.sort(arrString);
//		System.out.println(Arrays.toString(arrString));
//
//		//测试自定义类和Comparator
//		Student[] arrStudent = new Student[4];
//		arrStudent[0] = new Student("A", 98);
//		arrStudent[1] = new Student("B", 93);
//		arrStudent[2] = new Student("C", 95);
//		arrStudent[3] = new Student("D", 100);
//		for (Student student : arrStudent) {
//			System.out.println(student);
//		}
//
//		//functional program
//		Student[] ans = SelectionSort.sort(arrStudent, (x, y) -> {
//			return x.getScore() - y.getScore();
//		});
//		for (Student student : arrStudent) {
//			System.out.println(student);
//		}



	}

}
