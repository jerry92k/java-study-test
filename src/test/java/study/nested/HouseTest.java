package study.nested;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HouseTest {

	@Test
	void 중첩클래스_외부에서는_내부의_private도_접근할수_있다(){
		int roomNo = 10;
		House house = new House(roomNo);
		Assertions.assertThat(house.getRoomNo()).isEqualTo(roomNo);
	}
}
