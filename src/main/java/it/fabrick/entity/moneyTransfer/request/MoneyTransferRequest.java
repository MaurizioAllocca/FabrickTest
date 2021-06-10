package it.fabrick.entity.moneyTransfer.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.fabrick.entity.moneyTransfer.Address;
import it.fabrick.entity.moneyTransfer.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoneyTransferRequest {

    private Person creditor;
    private Address address;
    private String executionDate;
    private String uri;
    private String description;
    private Double amount;
    private String currency;
    private Boolean isUrgent;
    private Boolean isInstant;
    private String feeType;
    private String accountId;
    private TaxRelief taxRelief;

}
