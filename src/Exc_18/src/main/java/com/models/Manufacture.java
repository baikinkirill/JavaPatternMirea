package com.models;

import com.Backupable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "manufactures")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Manufacture implements Backupable {
    @Id
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Override
    public String toString() {
        return "User{" +
                "\n\tid=" + id +
                ", \n\tname='" + name + '\'' +
                ", \n\taddress='" + address + '\'' +
                "\n}";
    }
}
