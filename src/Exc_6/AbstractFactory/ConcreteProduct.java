package Exc_6.AbstractFactory;

interface AbstractProduct{
    void makeProduct();
    void useProduct();
    void brokeProduct();
}

public class ConcreteProduct implements AbstractProduct{
    public void construct(){

    }

    @Override
    public void makeProduct() {
        System.out.println("Продукт сделан");
    }

    @Override
    public void useProduct() {
        System.out.println("Продукт использован");
    }

    @Override
    public void brokeProduct() {
        System.out.println("Продукт сломан");
    }
}
