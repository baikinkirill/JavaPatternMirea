package Exc_6.AbstractFactory;

interface AbstractFactory{
    void createProductA();
    void createProductB();
}

public class ConcreteFactory implements AbstractFactory{


    @Override
    public void createProductA() {
        ConcreteProduct a = new ConcreteProduct();
        a.construct();
    }

    @Override
    public void createProductB() {
        ConcreteProduct b = new ConcreteProduct();
        b.construct();
    }
}
