package com.nttdata.bc.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.nttdata.bc.clients.IAccountRestClient;
import com.nttdata.bc.clients.IOperationRestClient;
import com.nttdata.bc.exceptions.BadRequestException;
import com.nttdata.bc.exceptions.NotFoundException;
import com.nttdata.bc.models.Account;
import com.nttdata.bc.models.Operation;
import com.nttdata.bc.services.IReportService;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ReportServiceImpl implements IReportService {
    @Inject
    Logger LOGGER;

    @Inject
    @RestClient
    IAccountRestClient accountRestClient;

    @Inject
    @RestClient
    IOperationRestClient operationRestClient;

    @ConfigProperty(name = "subtract-months")
    Long subtractMonths;

    @ConfigProperty(name = "max-size")
    Long maxSize;

    @Override
    public Uni<Account> operationsBanckAccount() {
        String cardNumber = "9cd7904b-150a-48ba-a3b2-9758e2e1f2e8";
        return this.accountRestClient.findByCardNumber(cardNumber)
                .onItem().ifNull()
                .failWith(() -> new NotFoundException("El cliente no contiene una cuenta bancaria."))
                .call(account -> {
                    LOGGER.info("subtractMonths: " + subtractMonths);
                    LOGGER.info("maxSize: " + maxSize);
                    return this.operationRestClient.findByAccountId(account.getAccountId())
                            .onItem()
                            .transform(ops -> {
                                if (ops.size() == 0)
                                    return Uni.createFrom().failure(
                                            new BadRequestException("El cliente no contiene con operaciones."));

                                List<Operation> operations = ops.stream()
                                        .filter(op -> {
                                            LocalDate compareDate = LocalDate
                                                    .now()
                                                    .minusMonths(Long.valueOf(subtractMonths));
                                            LocalDate date = op.getCreatedAt().toLocalDate();
                                            return compareDate.isBefore(date);
                                        }).limit(maxSize)
                                        .map(op -> {
                                            op.setOperationId(null);
                                            op.setAccountId(null);
                                            return op;
                                        })
                                        .toList();

                                if (operations.size() == 0)
                                    return Uni.createFrom()
                                            .failure(new BadRequestException(
                                                    "El cliente no ha realizado operaciones durante los "
                                                            + subtractMonths + " últimos meses."));

                                account.setOperations(operations);
                                account.setClientId(null);
                                account.setDebitCard(null);
                                account.setIsMain(null);
                                account.setIsActive(null);

                                return Uni.createFrom().item(account);
                            });
                });
    }

}
