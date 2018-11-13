package ch2_3;

import org.apache.commons.lang3.ArrayUtils;
import tool.SortTestHelper;

import java.util.Arrays;

/**
 * Created by lhy on 2018/10/9.
 */
public class SelectionSortBase {
	private SelectionSortBase(){}

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


	public static void main(String[] args) {
		//int[] nums = new int[]{10, 9, 8, 7, 7, 5, 6, 4, 3, 3, 2, 1};
		int[] nums = SortTestHelper.generateRandomIntArray(20, 4, 10);
		System.out.println(Arrays.toString(nums));
		System.out.println(Arrays.toString(SelectionSortBase.sort(nums)));
	}
}
