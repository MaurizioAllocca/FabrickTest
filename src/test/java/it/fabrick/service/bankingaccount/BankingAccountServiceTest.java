package it.fabrick.service.bankingaccount;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.fabrick.entity.cashAccountTransactions.response.CashAccountTransactionsPayload;
import it.fabrick.repository.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BankingAccountServiceTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @SpyBean
    @Autowired
    private IBankingAccountService bas;

    @Value("classpath:mocks/cash_account_transactions.json")
    private Resource cashAccountTransactionsMock;

    @Test
    public void storeCashAccountTransactionsTest() throws Exception {

        CashAccountTransactionsPayload cashAccountTransactionsPayload = new ObjectMapper()
            .readValue(cashAccountTransactionsMock.getInputStream(),
                new TypeReference<>() {
                });

        bas.storeCashAccountTransactions(null);
        assertThat(transactionRepository.findAll(),
            hasSize(0));

        assertThat(
            bas.storeCashAccountTransactions(cashAccountTransactionsPayload),
            is(cashAccountTransactionsPayload));

        assertThat(transactionRepository.findAll(),
            hasSize(11));

    }
}

