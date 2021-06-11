package it.fabrick.entity.cashAccountTransactions.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CashAccountTransaction {

    @Id
    private String transactionId;
    private String operationId;
    private String accountingDate;
    private String valueDate;
    private Type type;
    private Double amount;
    private String currency;
    private String description;

}
