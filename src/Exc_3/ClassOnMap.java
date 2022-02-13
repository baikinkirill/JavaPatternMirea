package Exc_3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

class ClassOnMap{
    ClassOnMap(){
        Semaphore sem = new Semaphore(1); // 1 разрешение
        Map<Integer, String> map = new HashMap<>();
        new Thread(new threadClass(map, sem, "Поток 1")).start();
        new Thread(new threadClass(map, sem, "Поток 2")).start();
        new Thread(new threadClass(map, sem, "Поток 3")).start();
    }
}

class threadClass implements Runnable{
    Map<Integer, String> map;
    Semaphore sem;
    String name;

    public threadClass(Map<Integer, String> map, Semaphore sem, String name) {
        this.map = map;
        this.sem = sem;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " ожидает разрешение");
            sem.acquire();
            System.out.println(name + " работает");
            for (int i = 0; i < 15; i++) {
                map.put(i, i + " - индекс");
                Thread.sleep(150);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " освобождает");
        sem.release();


    }
}
