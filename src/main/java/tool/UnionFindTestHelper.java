package tool;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by lhy on 2018/11/13.
 */
public class UnionFindTestHelper {
	public static void testUnionFindPerformance(String classname, int size) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Class clazz = Class.forName(classname);
		//int find(int p)
		Method isConnectedMethod = clazz.getMethod("isConnected", int.class, int.class);
		//void union(int p, int q)
		Method unionMethod = clazz.getMethod("union", int.class, int.class);
		//Object learn = clazz.newInstance(size);
		Constructor constructor = clazz.getConstructor(int.class);
		Object test = constructor.newInstance(size);

		long start = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			int p = (int) (Math.random() * size);
			int q = (int) (Math.random() * size);
			unionMethod.invoke(test, p, q);
		}
		for (int i = 0; i < size; i++) {
			int p = (int) (Math.random() * size);
			int q = (int) (Math.random() * size);
			isConnectedMethod.invoke(test, p, q);
		}

		long end = System.currentTimeMillis();
		System.out.printf("time used: %.3f s\n", ((float) (end - start)) / 1000);

	}

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		int n = 100000;
		testUnionFindPerformance("ch6.QuickFind", n);
		testUnionFindPerformance("ch6.QuickUnion", n);
		testUnionFindPerformance("ch6.QuickUnionSize", n);
		testUnionFindPerformance("ch6.QuickUnionRank", n);
		testUnionFindPerformance("ch6.PathCompress1", n);
		testUnionFindPerformance("ch6.PathCompress2", n);

		System.out.println("context: ");
		testUnionFindPerformance("ch6.UnionFind1", n);
		testUnionFindPerformance("ch6.UnionFind2", n);
		testUnionFindPerformance("ch6.UnionFind3", n);
//		testUnionFindPerformance("ch6.UnionFind4", n);
//		testUnionFindPerformance("ch6.UnionFind5", n);
//		testUnionFindPerformance("ch6.UnionFind6", n);

	}
}
