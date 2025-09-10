package com.banking.branches.services;

import com.banking.branches.domain.Address;
import com.banking.branches.domain.Branch;
import com.banking.branches.domain.http.BranchHttp;
import com.banking.branches.repository.BranchRepository;
import com.banking.branches.service.BranchService;
import com.banking.branches.service.http.RegistrationStatusHttpService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
public class BranchServiceTest {

    @Inject
    @InjectMock
    @RestClient
    private RegistrationStatusHttpService registrationStatusHttpService;

    @InjectMock
    private BranchRepository branchRepository;

    @Inject
    private BranchService branchService;

    @Test
    public void shouldNotCreateWhenClientReturnsNull() {
        Branch branch = createBranch();
        Mockito.when(registrationStatusHttpService.findByTaxId("123")).thenReturn(null);

        Assertions.assertThrows(RuntimeException.class, () -> branchService.create(branch));

        Mockito.verify(branchRepository, Mockito.never()).persist(branch);
    }

    @Test
    public void shouldCreateWhenClientReturnsActive() {
        Branch branch = createBranch();
        Mockito.when(registrationStatusHttpService.findByTaxId("123")).thenReturn(createBranchHttp());

        branchService.create(branch);

        Mockito.verify(branchRepository).persist(branch);
    }

    private Branch createBranch() {
        Address address = new Address(1, "Test Street", "Test Thoroughfare", "Test Complement", 1);
        return new Branch(1, "Test Branch", "Test Legal Name", "123", address);
    }

    private BranchHttp createBranchHttp() {
        return new BranchHttp("Test Branch", "Test Legal Name", "123", "ACTIVE");
    }
}


