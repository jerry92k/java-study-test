package study.inheritance;

import org.junit.jupiter.api.Test;

class SchoolTest {

	@Test
	void 생성자에서_호출하는_메서드를_오버라이딩하면_상위객체_생성시에도_오버라이딩된_메서드가_호출된다(){
		HighSchool highSchool = new HighSchool();
	}

}
