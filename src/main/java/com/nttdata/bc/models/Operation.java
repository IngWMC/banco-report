package com.nttdata.bc.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class Operation {

    private Integer operationId;
    private Integer accountId;
    private Integer debitCardId;
    private Integer creditCardId;
    private Integer creditId;
    private String operationType;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
