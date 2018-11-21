package ch7.dfs;

import ch7.Graph;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by lhy on 2018/11/19.
 */
public class Component {
	private Graph graph;
	private int ccount;  //连通分量数目
	private boolean[] visited;
	private int[] id;

	//求图的连通分量，逻辑放在构造函数里
	public Component(Graph graph) {
		this.graph = graph;
		ccount = 0;
		visited = new boolean[graph.V()];
		id = new int[graph.V()];
		Arrays.fill(visited, false);
		Arrays.fill(id, -1);

		for (int i = 0; i < graph.V(); i++) {
			if (!visited[i]) {
				dfs(i);
				ccount++;
			}
			//ccount++;
		}

	}

	//dfs
	public void dfs(int v) {
		visited[v] = true;
		id[v] = ccount;

		Iterator<Integer> it = graph.adj(v).iterator();
		while (it.hasNext()) {
			int next = it.next();
			//对邻接点递归调用dfs(体现了深度优先)
			if (!visited[next]) {
				dfs(next);
			}
		}

	}

	//图的连通分量个数
	public int getComponents() {
		return ccount;
	}

	//查询节点连通性
	public boolean isConnected(int p, int q) {
		return id[p] == id[q];
	}

}
