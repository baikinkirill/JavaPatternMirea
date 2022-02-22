package Exc_6.AbstractFactory;

public class Client {
    public static void main(String[] args){
        AbstractFactory factory = new ConcreteFactory();
        factory.createProductA();
        factory.createProductB();
    }
}
