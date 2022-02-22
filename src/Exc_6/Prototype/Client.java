package Exc_6.Prototype;

public class Client implements Prorotype {
    int age;
    String name;

    @Override
    public String toString() {
        return "Client{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public Client(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public Object copy() {
        Client copy = new Client(age, name);
        return copy;
    }
}
