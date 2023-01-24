package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "phone")
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id_phone")
    private int idPhone;
    @Column(name="number",length = 8)
    private int number;
    @Column(name="citycode",length = 3)
    private int citycode;
    @Column(name="contrycode",length = 3)
    private int contrycode;

    @Override
    public String toString() {
        return "Phone{" +
                "number=" + number +
                ", citycode=" + citycode +
                ", contrycode=" + contrycode +
                '}';
    }
}
