package it.fabrick.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CashAccountTransactionsPayload {

    private String transactionId;
    private String operationId;
    private String accountingDate;
    private String valueDate;
    private CashAccountTransactionsPayloadType type;
    private Double amount;
    private String currency;
    private String description;
}
