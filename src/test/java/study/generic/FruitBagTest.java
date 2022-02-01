package study.generic;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class FruitBagTest {

	@Test
	void 제네릭_생성() {
		Apple apple = new Apple("풋사과");
		FruitBag<Apple> appleFruitBag = new FruitBag<>(apple);
		assertThat(appleFruitBag.getMainFruit()).isEqualTo(apple);
	}

	@Test
	void 상위클래스로_제네릭_생성후_하위클래스_타입_객체_넣기() {
		Fruit apple = new Apple("풋사과");
		FruitBag<Fruit> appleFruitBag = new FruitBag<>(apple);
		assertThat(appleFruitBag.getMainFruit()).isEqualTo(apple);
	}

	@Test
	void list_형식_제네릭_생성() {
		FruitBag<Fruit> appleFruitBag = new FruitBag<>(new Apple("풋사과"));
		List<Fruit> fruits = Arrays.asList(new Fruit("과일1"), new Apple("금사과"), new Banana("몽키바나나"));
		appleFruitBag.addFruit(fruits);

		assertThat(appleFruitBag.getFruitList().size()).isEqualTo(fruits.size());
		assertThat(appleFruitBag.getFruitList()).containsAll(fruits);
	}

	@Test
	void list의_경우_일반_제네릭은_하위타입이_상위타입을_대신하지못한다() {
		FruitBag<Fruit> appleFruitBag = new FruitBag<>(new Apple("풋사과"));
		List<Apple> fruits = Arrays.asList(new Apple("과일1"), new Apple("금사과"), new Apple("몽키바나나"));

		// addFruit 메서드는 FruitBag에 선언한 T 타입과 동일한 타입만 허용함. A가 B를 상속한다고 하여서 List<A>가 List<B>를 상속하는 것은 아니기 때문에
		// 대체할 수 없고, 형변환해도 사용할 수 없음. 다음 테스트 처럼 사용해야함.
		// 리스트의 불공변성.
		assertThatThrownBy(()->appleFruitBag.addFruit((Fruit)fruits)).isInstanceOf(ClassCastException.class);
	}

	@Test
	void list의_경우_한정적_와일드카드를_사용하면_하위타입이_호환된다() {
		FruitBag<Fruit> appleFruitBag = new FruitBag<>(new Apple("풋사과"));
		List<Apple> fruits = Arrays.asList(new Apple("과일1"), new Apple("금사과"), new Apple("몽키바나나"));
		appleFruitBag.addFruitWithWild(fruits);

		assertThat(appleFruitBag.getFruitList().size()).isEqualTo(fruits.size());
		assertThat(appleFruitBag.getFruitList()).containsAll(fruits);
	}

	@Test
	void list의_경우_raw_타입으로_선언하면_문제없이_컴파일되고_실행된다_그래서_런타임시_문제가_발생한다() {
		FruitBag<Fruit> appleFruitBag = new FruitBag<>(new Apple("풋사과"));
		List fruits = Arrays.asList(new Basket("이상한상자"), new Apple("과일1"), new Apple("금사과"), new Apple("몽키바나나"));
		appleFruitBag.addFruitWithWild(fruits);
		List<Fruit> fruitList = appleFruitBag.getFruitList();
		assertThatThrownBy(()->fruitList.stream()
			.map(Fruit::getName)).isInstanceOf(ClassCastException.class);
	}

	@Test
	void 배열은_공변성을_가져서_런타임에_문제가_발생할_수_있다(){
		Object[] objects = new Long[1]; // 공변성으로 Long -> Object 상속하니 Long[] -> Object[] 상속도 성립하게되어 컴파일 오류 안남.
		assertThatThrownBy(()->objects[0] = "런타임에 오류난다.").isInstanceOf(ArrayStoreException.class);
	}

	@Test
	void 제네릭_리스트와_가변인수로_받아서_Object배열로_참조하면_런타임_오류에_취약하다(){
		List<Fruit> fruits1 = Arrays.asList(new Fruit("과일1"), new Apple("금사과"), new Banana("몽키바나나"));
		List<Fruit> fruits2 = Arrays.asList(new Fruit("과일2"), new Apple("금사과2"), new Banana("몽키바나나2"));
		List<Fruit> fruits3 = Arrays.asList(new Fruit("과일3"), new Apple("금사과3"), new Banana("몽키바나나3"));

		FruitBag<Fruit> appleFruitBag = new FruitBag<>(new Apple("풋사과"));

		assertThatThrownBy(()->appleFruitBag.addFruitListFail(fruits1,fruits2,fruits3)).isInstanceOf(ArrayStoreException.class);
		/*
		public void addFruitListFail(List<? extends T>... fruits){
		Object[] objects = fruits;
		objects[0]="런타임 오류 발생";
		 */
	}

	@Test
	void 제네릭_리스트와_가변인수로_사용할땐_타입을_명시해주면_된다(){
		List<Fruit> fruits1 = Arrays.asList(new Fruit("과일1"), new Apple("금사과"), new Banana("몽키바나나"));
		List<Fruit> fruits2 = Arrays.asList(new Fruit("과일2"), new Apple("금사과2"), new Banana("몽키바나나2"));
		List<Fruit> fruits3 = Arrays.asList(new Fruit("과일3"), new Apple("금사과3"), new Banana("몽키바나나3"));

		FruitBag<Fruit> appleFruitBag = new FruitBag<>(new Apple("풋사과"));
		appleFruitBag.addFruitListSuccess(fruits1,fruits2,fruits3);
		assertThat(appleFruitBag.getFruitList().size()).isEqualTo(fruits1.size()+fruits2.size()+fruits3.size());
		/*
		public void addFruitListSuccess(List<? extends T>... fruits){
		for(List<? extends T> list : fruits){
			fruitList.addAll(list);
		}
		 */
	}

}
