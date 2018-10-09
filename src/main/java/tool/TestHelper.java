package tool;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by lhy on 2018/10/9.
 */
public class TestHelper {

	//element ~ [rangeR, rangeL]
	public static int[] generateRandomIntArray(int n, int rangeL, int rangeR) {

		assert rangeL <= rangeR;

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			int offset = (int) (Math.random() * (rangeR - rangeL + 1));  //[0, distance + 1)
			arr[i] = rangeL + offset;
		}
		return arr;
	}


}
