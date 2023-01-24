package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class User {
    @Id
    @Column(name= "id_usuario")
    private String id;
    @Column(name= "name")
    private String name;

    @Column(name= "email")
    private String email;

    @Column(name= "password")
    private String password;

    @JsonIgnoreProperties
    @OneToMany(cascade= CascadeType.ALL)
    private List<Phone> phones;

    @Column(name= "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name= "modified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    @Column(name= "last_login", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @Column(name= "token", nullable = true)
    private String token;

    @Column(name= "isactive")
    private boolean isActive;

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phones=" + phones +
                ", created=" + created +
                ", modified=" + modified +
                ", last_login=" + lastLogin +
                ", token='" + token + '\'' +
                ", isactive=" + isActive +
                '}';
    }
}
