package ch6;

/**
 * Created by lhy on 2018/11/13.
 */
public class PathCompress1 {
	private int[] parent;
	private int[] rank;
	private int size;

	public PathCompress1(int size) {
		parent = new int[size];
		rank = new int[size];
		this.size = size;

		for (int i = 0; i < size; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
	}

	//两步一跳，路径压缩
	public int find(int p) {
		while (parent[p] != p) {
			parent[p] = parent[parent[p]];
			p= parent[p];
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
