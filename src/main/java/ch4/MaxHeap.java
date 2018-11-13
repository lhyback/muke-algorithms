package ch4;

import tool.SortTestHelper;

import java.util.Arrays;

/**
 * Created by lhy on 2018/10/19.
 */
public class MaxHeap<Item extends Comparable> {
	protected int capacity;
	protected Item[] data;
	protected int count;

	public MaxHeap(int capacity) {
		this.capacity = capacity;
		this.data = (Item[]) new Comparable[capacity + 1];
		this.count = 0;
	}

	//Heapify 用数组初始化一个堆
	public MaxHeap(Item[] arr, int capacity) {
		this.capacity = capacity;
		this.data = (Item[]) new Comparable[capacity + 1];
		for (int i = 0; i < arr.length; i++) {
			data[i + 1] = arr[i];  //注意：root是 i = 1的
		}
		this.count = arr.length;

 		//heapify
		for (int i = count / 2; i > 0; i--) {
			shiftDown(i);
		}
	}

	public Item[] getData() {
		return data;
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
		shiftUp(count);  //当前节点shift up
		return true;
	}

	public Item peek() {
		Item e = null;
		if (count > 0) {
			e = data[1];
			//move the end to the root
			data[1] = data[count];
			count--;
			//shift down
			shiftDown(1);
		}
		return e;
	}


	private void swap(int i, int j) {
		Item tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	private void shiftUp(int i) {
		Item value = data[i];
		while (i > 1 && value.compareTo(data[i / 2]) > 0) {
			//这一步swap可以优化（类似插入排序），找到i的最终位置进行赋值，不用一直swap（3n 优化为 n + 1）。
			//swap(i, i / 2);
			data[i] = data[i / 2];
			i /= 2;
		}
		data[i] = value;
	}

	public void shiftDown(int i) {
		while (i * 2 <= count) {
			int j = i * 2;  //j: 应该交换的孩子节点
			if (j + 1 <= count && data[j + 1].compareTo(data[j]) > 0) {
				j++;
			}
			if (data[i].compareTo(data[j]) >= 0) {
				break;
			}
			//同理，swap可以优化
			swap(i, j);
			i = j;
		}
	}

	public static void main(String[] args) {
		int[] data = SortTestHelper.generateRandomIntArray(30, 0, 50);
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

	public static void test() {

	}

}
