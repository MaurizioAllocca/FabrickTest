package it.fabrick.entity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.fabrick.entity.request.Person;
import it.fabrick.entity.request.TaxRelief;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
