package com.banking.branches.repository;

import com.banking.branches.domain.Branch;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BranchRepository implements PanacheRepository<Branch> {
}


