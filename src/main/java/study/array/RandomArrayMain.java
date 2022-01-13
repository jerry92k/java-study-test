package study.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomArrayMain {

	private static final Random random = new Random();
	private static final int arraySize = 100_000;
	private static final int strLen = 6;
	private static final int times = 100_000;

	static {
		compareSearchTime();
	}

	public static void main(String[] args) {
		compareSearchTime();
	}

	public static void compareSearchTime() {
		String[] strs = RandomArray.makeStrs(strLen, arraySize);
		List<String> listStrs = Arrays.asList(strs);
		List<String> linkedStrs = new LinkedList<>(Arrays.asList(strs));

		long arrayTotalTime = 0;
		long arrayListTotalTime = 0;
		long linkedListTotalTime = 0;

		for (int i = 0; i < times; i++) {
			String target = strs[random.nextInt(arraySize)];

			long startTime = System.nanoTime();
			int j = 0;
			while (!strs[j].equals(target)) {
				j++;
			}
			arrayTotalTime += System.nanoTime() - startTime;

			startTime = System.nanoTime();
			listStrs.indexOf(target);
			arrayListTotalTime += System.nanoTime() - startTime;

			startTime = System.nanoTime();
			linkedStrs.indexOf(target);
			linkedListTotalTime += System.nanoTime() - startTime;

		}

		System.out.println("Array times : " + (double)arrayTotalTime / times);
		System.out.println("ArrayList times : " + (double)arrayListTotalTime / times);
		System.out.println("LinkedList times : " + (double)linkedListTotalTime / times);
	}
}
