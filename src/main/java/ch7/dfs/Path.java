package ch7.dfs;

import ch7.Graph;

import java.util.*;

/**
 * Created by lhy on 2018/11/19.
 */
public class Path {
	private Graph graph;
	private boolean[] visited;
	private int[] from;  //dfs节点的前驱节点
	private int source;  //源节点

	public Path(Graph graph, int source) {
		this.graph = graph;
		this.source = source;
		visited = new boolean[graph.V()];
		from = new int[graph.V()];
		Arrays.fill(visited, false);
		Arrays.fill(from, -1);

		dfs(source);
	}

	public boolean hasPath(int w) {
		return visited[w];
	}

	public List<Integer> getPath(int w) {
		List<Integer> path = null;
		if (hasPath(w)) {
			path = new ArrayList<>();
			LinkedList<Integer> tmp = new LinkedList<>();
			while (w != source) {
				tmp.addFirst(w);
				w = from[w];
			}
			while (!tmp.isEmpty()) {
				path.add(tmp.removeFirst());
			}
		}
		return path;
	}

	public void showPath(int w) {
		System.out.print(source);
		Iterator<Integer> it = getPath(w).iterator();
		while (it.hasNext()) {
			System.out.print("->" + it.next());
		}
		System.out.println();
	}

	//对未被访问的节点v 进行深度优先遍历
	private void dfs(int v) {
		visited[v] = true;
		Iterator<Integer> it = graph.adj(v).iterator();  //dfs也要用到邻接点列表
		while(it.hasNext()) {
			int x = it.next();
			if (!visited[x]) {
				from[x] = v;
				dfs(x);
			}
		}
	}


}
