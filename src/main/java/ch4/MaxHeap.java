package ch4;

import tool.TestHelper;

import java.util.Arrays;

/**
 * Created by lhy on 2018/10/19.
 */
public class MaxHeap<Item extends Comparable> {
	private int capacity;
	private Item[] data;
	private int count;

	public MaxHeap(int capacity) {
		this.capacity = capacity;
		this.data = (Item[]) new Comparable[capacity + 1];
		this.count = 0;
	}

	public int size() {
		return count;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public boolean insert(Item item) {
		if (count == capacity) {
			return false;
		}
		data[++count] = item;  //root节点从1开始
		shiftUp();
		return true;
	}

	private void swap(int i, int j) {
		Item tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	private void shiftUp() {
		int i = count;
		while (i > 1 && data[i].compareTo(data[i / 2]) > 0) {
			swap(i, i / 2);
			i /= 2;
		}
	}

	public static void main(String[] args) {
		int[] data = TestHelper.generateRandomIntArray(10, 0, 10);
		System.out.println(Arrays.toString(data));
		MaxHeap<Integer> maxHeap = new MaxHeap<>(100);
		for (int num : data) {
			if (!maxHeap.insert(num)) {
				throw new RuntimeException("Cannot insert, heap's capacity is full");
			}
		}
		System.out.println(maxHeap.size());
		System.out.println(Arrays.toString(maxHeap.data));

	}
}
