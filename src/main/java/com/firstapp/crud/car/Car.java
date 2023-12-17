package com.firstapp.crud.car;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    //    Getters and Setters
    @Id
    @Column (unique = true, nullable = false)
    String registration;
    @Column (nullable = false)
    String brand;
    @Column (nullable = false)
    String model;
    @Column
    Double displacement;
    @Column
    Integer power;
    @Column (nullable = false)
    String color;
    @Column (nullable = false)
    Double price;

    //    toString
    @Override
    public String toString() {
        return "Car{" + "registration=" + registration + ", brand=" + brand + ", model=" + model + ", displacement=" + displacement + ", power=" + power + ", color=" + color + ", price=" + price + '}';
    }
}
