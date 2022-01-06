package study.generic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import study.inheritance.Child;
import study.inheritance.Parent;

class BasketTest {

    @Test
    void Object_클래스를_이용한_사용(){
        Basket basket1 = new Basket("item1");
        Object item1 = basket1.getItem();
        assertThat(item1 instanceof String).isTrue();
        assertThat((String)item1).contains("item1");

        Basket basket2 = new Basket(new Parent());
        Object item2 = basket2.getItem();
        assertThat(item2 instanceof Parent).isTrue();
        Parent parent = (Parent)item2;
        assertThat(parent.printMessage()).contains("parent");

        Basket basket3 = new Basket(new Child());
        Object item3 = basket3.getItem();
        assertThat(item3 instanceof Child).isTrue();
        Child child = (Child)item3;
        assertThat(child.printMessage()).contains("child");
    }

    @Test
    void Object_형변환_오류(){
        Basket basket2 = new Basket(new Parent());
        Object item2 = basket2.getItem();
        assertThat(item2 instanceof Parent).isTrue();
        assertThatThrownBy(()->{
            Child parent = (Child)item2;
        }).isInstanceOf(ClassCastException.class);
    }
}
