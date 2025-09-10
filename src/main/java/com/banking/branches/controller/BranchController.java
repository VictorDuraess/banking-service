package com.banking.branches.controller;

import com.banking.branches.domain.Branch;
import com.banking.branches.service.BranchService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/branches")
public class BranchController {

    private final BranchService branchService;

    BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @POST
    @Transactional
    public RestResponse<Void> create(Branch branch, @Context UriInfo uriInfo) {
        this.branchService.create(branch);
        return RestResponse.created(uriInfo.getAbsolutePathBuilder().build());
    }

    @GET
    @Path("{id}")
    public RestResponse<Branch> findById(Long id) {
        Branch branch = this.branchService.findById(id);
        return RestResponse.ok(branch);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public RestResponse<Void> delete(Long id) {
        this.branchService.delete(id);
        return RestResponse.ok();
    }

    @PUT
    @Transactional
    public RestResponse<Void> update(Branch branch) {
        this.branchService.update(branch);
        return RestResponse.ok();
    }
}


