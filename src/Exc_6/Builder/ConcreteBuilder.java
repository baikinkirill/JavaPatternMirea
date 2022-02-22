package Exc_6.Builder;

interface Builder {
    void buildPart();
}

public class ConcreteBuilder implements Builder {
    private String name = "";
    private int price;
    private int age;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void buildPart() {
        System.out.println("Part was build");
    }

    String getResult() {
        return "nice";
    }
}
