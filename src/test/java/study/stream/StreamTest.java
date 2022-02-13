package study.stream;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class StreamTest {

	@Test
	void 스트림_함수에서_예외발생(){

		List<String> strs = Arrays.asList("abc","def","geh","gs");

		assertThatThrownBy(()->strs.stream()
			.map(str -> changeText(str))
			.collect(Collectors.toList()))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void 스트림에서는_리턴해도_메서드리턴이_되지않는다(){

		List<String> strs = Arrays.asList("gs","def","geh","egs");

		strs.stream()
			.forEach(str->{
				if(str.length()<3){
					return ;
				}
				System.out.println(str);
			});
		System.out.println("메서드가 리턴되지 않고 해당 element 작업에 대한 람다 메서드만 리턴된다.\n");
	}

	private String changeText(String text){
		if(text.length()<3){
			throw new IllegalArgumentException();
		}
		return text;
	}
}
