package study;

import java.util.Random;

public class RandomArray {

	private static Random random = new Random();
	public static String[] makeStrs(int strLen, int arraySize){

		String[] strs= new String[arraySize];
		for(int i=0; i<arraySize; i++){
			strs[i] =makeWord(strLen);
		}
		return strs;
	}

	private static String makeWord(int strLen) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<strLen; i++){
			sb.append((char) (random.nextInt(26) + 97));
		}
		return sb.toString();
	}
}
