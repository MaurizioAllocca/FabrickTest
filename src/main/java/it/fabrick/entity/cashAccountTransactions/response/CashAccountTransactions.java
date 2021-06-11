package it.fabrick.entity.cashAccountTransactions.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.fabrick.entity.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CashAccountTransactions extends GenericResponse {

    private CashAccountTransactionsPayload payload;

}
