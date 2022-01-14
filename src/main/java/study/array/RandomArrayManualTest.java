package study.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomArrayManualTest {

	private static final Random random = new Random();
	private static final int arraySize = 100_000; //배열의 길이
	private static final int strLen = 6; // 한 문자열의 길이
	private static final int times = 100_000; // 수행 횟수
	private String[] strs;
	private List<String> listStrs;
	private List<String> linkedStrs;

	private RandomArrayManualTest(String[] strs, List<String> listStrs, List<String> linkedStrs) {
		this.strs = strs;
		this.listStrs = listStrs;
		this.linkedStrs = linkedStrs;
	}

	public static RandomArrayManualTest makeRandomArrayTest() {
		String[] strs = RandomArray.makeStrs(strLen, arraySize);
		return new RandomArrayManualTest(strs, //배열의 길이와 문자열 길이를 입력받아, 랜덤한 문자열 배열 생성
			// ArrayList 객체로 복사 (new ArrayList 로 생성하지 않을 경우 copy하지 않고 참조만 하여 Array가 캐시 해놓은 것을 쓰기 때문에 성능비교가 제대로 안됨.
			new ArrayList<>(Arrays.asList(strs)),
			new LinkedList<>(Arrays.asList(strs))); // LinkedList 객체로 복사
	}

	public static void main(String[] args) {
		RandomArrayManualTest randomArrayManualTest = RandomArrayManualTest.makeRandomArrayTest();

		System.out.println("warm-up start --");
		for (int i = 0; i < 3; i++) {
			randomArrayManualTest.compareSearchTime();
			System.out.println();
		}
		System.out.println("warm-up finish --\n");

		System.out.println("measure start --");
		randomArrayManualTest.compareSearchTime();
		System.out.println("measure finish --");
	}

	public void compareSearchTime() {
		long arrayTotalTime = 0; // Array 탐색 시간 측정용
		long arrayListTotalTime = 0; // ArrayList 탐색 시간 측정용
		long linkedListTotalTime = 0; // LinkedList 탐색 시간 측정용

		for (int i = 0; i < times; i++) { // times 만큼 시도하고, times로 나눠 평균 시간을 측정
			// 임의의 문자열을 하나 pick. 동일한 조건으로 테스트 하기 위해 ArrayList와 LinkedList도 동일한 target을 사용한다.
			String target = strs[random.nextInt(arraySize)];

			arrayTotalTime += measureSearchInArray(target);  // Array 탐색 시간 측정

			arrayListTotalTime += measureSearchInArrayList(target); // ArrayList 탐색 시간

			linkedListTotalTime += measureSearchInLinkedList(target); // LinkedList 탐색 시간
		}

		// 각각의 평균값 계산
		System.out.println("Array times : " + (double)arrayTotalTime / times);
		System.out.println("ArrayList times : " + (double)arrayListTotalTime / times);
		System.out.println("LinkedList times : " + (double)linkedListTotalTime / times);
	}

	private long measureSearchInArray(String target) {
		long startTime = System.nanoTime();
		int j = 0;
		while (!strs[j].equals(target)) {
			j++;
		}
		return System.nanoTime() - startTime;
	}

	private long measureSearchInArrayList(String target) {
		long startTime = System.nanoTime();
		int j = 0;
		while (!listStrs.get(j).equals(target)) {
			j++;
		}
		return System.nanoTime() - startTime;
	}

	private long measureSearchInLinkedList(String target) {
		long startTime = System.nanoTime();
		linkedStrs.indexOf(target);
		return System.nanoTime() - startTime;
	}
}
