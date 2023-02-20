package com.nttdata.bc.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebitCard {

    private Integer debitCardId;
    private Integer accountId;
    private String cardNumber; // número de la tarjeta
    private String pin;
    private String expirationDate; // fecha de vencimiento
    private String cardValidationCode; // código de validación de la tarjeta
    private Boolean isActive;

}
