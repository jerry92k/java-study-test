package study.inheritance;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class InheritanceTest {

    @Test
    void 명시적_형변환해도_객체타입이_변하진_않음() {
        Child child =new Child();
        Parent convertChild = (Parent)child;
        assertThat(convertChild.printMessage()).contains("child");
    }

    @Test
    void 하위클래스는_상위클래스의_instanceof_이기도하다() {
        Child child =new Child();
        Parent parent = new Parent();
        assertThat(child instanceof Parent).isTrue();
        assertThat(parent instanceof Parent).isTrue();
        assertThat(child instanceof Child).isTrue();
    }
}
