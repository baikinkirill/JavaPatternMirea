package Exc_5;

public class someClass2 {
    private static someClass2 INSTANCE = new someClass2();
    private someClass2(){
        System.out.println("Hello from class 2");
    }
    public static someClass2 getINSTANCE(){
        if(INSTANCE==null){
            INSTANCE = new someClass2();
        }
        return INSTANCE;
    }
}
