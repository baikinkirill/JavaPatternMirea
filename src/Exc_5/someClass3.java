package Exc_5;

public class someClass3 {
    private static someClass3 INSTANCE;
    private someClass3(){
        System.out.println("Hello from class 3");
    }
    public static synchronized someClass3 getINSTANCE(){
        if(INSTANCE==null){
            INSTANCE = new someClass3();
        }
        return INSTANCE;
    }
}
