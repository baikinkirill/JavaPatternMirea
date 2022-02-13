package Exc_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClassOnList {
    private static final Lock lock = new ReentrantLock();
    ClassOnList(){
        List<String> list = new ArrayList<String>();
        new Thread(new threadClass2(list, lock, "Поток 1")).start();
        new Thread(new threadClass2(list, lock, "Поток 2")).start();
        new Thread(new threadClass2(list, lock, "Поток 3")).start();
    }
}

class threadClass2 implements Runnable{
    List<String> list;
    Lock lock;
    String name;

    public threadClass2(List<String> list, Lock lock, String name) {
        this.list = list;
        this.lock = lock;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " ожидает разрешение");
        lock.lock();
        System.out.println(name + " работает");
        for (int i = 0; i < 15; i++) {
            list.add(i+"");
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " освобождает");
        lock.unlock();
    }
}