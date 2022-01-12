package study.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ArrayTest {

	private static final Random random = new Random();
	private static final int arraySize = 1_000_000;
	private static final int strLen = 6;
	private static final int times = 1_000_000;
	private static String[] strs;

	@BeforeAll
	public static void setUp() {
		strs = RandomArray.makeStrs(strLen, arraySize);
	}

	@Test
	void compareSearchTime() {
		List<String> listStrs = Arrays.asList(strs);
		List<String> linkedStrs = new LinkedList<>(Arrays.asList(strs));

		long arrayTotalTime = 0;
		long arrayListTotalTime = 0;
		long linkedListTotalTime = 0;

		int halfTimes=times/2;

		for (int i = 0; i < times; i++) {
			String target = strs[random.nextInt(arraySize)];

			long startTime = System.nanoTime();
			for(int j=0; j<strs.length; j++){
				if (strs[j].equals(target)) {
					break;
				}
			}
			if(i>halfTimes){
				arrayTotalTime += System.nanoTime() - startTime;
			}


			startTime = System.nanoTime();
			listStrs.indexOf(target);
			if(i>halfTimes){
				arrayListTotalTime += System.nanoTime() - startTime;
			}


			startTime = System.nanoTime();
			linkedStrs.indexOf(target);
			if(i>halfTimes){
				linkedListTotalTime += System.nanoTime() - startTime;
			}
		}

		System.out.println("Array times : "+ (double)arrayTotalTime / halfTimes);
		System.out.println("ArrayList times : "+ (double)arrayListTotalTime / halfTimes);
		System.out.println("LinkedList times : "+ (double)linkedListTotalTime / halfTimes);
	}

	@Test
	void searchInArray() {
		long totalTime = 0;

		for (int i = 0; i < times; i++) {
			String target = strs[random.nextInt(arraySize)];

			long startTime = System.nanoTime();
			for (String str : strs) {
				if (str.equals(target)) {
					break;
				}
			}
			long endTime = System.nanoTime() - startTime;
			totalTime += endTime;
		}
		System.out.println((double)totalTime / times);
	}

	@Test
	void searchInArrayList() {
		List<String> listStrs = Arrays.asList(strs);

		long totalTime = 0;
		for (int i = 0; i < times; i++) {
			String target = strs[random.nextInt(arraySize)];
			long startTime = System.nanoTime();
			listStrs.indexOf(target);
			long endTime = System.nanoTime() - startTime;
			totalTime += endTime;
		}
		System.out.println((double)totalTime / times);
	}

	@Test
	void searchInLinkedList() {
		List<String> listStrs = new LinkedList<>(Arrays.asList(strs));

		long totalTime = 0;
		for (int i = 0; i < times; i++) {
			String target = strs[random.nextInt(arraySize)];
			long startTime = System.nanoTime();
			listStrs.indexOf(target);
			long endTime = System.nanoTime() - startTime;
			totalTime += endTime;
		}
		System.out.println((double)totalTime / times);
	}
}
