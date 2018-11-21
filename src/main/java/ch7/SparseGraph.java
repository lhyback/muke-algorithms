package ch7;

/**
 * Created by lhy on 2018/11/14.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 稀疏图-邻接表
 */
public class SparseGraph implements Graph {
	private int vertexes;  //节点数
	private int edges;  //边数
	private boolean directed;
	public List<Integer>[] g;

	@Override
	public int V() {
		return vertexes;
	}

	@Override
	public int E() {
		return edges;
	}

	@Override
	public void show() {
		for (int i = 0; i < vertexes; i++) {
			System.out.print(i);
			for (int j = 0; j < g[i].size(); j++) {
				System.out.print("->" + g[i].get(j));
			}
			System.out.println();
		}
	}

	//构造函数
	public SparseGraph(int n, boolean directed) {
		this.vertexes = n;
		this.directed = directed;
		edges = 0;
		//Java中不允许非通配符的泛型数组实例化，所以需要强制类型转换
		g = (ArrayList<Integer>[]) new ArrayList[n];
		for (int i = 0; i < n; i++) {
			g[i] = new ArrayList<>();
		}
	}

	public SparseGraph() {}

	//添加边
	public void addEdge(int p, int q) {
		//防止重边和自环
		if (p == q || hasEdge(p, q)) {
			return;
		}
		g[p].add(q);
		if (!directed) {
			g[q].add(p);
		}
		edges++;
	}

	//判断是否含有边
	public boolean hasEdge(int p, int q) {
		for (int i = 0; i < g[p].size(); i++) {
			if (g[p].get(i) == q) {
				return true;
			}
		}
		return false;
	}

	//对外暴露可迭代对象，用于遍历某节点的所有边
	public Iterable<Integer> adj(int v) {
		assert v < vertexes;
		return g[v];
	}
}
