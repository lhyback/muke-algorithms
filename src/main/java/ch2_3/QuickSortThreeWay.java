package ch2_3;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by lhy on 2018/10/17.
 */
public class QuickSortThreeWay {

	public static int[] sort(int[] arr, int l, int r) {
		//千万别忘了递归出口
		if (l >= r) {
			return arr;
		}

		//partition(因为三路排序每次partition需要返回lt、gt两个索引位置，所以没有封装成一个函数)
		int random = l + (int) (Math.random() * (r - l + 1));
		ArrayUtils.swap(arr, l, random);
		int v = arr[l];
		int lt = l;   //a[l...lt] < v
		int gt = r + 1; //a[gt...r] > v
		for (int i = l + 1; i < gt;) {
			if (arr[i] < v) {
				ArrayUtils.swap(arr, i, lt + 1);
				lt++;
				i++;
			} else if (arr[i] > v) {
				ArrayUtils.swap(arr, i, gt - 1);
				gt--;
			}
			else {
				i++;
			}
		}
		ArrayUtils.swap(arr, l, lt);


		sort(arr, l, lt - 1);
		sort(arr, gt, r);
		return arr;
	}

	public static int[] sort(int[] arr) {
		return sort(arr, 0, arr.length - 1);
	}
}
