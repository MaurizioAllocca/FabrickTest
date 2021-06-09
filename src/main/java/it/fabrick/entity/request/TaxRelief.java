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
public class TaxRelief {

    private String taxReliefId;
    private Boolean isCondoUpgrade;
    private String creditorFiscalCode;
    private String beneficiaryType;
    private NaturalPersonBeneficiary naturalPersonBeneficiary;

}
