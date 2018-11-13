package training.sort;

/**
 * Created by lhy on 2018/10/30.
 */
public class QuickSort {
	private QuickSort(){}

	public static void sort(Comparable[] arr) {
		sort(arr, 0, arr.length - 1);
	}


	//sort: [l...r]
	public static void sort(Comparable[] arr, int l, int r) {
		if (l >= r) {
			return;
		}
		int m = partition(arr, l, r);
		sort(arr, l, m - 1);
		sort(arr, m + 1, r);
	}

	//return the final position of the v element;
	public static int partition(Comparable[] arr, int l, int r) {
		//随机取枢轴元素，防止有序情况下的快排性能下降
		int random = l + (int) (Math.random() * (r - l + 1));
		swap(arr, l, random);

		Comparable v = arr[l];
		int i = l + 1;
		int j = r;
		//arr[l...i - 1] <= v && arr[j + 1...r] >= v
		while (true) {
			while (i <= r && arr[i].compareTo(v) < 0) i++;
			while (j > l && arr[j].compareTo(v) > 0) j--;
			if (i >= j) {
				break;
			}
			swap(arr, i, j);
			i++;
			j--;
		}
		swap(arr, l, j);
		return j;
	}

	public static void swap(Object[] arr, int i, int j) {
		Object temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}


}
