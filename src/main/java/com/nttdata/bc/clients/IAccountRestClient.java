package com.nttdata.bc.clients;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.nttdata.bc.models.Account;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/accounts")
@RegisterRestClient
public interface IAccountRestClient {

    @GET
    @Path("/debit-card/card-number/{cardNumber}")
    Uni<Account> findByCardNumber(@PathParam("cardNumber") String cardNumber);

}
