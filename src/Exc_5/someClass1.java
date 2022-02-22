package Exc_5;

public class someClass1 {
    private static final someClass1 INSTANCE = new someClass1();
    private someClass1(){
        System.out.println("Hello from class 1");
    }
    public static someClass1 getINSTANCE(){
        return INSTANCE;
    }
}
