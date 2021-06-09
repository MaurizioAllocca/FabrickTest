package it.fabrick.repository;

import it.fabrick.entity.cashAccountTransactions.response.CashAccountTransactionsPayload;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<CashAccountTransactionsPayload, String> {

}
