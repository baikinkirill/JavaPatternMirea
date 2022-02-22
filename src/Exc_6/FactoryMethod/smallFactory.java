package Exc_6.FactoryMethod;

public class smallFactory {
    private Product myProduct;
    public  smallFactory (Product myProduct){
        this.myProduct = myProduct;
    }
    public void makeProduct(Product prod){
        myProduct.constructor();
    }

}