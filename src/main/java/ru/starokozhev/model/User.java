package ru.starokozhev.model;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (name = "name")
    private String name;
    @Column (name = "email")
    private String email;
    @Column (name = "password")
    private String password;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public User(){}

    public User(Integer id, String name, String email, String password, City city) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
    }
    //public User(String name, String email, String password)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
