package it.fabrick.repository;

import it.fabrick.entity.Payload;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountRepository extends MongoRepository<Payload, String> {
    List<Payload> findByIban(String iban);
}
