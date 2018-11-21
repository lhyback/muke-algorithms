package ch7;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by lhy on 2018/11/15.
 */
public interface Graph {
	int V();
	int E();
	boolean hasEdge(int p, int q);
	void addEdge(int p, int q);
	void show();
	Iterable adj(int v);

}
