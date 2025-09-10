package com.banking.branches.service;

import com.banking.branches.domain.Branch;
import com.banking.branches.domain.http.BranchHttp;
import com.banking.branches.domain.http.RegistrationStatus;
import com.banking.branches.exception.BranchNotActiveOrNotFoundException;
import com.banking.branches.repository.BranchRepository;
import com.banking.branches.service.http.RegistrationStatusHttpService;
import io.micrometer.core.instrument.MeterRegistry;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class BranchService {

    private final BranchRepository branchRepository;
    private final MeterRegistry meterRegistry;

    BranchService(BranchRepository branchRepository, MeterRegistry meterRegistry) {
        this.branchRepository = branchRepository;
        this.meterRegistry = meterRegistry;
    }

    @Inject
    @RestClient
    RegistrationStatusHttpService registrationStatusHttpService;

    public void create(Branch branch) {
        BranchHttp branchHttp = registrationStatusHttpService.findByTaxId(branch.getTaxId());
        if(branchHttp != null && branchHttp.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)) {
            this.meterRegistry.counter("branch_added_count").increment();
            Log.info("Branch with taxId " + branch.getTaxId() + " was added");
            branchRepository.persist(branch);
        } else {
            Log.info("Branch with taxId " + branch.getTaxId() + " is not active or not found");
            this.meterRegistry.counter("branch_not_added_count").increment();
            throw new BranchNotActiveOrNotFoundException();
        }
    }

    public Branch findById(Long id) {
        return branchRepository.findById(id);
    }

    public void delete(Long id) {
        Log.info("The branch was deleted");
        branchRepository.deleteById(id);
    }

    public void update(Branch branch) {
        Log.info("The branch was updated");
        branchRepository.persist(branch);
    }
}


