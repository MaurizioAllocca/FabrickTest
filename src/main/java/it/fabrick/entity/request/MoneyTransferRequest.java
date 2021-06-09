package it.fabrick.entity.request;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    private String executionDate;
    private String description;
    private Double amount;
    private String currency;
    private TaxRelief taxRelief;

}
