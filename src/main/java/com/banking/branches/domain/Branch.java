package com.banking.branches.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "branch")
public class Branch {

    Branch() {

    }

    public Branch(Integer id, String name, String legalName, String taxId, Address address) {
        this.id = id;
        this.name = name;
        this.legalName = legalName;
        this.taxId = taxId;
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "legal_name")
    private String legalName;

    @Column(name = "tax_id")
    private String taxId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLegalName() {
        return legalName;
    }

    public String getTaxId() {
        return taxId;
    }

    public Address getAddress() {
        return address;
    }
}


