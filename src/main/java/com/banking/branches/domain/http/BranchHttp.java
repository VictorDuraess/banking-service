package com.banking.branches.domain.http;

public class BranchHttp {

    public BranchHttp(String name, String legalName, String taxId, String registrationStatus) {
        this.name = name;
        this.legalName = legalName;
        this.taxId = taxId;
        this.registrationStatus = RegistrationStatus.valueOf(registrationStatus);
    }

    private final String name;
    private final String legalName;
    private final String taxId;
    private final RegistrationStatus registrationStatus;

    public String getName() {
        return name;
    }

    public String getLegalName() {
        return legalName;
    }

    public String getTaxId() {
        return taxId;
    }

    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }
}


