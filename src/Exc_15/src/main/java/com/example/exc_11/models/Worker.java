package com.example.exc_11.models;

import javax.persistence.*;

@Entity
@Table(name = "workers")
public class Worker {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Manufacture manufacture;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;


    public int getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Worker() {

    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Manufacture getManufacture() {
        return manufacture;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Worker(String firstName, String lastName, String middleName) {
        this.manufacture = manufacture;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public void setId(int id) {
        this.id = id;
    }


}
