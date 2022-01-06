package study.polymorphism;

public class GenericBasket<T>{
    private T item;

    public GenericBasket(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}
