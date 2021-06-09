package it.fabrick.entity.cashAccountBalance.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class CashAccountBalancePayload {

    @JsonIgnore
    private String date;
    private String balance;
    @JsonIgnore
    private String availableBalance;
    @JsonIgnore
    private String currency;
}
