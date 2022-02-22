package Exc_4;

public class main {
    public static void main(String[] args) {
        someClass cl = new someClass(3);

        Thread th = new Thread(() -> {
            System.out.println("Thread " + Thread.currentThread().getName() + " started");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " finished");
        });
        for (int i=0;i<5;i++){
            cl.submit(th);
        }
        cl.shutdown();
    }
}
