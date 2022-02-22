package Exc_4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class someClass {
    ExecutorService service;
    someClass(int n){
        service = Executors.newFixedThreadPool(n);
    }

    public void shutdown(){
        service.shutdown();
    }

    public void submit(Runnable thread){
        service.submit(thread);
    }
}
