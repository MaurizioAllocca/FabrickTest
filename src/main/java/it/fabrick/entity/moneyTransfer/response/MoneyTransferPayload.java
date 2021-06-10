package it.fabrick.entity.moneyTransfer.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
public class MoneyTransferPayload {

    private String moneyTransferId;
    private String status;
    private String direction;
    private Person debtor;
    private Person creditor;
    private String cro;
    private String uri;
    private String trn;
    private String description;
    private String feeAccountId;
    private String createdDatetime;
    private String accountedDatetime;
    private String debtorValueDate;
    private String creditorValueDate;
    private Amount amount;
    private Boolean isUrgent;
    private Boolean isInstant;
    private String feeType;
    private ArrayNode fees;
    private Boolean hasTaxRelief;

}
