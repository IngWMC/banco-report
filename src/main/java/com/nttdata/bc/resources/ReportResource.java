package com.nttdata.bc.resources;

import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse.StatusCode;

import com.nttdata.bc.models.Account;
import com.nttdata.bc.services.IReportService;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/reports")
public class ReportResource {
    @Inject
    Logger LOGGER;

    @Inject
    IReportService service;

    @GET
    @Path("/operations-banck-account")
    @ResponseStatus(StatusCode.OK)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Account> operationsBanckAccount() {
        return this.service.operationsBanckAccount();
    }
}
