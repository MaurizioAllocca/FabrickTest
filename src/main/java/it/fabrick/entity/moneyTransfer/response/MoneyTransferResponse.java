package it.fabrick.entity.moneyTransfer.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.fabrick.entity.response.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MoneyTransferResponse extends GenericResponse {

    private MoneyTransferPayload payload;

}
