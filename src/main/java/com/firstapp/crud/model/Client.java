package com.firstapp.crud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @Column(unique = true, nullable = false)
    Integer id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String surname;

    @Column(nullable = false, unique = true)
    String email;

    @Column(name = "birth_date", nullable = false)
    LocalDateTime birthDate;

    @Column(nullable = false)
    Long phone;

    @Column(nullable = true)
    String address;

// toString
    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", birthDate=" + birthDate + ", phone=" + phone + ", address=" + address + '}';
    }
}
