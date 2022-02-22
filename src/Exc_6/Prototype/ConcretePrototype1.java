package Exc_6.Prototype;

public class ConcretePrototype1 implements Prorotype{
    String name;
    int weight;
    int size;
    String color;

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public ConcretePrototype1(String name, int weight, int size, String color) {
        this.name = name;
        this.weight = weight;
        this.size = size;
        this.color = color;
    }

    @Override
    public Object copy() {
        ConcretePrototype1 copy = new ConcretePrototype1(name, weight, size, color);
        return copy;
    }
}
