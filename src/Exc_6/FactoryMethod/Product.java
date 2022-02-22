package Exc_6.FactoryMethod;

interface ProductMethods{
    void makeProduct();
    void useProduct();
    void brokeProduct();
}

public class Product implements ProductMethods{
    public void constructor(){
        makeProduct();
        useProduct();
        brokeProduct();
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
