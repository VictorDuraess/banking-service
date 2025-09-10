package com.banking.branches.service.http;

import com.banking.branches.domain.http.BranchHttp;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/registration-status")
@RegisterRestClient(configKey = "registration-status-api")
public interface RegistrationStatusHttpService {

    @GET
    @Path("/{taxId}")
    BranchHttp findByTaxId(@PathParam("taxId") String taxId);
}


