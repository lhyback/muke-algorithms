package ch7.bfs;

import ch7.Graph;
import ch7.dfs.Path;

import java.util.*;

/**
 * Created by lhy on 2018/11/19.
 */
public class ShortestPath {

	private Graph graph;
	private int source;
	private boolean[] visited;
	private int[] from;
	private int[] order;

	public ShortestPath(Graph graph, int source) {
		this.graph = graph;
		this.source = source;
		this.visited = new boolean[graph.V()];
		this.from = new int[graph.V()];
		this.order = new int[graph.V()];
		Arrays.fill(visited, false);
		Arrays.fill(from, -1);
		Arrays.fill(order, -1);

		bfs(source);
	}

	//从v开始广度优先遍历（和树的层序遍历不一样，是让visited过的元素入队。为了求from和order）
	//入队的都是被访问过的元素
	public void bfs(int v) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(v);
		//将根节点的访问放在循环体外，循环体内是访问邻接点，意义是便于from和order数组赋值
		order[v] = 0;
		visited[v] = true;
		//入队
		while (!queue.isEmpty()) {
			//队首元素e，是一个已经被访问过的元素
			int e = queue.remove();
			Iterator<Integer> it = graph.adj(e).iterator();
			while (it.hasNext()) {
				//访问出队元素e的邻接点x
				int x = it.next();
				if (!visited[x]) {
					visited[x] = true;
					from[x] = e;
					order[x] = order[e] + 1;
					queue.add(x);
				}
			}
		}
	}

	public boolean hasPath(int w) {
		return visited[w];
	}

	public List<Integer> getShortestPath(int w) {
		List<Integer> path = null;
		if (hasPath(w)) {
			path = new ArrayList<>();
			LinkedList<Integer> tmp = new LinkedList<>();
			while (w != source) {
				//等价于tmp.addFirst(w);   LinkdeList实现了Deque（定义了push和pop方法）
				tmp.push(w);
				w = from[w];
			}
			while (!tmp.isEmpty()) {
				path.add(tmp.pop());
			}
		}
		return path;
	}

	public int getDistance(int w) {
		return order[w];
	}

	public void showPath(int w) {
		List<Integer> path = getShortestPath(w);
		System.out.print(source);
		for (Integer x : path) {
			System.out.print("->" + x);
		}
	}

}
