package it.fabrick.service.bankingpayments;

import it.fabrick.entity.moneyTransfer.request.MoneyTransferRequest;
import it.fabrick.entity.moneyTransfer.response.MoneyTransferPayload;

public interface IBankingPaymentsService {

    MoneyTransferPayload createMoneyTransfer(MoneyTransferRequest moneyTransferRequest);
}
