package ch5;

import ch4.MaxHeap;

import java.util.*;

/**
 * Created by lhy on 2018/11/7.
 */
public class BinarySearchTree<Key extends Comparable, Value> {

	private class Node {
		private Key key;
		private Value value;
		private Node left;
		private Node right;

		public Node(Key key, Value value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}

	private int count;
	private Node root;

	public BinarySearchTree() {
		count = 0;
		root = null;
	}

	//public method
	public int size() {
		return count;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public boolean contain(Key key) {
		return search(key) != null;
	}

	public Value search(Key key) {
		Node node = search(root, key);
		return node == null ? null : node.value;
	}

	public void insert(Key key, Value value) {
		root = insert(root, key, value);
	}

	//public void delete(Key key) {}

	public void preOrder() {
		preOrder(root);
	}

	public void inOrder() {
		inOrder(root);
	}

	public void postOrder() {
		postOrder(root);
	}

	public void levelOrder() {
		levelOrder(root);
	}

	public Key maximum() {
		Node node = maximum(root);
		return node.key;
	}

	public Key minimum() {
		Node node = minimum(root);
		return node.key;

	}

	public Key removeMax() {
		return removeMax(root).key;
	}

	public Key removeMin() {
		return removeMin(root).key;
	}

	public boolean remove(Key key) {
		Node node = remove(root, key);
		return node != null;
	}





	//private aux method
	private Node search(Node node, Key key) {
		if (node == null) {
			return null;
		}

		if (node.key.compareTo(key) == 0) {
			return node;
		} else if (node.key.compareTo(key) > 0) {
			return search(node.left, key);
		} else {
			return search(node.right, key);
		}
	}

	private Node insert(Node node, Key key, Value value) {
		if (node == null) {
			node = new Node(key, value);
			count++;
			return node;
		}

		if (node.key.compareTo(key) == 0) {
			node.value = value;  //update
		} else if (node.key.compareTo(key) > 0) {
			node.left = insert(node.left, key, value);
		} else {
			node.right = insert(node.right, key, value);
		}
		return node;
	}


	//先序遍历
	private void preOrder(Node node) {
		if (node != null) {
			System.out.println("node->key=" + node.key);
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	//中序遍历
	private void inOrder(Node node) {
		if (node != null) {
			inOrder(node.left);
			System.out.println("node->key=" + node.key);
			inOrder(node.right);
		}
	}

	//后序遍历
	private void postOrder(Node node) {
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			System.out.println("node->key=" + node.key);
		}
	}

	//层序遍历（广度优先遍历）
	private void levelOrder(Node node) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(node);  //尾部
		while (!queue.isEmpty()) {
			Node currentNode = queue.remove();  //头部
			if (currentNode.left != null) {
				queue.add(currentNode.left);
			}
			if (currentNode.right != null) {
				queue.add(currentNode.right);
			}

			System.out.println("node->key=" + currentNode.key);
		}
	}





	public static void main(String[] args) {
		//创建一个数组, [0...n),不重复
		int n = 100000;
		List<Integer> dataList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			dataList.add(i);
		}

		//混洗(按大小顺序插入，会导致二叉树退化成链表)
		Collections. shuffle(dataList);

		BinarySearchTree<String, Integer> bst = new BinarySearchTree();
		for (int i = 0; i < dataList.size(); i++) {
			bst.insert(dataList.get(i).toString(), dataList.get(i));
		}

		for (int i = 0; i < 2 * n; i++) {
			Integer res = bst.search(String.valueOf(i));
			if (i < n) {
				assert res == i;
			} else {
				assert res == null;
			}
		}
	}

	private Node maximum(Node node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}

	private Node minimum(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;

	}

	//利用递归返回值建立爷孙关系。记录最值节点的父节点，因为需要将最值节点可能存在的子节点"接上去"
	//返回值：删除以node为根的二叉树后，新的根节点
	private Node removeMax(Node node) {

		if (node.right == null) {
			Node leftChild = node.left;
			node = null; //删除
			count--;
			return leftChild;
		}
		node.right = removeMax(node.right);
		return node;
	}

	private Node removeMin(Node node) {
		if (node.left == null) {
			Node rightChild = node.right;
			node = null;
			count--;
			return rightChild;
		}
		node.left = removeMin(node.left);
		return node;
	}

	/**
	 *
	 * @param node 根
	 * @param key 待删除节点的键
	 * @return 删除完成后新的根
	 */
	private Node remove(Node node, Key key) {
		if (node == null) {
			return null;
		}
		if (key.compareTo(node.key) < 0) {
			node.left = remove(node.left, key);
			return node;
		} else if (key.compareTo(node.key) > 0) {
			node.right = remove(node.right, key);
			return node;
		} else {
			if (node.left == null) {
				node = node.right;
				count--;
				return node;
			}
			if (node.right == null) {
				node = node.left;
				count--;
				return node;
			}
			//待删除节点node同时存在左右孩子
			//找到node的后继节点s
			Node s = minimum(node.right);
			s.right = removeMin(node.right);
			s.left = node.left;
			count--;
			return s;
		}
	}


}



