package ch6;

/**
 * Created by lhy on 2018/11/13.
 */
public class QuickUnionSize {
	private int[] parent;
	private int[] sz;
	private int size;

	public QuickUnionSize(int size) {
		parent = new int[size];
		sz = new int[size];
		this.size = size;

		for (int i = 0; i < size; i++) {
			parent[i] = i;
			sz[i] = 1;
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
		if (sz[pRoot] < sz[qRoot]) {
			parent[pRoot] = qRoot;   //将pRoot嫁接到qRoot，而非嫁接p到q
			sz[qRoot] += sz[pRoot];
		} else {
			parent[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
		}
	}
}
