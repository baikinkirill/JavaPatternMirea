package Exc_6.Builder;

public class Director {
    public static void main(String[] args) {
        Builder A = new ConcreteBuilder();
        Builder B = new ConcreteBuilder();
        A.buildPart();
        B.buildPart();

        ((ConcreteBuilder) A).getResult();
        ((ConcreteBuilder) B).getResult();
    }
}
