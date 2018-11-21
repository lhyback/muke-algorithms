package ch7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lhy on 2018/11/14.
 */
public class DenseGraph implements Graph {
	private int vertexes;
	private int edges;
	private boolean directed;
	public boolean[][] g;

	public DenseGraph(){}

	public DenseGraph(int n, boolean directed) {
		this.vertexes = n;
		this.directed = directed;
		edges = 0;
		g = new boolean[n][n];
	}

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
			for (int j = 0; j < vertexes; j++) {
				if (hasEdge(i, j)) {
					System.out.print("->" + j);
				}
			}
			System.out.println();
		}
	}

	public void addEdge(int p, int q) {
		if (p == q || hasEdge(p, q)) {
			return;
		}
		g[p][q] = true;
		if (!directed) {
			g[q][p] = true;
		}
		edges++;
	}

	public boolean hasEdge(int p, int q) {
		return g[p][q];
	}

	public Iterable<Integer> adj(int v) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < g[v].length; i++) {
			if (g[v][i]) {
				list.add(i);
			}
		}
		return list;
	}
}
