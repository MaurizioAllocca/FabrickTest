package it.fabrick.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.fabrick.entity.response.GenericResponse;
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
public class CashAccountBalance extends GenericResponse {

    private CashAccountBalancePayload payload;
}
