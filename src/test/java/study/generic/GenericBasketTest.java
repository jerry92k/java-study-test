package study.generic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import study.inheritance.Child;
import study.inheritance.Parent;

class GenericBasketTest {

    @Test
    void Object_클래스를_이용한_사용(){
        GenericBasket<String> basket1 = new GenericBasket("item1");
        String item1 = basket1.getItem();
        assertThat(item1 instanceof String).isTrue();
        assertThat(item1).contains("item1");

        GenericBasket<Parent> basket2 = new GenericBasket(new Parent());
        Parent item2 = basket2.getItem();
        assertThat(item2 instanceof Parent).isTrue();
        assertThat(item2.printMessage()).contains("parent");

        GenericBasket<Child> basket3 = new GenericBasket(new Child());
        Child item3 = basket3.getItem();
        assertThat(item3 instanceof Child).isTrue();
        assertThat(item3.printMessage()).contains("child");
    }

}
