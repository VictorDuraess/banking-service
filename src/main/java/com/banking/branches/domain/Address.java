package com.banking.branches.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    Address() {

    }

    public Address(Integer id, String street, String thoroughfare, String complement, Integer number) {
        this.id = id;
        this.street = street;
        this.thoroughfare = thoroughfare;
        this.complement = complement;
        this.number = number;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String street;

    private String thoroughfare;

    private String complement;

    @Column(name = "number")
    private Integer number;

    public Integer getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getThoroughfare() {
        return thoroughfare;
    }

    public String getComplement() {
        return complement;
    }

    public Integer getNumber() {
        return number;
    }
}


