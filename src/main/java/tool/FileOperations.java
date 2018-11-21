package tool;

import ch7.DenseGraph;
import ch7.Graph;
import ch7.SparseGraph;

import java.io.*;
import java.util.*;

/**
 * Created by lhy on 2018/11/7.
 */
public class FileOperations {

	private static Scanner scanner;
	/**
	 * 将英文文本(类加载路径下)以单词为单位读取到一个List中
	 * @param fileName
	 * @return  英文单词列表
	 */
	public static List<String> readWords(String fileName) {
		List<String> words = new ArrayList<>();
		try {
			String path = FileOperations.class.getResource("/").getPath();
			FileInputStream inputStream = new FileInputStream(path + fileName);
			scanner = new Scanner(inputStream);
			scanner.useDelimiter("\\A");  //以行首（\A）作分界符
			//scanner.useLocale(Locale.ENGLISH);

			while (scanner.hasNextLine()) {
				String content = scanner.nextLine();
				//从0开始，第一个字母的位置
				int start = findFirstLetterPos(content, 0);
				for (int i = start + 1; i <= content.length(); i++) {
					if (i == content.length() || !Character.isLetter(content.charAt(i))) {
						String word = content.substring(start, i);
						words.add(word.toLowerCase());
						start = findFirstLetterPos(content, i);
						i = start + 1;
					} else {
						i++;
					}
				}
			}
			return words;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Graph readGraph(String fileName, boolean densed) throws Exception {
		fileName = FileOperations.class.getResource("/").getPath() + fileName;
		//DataInputStream input = new DataInputStream();
		//使用缓冲流，减少对磁盘的访问次数
		scanner = new Scanner(new BufferedInputStream(new FileInputStream(fileName)));
		int v = scanner.nextInt();
		int e = scanner.nextInt();
		Graph graph;
		if (densed) {
			graph = new DenseGraph(v, false);
		} else {
			graph = new SparseGraph(v, false);
		}
		while (e > 0) {
			int i = scanner.nextInt();
			int j = scanner.nextInt();
			graph.addEdge(i, j);
			e--;
		}
		return graph;
	}

	public static int findFirstLetterPos(String content, int begin) {
		while (begin < content.length() && !Character.isLetter(content.charAt(begin))) {
			begin++;
		}
		return begin;
	}

	public static void main(String[] args) {

		String classLoadRootPath = FileOperations.class.getResource("/").getPath();
		System.out.println("类加载的根路径：" + classLoadRootPath);

		String classLoadCurrentPath = FileOperations.class.getResource("").getPath();
		System.out.println("当前类的加载目录：" + classLoadCurrentPath);


		List<String> words = readWords("bible.txt");
		if (words != null) {
			System.out.println(words.size());
		} else {
			System.out.println("Error：500");
		}

		try {
			DenseGraph denseGraph = new DenseGraph();
			SparseGraph sparseGraph = new SparseGraph();
			denseGraph = (DenseGraph) readGraph("testG1.txt", true);
			sparseGraph = (SparseGraph) readGraph("testG2.txt", false);
			denseGraph.show();
			System.out.println();
			sparseGraph.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
