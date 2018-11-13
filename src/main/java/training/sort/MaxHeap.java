package training.sort;

/**
 * Created by lhy on 2018/10/30.
 */
public class MaxHeap<Item extends Comparable> {
	private Item[] data;
	private int capacity;
	private int count;

	public MaxHeap (int capacity) {
		this.data = (Item[]) new Comparable[capacity + 1];
		this.capacity = capacity;
		this.count = 0;
	}

	//O(n)复杂度的建堆--heapify
	public MaxHeap (int capacity, Item[] elements) {
		this.data = (Item[]) new Comparable[capacity + 1];
		this.capacity = capacity;
		this.count = elements.length;

		for (int i = 0; i < count; i++) {
			data[i + 1] = elements[i];
		}
		//heapify
		for (int i = count / 2; i >= 1; i--) {
			shiftDown(i);
			//shiftUp(i);
		}
	}


	public boolean isEmpty() {
		return count == 0;
	}

	public boolean add(Item element) {
		if (count >= capacity) {
			return false;
		}
		data[++count] = element;
		shiftUp(count);
		return true;
	}

	public Item remove() {
		if (isEmpty()) {
			return null;
		}
		Item e = data[1];
		data[1] = data[count];
		count--;
		shiftDown(1);
		return e;  //缓存了remove的元素引用
	}

	private void shiftUp(int i) {
		Item value = data[i];
		int j = i;
		for (; j / 2 >= 1 && data[j / 2].compareTo(value) < 0; j /= 2) {
			data[j] = data[j / 2];
		}
		data[j] = value;
	}

	private void shiftDown(int i) {
		Item value = data[i];
		while(i * 2 <= count) {
			int j = i * 2;
			if (j + 1 <= count && data[j].compareTo(data[j + 1]) < 0) {
				j++;
			}
			if (data[i].compareTo(data[j]) < 0) {
				data[i] = data[j];
				//swap(i, j);
				i = j;
			} else {
				data[j] = value;
				break;
			}
		}
	}

	private void swap(int i, int j) {
		Item tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	public static void main(String[] args) {

	}
}
