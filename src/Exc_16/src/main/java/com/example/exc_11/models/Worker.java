package com.example.exc_11.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "workers")
@Getter
@Setter
public class Worker {


    @JoinColumn(name = "manufacture_id")
    private Long manufacture;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    public Worker() {}
    public Worker(String firstName, String lastName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }
    public Worker(String firstName, String lastName, String middleName, Long manufacture) {
        this.manufacture=manufacture;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }
    public void setId(int id) {
        this.id = id;
    }
}
