package it.fabrick.repository;

import it.fabrick.entity.cashAccountTransactions.response.CashAccountTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<CashAccountTransaction, String> {

}
