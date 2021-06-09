package it.fabrick.entity.moneyTransfer.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    private String cro;
    private String uri;
    private String trn;
    private String description;

}
