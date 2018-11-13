package ch6;

/**
 * Created by lhy on 2018/11/13.
 */
public class QuickFind {

	private int[] id;
	private int size;

	public QuickFind(int size) {
		this.size = size;
		id = new int[size];
		for (int i = 0; i < size; i++) {
			id[i] = i;
		}
	}
	/**
	 * @param p 元素p
	 * @return  p的根节点
	 */
	public int find(int p) {
		assert p > 0 && p < size;
		return id[p];
	}

	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}

	public void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		if (pRoot == qRoot) {
			return;
		}

		for (int i = 0; i < size; i++) {
			if (id[i] == pRoot) {
				id[i] = qRoot;
			}
		}
	}
}
