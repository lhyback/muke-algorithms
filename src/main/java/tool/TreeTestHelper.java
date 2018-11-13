package tool;

import ch5.BinarySearchTree;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * Created by lhy on 2018/11/7.
 */
public class TreeTestHelper {

	private BinarySearchTree<Integer, String> bst = getBST();

	public BinarySearchTree getBST() {
		if (bst == null) {
			bst = new BinarySearchTree<>();
			bst.insert(41, "node");
			bst.insert(22, "node");
			bst.insert(60, "node");
			bst.insert(15, "node");
			bst.insert(33, "node");
			bst.insert(59, "node");
			bst.insert(63, "node");
			bst.insert(62, "node");
		}
		return bst;
	}

	@Test
	public void testSearchFromText() {
		List<String> words = FileOperations.readWords("bible.txt");
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
		for (String word : words) {
			if (bst.contain(word)) {
				int value = bst.search(word);
				bst.insert(word, value + 1);
			} else {
				bst.insert(word, 1);
			}
		}

		//查询god出现次数
		Integer fequency = bst.search("god");
		System.out.println("\"god\" in bible appear times: " + fequency);
	}

	@Test
	public void testOrderVisit() {

		System.out.println("pre:");
		bst.preOrder();
		System.out.println("in:");
		bst.inOrder();
		System.out.println("post:");
		bst.postOrder();
		System.out.println("level:");
		bst.levelOrder();
	}

	@Test
	public void testDeleteMaximum() {
		//bst.insert();
		bst.removeMax();
		bst.levelOrder();
	}

	@Test
	public void testDeleteByKey() {

		bst.remove(60);
		bst.levelOrder();
	}
}
