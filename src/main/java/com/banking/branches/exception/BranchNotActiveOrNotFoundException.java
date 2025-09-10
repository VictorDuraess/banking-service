package com.banking.branches.exception;

import com.banking.branches.domain.http.RegistrationStatus;

public class BranchNotActiveOrNotFoundException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Branch status is " + RegistrationStatus.INACTIVE + " or not found";
    }
}


