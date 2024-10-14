package at.devp.myfinance.crud.spending.delete;

import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.repositories.TransferRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestEntityManager
@Sql("classpath:data_test.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class SpendingDeletionServiceTest {
    @Autowired
    private SpendingDeletionService underTest;

    @Autowired
    private SpendingRepository spendingRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private TransferRepository transferRepository;

    private Spending msciWorldSpending;

    private Rule langzeitinvest1Rule;

    private Transfer etfTransfer;


    @BeforeEach
    void setUp() {
        final var spendings = spendingRepository.findAll();
        msciWorldSpending = spendings.stream().filter(s -> s.getDescription().equalsIgnoreCase("MSCI World")).findFirst().get();
        langzeitinvest1Rule = ruleRepository.findAll().stream().filter(t -> t.getDescription().equalsIgnoreCase("langzeitinvest1")).findFirst().get();
        etfTransfer = transferRepository.findAll().stream().filter(r -> r.getDescription().equalsIgnoreCase("ETF")).findFirst().get();
    }

    @Test
    @Transactional
    void whenDeleteByIdGivenIdOfSpendingThenDeleteItAndAdjustRuleAndTransferAmounts() {
        final var spendingBeforeDeletion = testEntityManager.find(Spending.class, msciWorldSpending.getId()).getAmount();
        final var ruleBeforeDeletion = testEntityManager.find(Rule.class, langzeitinvest1Rule.getId()).getAmount();
        final var transferBeforeDeletion = testEntityManager.find(Transfer.class, etfTransfer.getId()).getAmount();

        underTest.deleteById(msciWorldSpending.getId());
        testEntityManager.flush();

        assertThat(testEntityManager.find(Spending.class, msciWorldSpending.getId()), nullValue());

        final var ruleAfterDeletion = testEntityManager.find(Rule.class, langzeitinvest1Rule.getId()).getAmount();
        assertThat(ruleAfterDeletion, is(ruleBeforeDeletion.subtract(spendingBeforeDeletion)));


        final var transferAfterDeletion = testEntityManager.find(Transfer.class, etfTransfer.getId()).getAmount();
        assertThat(transferAfterDeletion, is(transferBeforeDeletion.subtract(spendingBeforeDeletion)));
    }
}