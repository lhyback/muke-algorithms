package ch6;

/**
 * Created by lhy on 2018/11/13.
 */
public class QuickUnion {
	private int[] parent;
	private int size;

	public QuickUnion(int size) {
		this.size = size;
		this.parent = new int[size];
		for (int i = 0; i < size; i++) {
			parent[i] = i;
		}
	}

	public int find(int p) {
		while (parent[p] != p) {
			p = parent[p];
		}
		return p;
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
		parent[pRoot] = qRoot;   //将pRoot嫁接到qRoot，而非嫁接p到q
	}

}
