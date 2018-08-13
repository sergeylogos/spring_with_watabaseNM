package ua.com.owu.controllers.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "Polzovatel")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "imya", nullable = false)
    private String username;


    private String avatar;


    public User() {
    }

    public User(String username) {
        this.username = username;
    }
}
