package study.serializable;

import java.io.Serializable;
import java.util.Objects;

public class RiceCake implements Serializable {

	private String color;
	private int calorie;
	private int price;

	public RiceCake(String color, int calorie, int price) {
		this.color = color;
		this.calorie = calorie;
		this.price = price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		RiceCake riceCake = (RiceCake)o;
		return calorie == riceCake.calorie && price == riceCake.price && Objects.equals(color, riceCake.color);
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, calorie, price);
	}

	@Override
	public String toString() {
		return "RiceCake{" +
			"color='" + color + '\'' +
			", calorie=" + calorie +
			", price=" + price +
			'}';
	}
}
