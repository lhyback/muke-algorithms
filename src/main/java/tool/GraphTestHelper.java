package tool;

import ch7.bfs.ShortestPath;
import ch7.dfs.Component;
import ch7.DenseGraph;
import ch7.Graph;
import ch7.SparseGraph;
import ch7.dfs.Path;
import org.junit.Test;

import java.io.File;
import java.util.*;

/**
 * Created by lhy on 2018/11/14.
 */
public class GraphTestHelper {
	@Test
	public void testGraphStorage() {
		int n = 20;
		int m = 30;  //控制稀疏度
		DenseGraph denseGraph = new DenseGraph(n, false);
		SparseGraph sparseGraph = new SparseGraph(n, false);

		for (int i = 0; i < m; i++) {
			int p = (int) (Math.random() * n);
			int q = (int) (Math.random() * n);
			denseGraph.addEdge(p, q);
			sparseGraph.addEdge(p, q);
		}

		//邻接表-遍历 边 O(e)
		System.out.println("稀疏图-邻接表");
		sparseGraph.show();
		System.out.println();

		//邻接矩阵-遍历边 O(v^2)
		System.out.println("稠密图-邻接矩阵");
		denseGraph.show();
	}

	@Test
	public void testReadGraphFromTxt() throws Exception {
		SparseGraph sparseGraph;
		sparseGraph = (SparseGraph) FileOperations.readGraph("testG1.txt", false);
		sparseGraph.show();
	}

	@Test
	public void testComponents() throws Exception {
		//Graph graph = FileOperations.readGraph("testG1.txt", false);
		Graph graph = FileOperations.readGraph("testG1.txt", true);
		Component component = new Component(graph);
		graph.show();
		System.out.println("component counts: " + component.getComponents());
	}


	@Test
	public void testPath() throws Exception {
		Graph graph = FileOperations.readGraph("testG.txt", false);
		Path path = new Path(graph, 0);
		path.showPath(4);
	}

	@Test
	public void testShortestPath() throws Exception {
		Graph graph = FileOperations.readGraph("testG.txt", false);
		ShortestPath path = new ShortestPath(graph, 0);
		path.showPath(4);
		System.out.println();
		System.out.println(path.getDistance(6));
	}


	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(13);
		list.add(11);
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
	}
}
