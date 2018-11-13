package ch6;

/**
 * Created by lhy on 2018/11/13.
 */
public class PathCompress2 {
	private int[] parent;
	private int[] rank;
	private int size;

	public PathCompress2(int size) {
		parent = new int[size];
		rank = new int[size];
		this.size = size;

		for (int i = 0; i < size; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
	}

	public int find(int p) {
		if (p != parent[p]) {
			parent[p] = find(parent[p]);
		}
		return parent[p];
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
		if (rank[pRoot] < rank[qRoot]) {
			parent[pRoot] = qRoot;   //将pRoot嫁接到qRoot，而非嫁接p到q
		} else if (rank[pRoot] > rank[qRoot]){
			parent[qRoot] = pRoot;
		} else {
			parent[pRoot] = qRoot;
			rank[qRoot] += 1;
		}
	}
}
