package com.nttdata.bc.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {
    private Integer accountId;
    private Integer clientId;
    private DebitCard debitCard;
    private BigDecimal amount;
    private Boolean isMain;
    private Boolean isActive;
    private List<Operation> operations;
    private Instant createdAt;
    private Instant updateddAt;
}
