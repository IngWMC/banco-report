package com.nttdata.bc.services;

import com.nttdata.bc.models.Account;

import io.smallrye.mutiny.Uni;

public interface IReportService {
    Uni<Account> operationsBanckAccount();
}
