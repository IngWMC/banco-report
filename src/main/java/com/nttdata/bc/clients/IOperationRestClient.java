package com.nttdata.bc.clients;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.nttdata.bc.models.Operation;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/operations")
@RegisterRestClient
public interface IOperationRestClient {

    @GET
    @Path("/account/{accountId}")
    Uni<List<Operation>> findByAccountId(@PathParam("accountId") Integer accountId);

}
