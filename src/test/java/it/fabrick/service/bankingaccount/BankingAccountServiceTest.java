package it.fabrick.service.bankingaccount;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.fabrick.Application;
import it.fabrick.entity.cashAccountTransactions.response.CashAccountTransactionsList;
import it.fabrick.repository.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;

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

        CashAccountTransactionsList cashAccountTransactionsList = new ObjectMapper()
            .readValue(cashAccountTransactionsMock.getInputStream(),
                new TypeReference<>() {
                });

        bas.storeCashAccountTransactions(null);
        assertThat(transactionRepository.findAll(), hasSize(0));

        assertThat(
            bas.storeCashAccountTransactions(cashAccountTransactionsList),
            is(cashAccountTransactionsList));

        assertThat(transactionRepository.findAll(), hasSize(11));

    }
}

