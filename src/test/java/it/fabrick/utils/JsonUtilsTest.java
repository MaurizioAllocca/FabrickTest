package it.fabrick.utils;

import it.fabrick.entity.cashAccountTransactions.response.CashAccountTransaction;
import it.fabrick.entity.cashAccountTransactions.response.CashAccountTransactionsPayload;
import it.fabrick.entity.cashAccountTransactions.response.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JsonUtilsTest {

    @Value("classpath:mocks/cash_account_transactions.json")
    private Resource cashAccountTransactionsMock;

    @Test
    public void asPojoTest() throws IOException {
        CashAccountTransactionsPayload cashAccountTransactionsList = JsonUtils.asPojo(
            StreamUtils.copyToString
                (cashAccountTransactionsMock.getInputStream(), Charset.defaultCharset())
            , CashAccountTransactionsPayload.class);

        assertThat(cashAccountTransactionsList, notNullValue(CashAccountTransactionsPayload.class));

        assertThat(cashAccountTransactionsList.getList()
            , hasSize(11));

        assertThat(cashAccountTransactionsList.getList()
            , hasItem(
            CashAccountTransaction
                .builder()
                .transactionId("222260168301")
                .operationId("21000094633763")
                .accountingDate("2021-06-09")
                .valueDate("2021-06-09")
                .type(
                    Type
                        .builder()
                        .enumeration("GBS_TRANSACTION_TYPE")
                        .value("GBS_ACCOUNT_TRANSACTION_TYPE_0009")
                        .build())
                .amount(-1.0)
                .currency("EUR")
                .description("BA JOHN DOE               PAYMENT INVOICE 75/2017")
                .build()));
    }
}
