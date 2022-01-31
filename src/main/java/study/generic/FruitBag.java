package study.generic;

import java.util.ArrayList;
import java.util.List;

public class FruitBag<T> {

	private T mainFruit;
	private List<T> fruitList;

	public FruitBag(T fruit) {
		this.mainFruit = fruit;
		this.fruitList = new ArrayList<>();
	}

	public T getMainFruit() {
		return mainFruit;
	}

	public void addFruit(T fruit){
		this.fruitList.add(fruit);
	}

	public void addFruit(List<T> fruits){
		this.fruitList.addAll(fruits);
	}

	public void addFruitWithWild(List<? extends T> fruits){
		this.fruitList.addAll(fruits);
	}

	public List<T> getFruitList() {
		return fruitList;
	}
}
