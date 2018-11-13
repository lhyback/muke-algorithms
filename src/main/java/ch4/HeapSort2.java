package ch4;

/**
 * Created by lhy on 2018/10/29.
 */
public class HeapSort2 {
	public static Comparable[] sort(Comparable[] arr) {
		//heapify init data[]
		MaxHeap<Comparable> maxHeap = new MaxHeap<>(arr, arr.length);
//		for (int i = 0; i < arr.length; i++) {
//			maxHeap.insert(arr[i]);
//		}

		for (int i = 0; i < arr.length; i++) {
			arr[i] = maxHeap.peek();
		}
		return arr;
	}
}
