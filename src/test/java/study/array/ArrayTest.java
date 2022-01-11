package study.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ArrayTest {

	@Test
	void searchInArray(){
		String[] strs= {"a","b","c","d","e","f","g","h","i","j","k","l"};
		long startTime = System.nanoTime();
		for(String str: strs){
			if(str.equals("l")){
				long endTime = System.nanoTime() - startTime;
				System.out.println("times : "+endTime);
				break;
			}
		}
	}

	@Test
	void searchInArrayList(){
		List<String> strs = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l");
		long startTime = System.nanoTime();
		for(String str: strs){
			if(str.equals("l")){
				long endTime = System.nanoTime() - startTime;
				System.out.println("times : "+endTime);
				break;
			}
		}
	}

	@Test
	void searchInLinkedList(){
		List<String> strs=new LinkedList<>(Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l"));
		long startTime = System.nanoTime();
		for(String str: strs){
			if(str.equals("l")){
				long endTime = System.nanoTime() - startTime;
				System.out.println("times : "+endTime);
				break;
			}
		}
	}
}
