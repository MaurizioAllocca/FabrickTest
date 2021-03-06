package it.fabrick.entity.moneyTransfer.request;

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
public class LegalPersonBeneficiary {

    private String fiscalCode;
    private String legalRepresentativeFiscalCode;

}
