package com.example.exc_11.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "manufactures")
public class Manufacture {

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String name;
    private String address;

    @OneToMany(mappedBy = "manufacture", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Worker> workers;

    public Manufacture() {
    }

    public Manufacture(String name, String address) {
        this.name = name;
        this.address = address;
        workers = new ArrayList<Worker>();
    }

    public void addWorker(Worker worker) {
        worker.setManufacture(this);
        workers.add(worker);
    }

    public void removeWorker(Worker worker) {
        workers.remove(worker);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address=address;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    @Override
    public String toString() {
        return "models.Manufacture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
