package at.devp.myfinance.services.spending;

import at.devp.myfinance.dto.SpendingEditDto;
import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.repositories.TransferRepository;
import at.devp.myfinance.types.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ActiveProfiles("test")
@Sql("classpath:data_test.sql")
@AutoConfigureTestEntityManager
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SpendingEditServiceIntTest {

  @Autowired
  private SpendingEditService underTest;

  @Autowired
  private SpendingRepository spendingRepository;

  @Autowired
  private RuleRepository ruleRepository;

  @Autowired
  private TransferRepository transferRepository;

  @Autowired
  private TestEntityManager testEntityManager;

  private Spending msciWorldSpending;

  private Rule langzeitinvest1Rule;

  private Transfer etfTransfer;

  private Transfer bitcoinTransfer;

  private Rule zockenRule;

  @BeforeEach
  void setUp() {
    final var spendings = spendingRepository.findAll();
    msciWorldSpending = spendings.stream().filter(s -> s.getDescription().equalsIgnoreCase("MSCI World")).findFirst().get();
    langzeitinvest1Rule = ruleRepository.findAll().stream().filter(t -> t.getDescription().equalsIgnoreCase("langzeitinvest1")).findFirst().get();
    etfTransfer = transferRepository.findAll().stream().filter(r -> r.getDescription().equalsIgnoreCase("ETF")).findFirst().get();
    bitcoinTransfer = transferRepository.findAll().stream().filter(r -> r.getDescription().equalsIgnoreCase("Bitcoin")).findFirst().get();
    zockenRule = ruleRepository.findAll().stream().filter(t -> t.getDescription().equalsIgnoreCase("zocken")).findFirst().get();
  }


  @Test
  void whenEditSpendingGivenMsciWorldSpendingAndChangeAmountThenRuleAndTransferShouldUpdated() {
    final var editedMsciWorldSpending = new SpendingEditDto();
    editedMsciWorldSpending.setId(msciWorldSpending.getId());
    editedMsciWorldSpending.setDescription(msciWorldSpending.getDescription());
    editedMsciWorldSpending.setCategory(msciWorldSpending.getCategory());
    editedMsciWorldSpending.setRuleId(msciWorldSpending.getRule().getId());
    editedMsciWorldSpending.setTransferId(msciWorldSpending.getTransfer().getId());
    editedMsciWorldSpending.setAmount(new BigDecimal("200.00"));

    underTest.editSpending(editedMsciWorldSpending);

    final var result = spendingRepository.findById(msciWorldSpending.getId()).orElse(null);
    assertThat(result.getAmount(), is(editedMsciWorldSpending.getAmount()));

    final var deltaAmount = editedMsciWorldSpending.getAmount().subtract(msciWorldSpending.getAmount());

    final var transferAmount = etfTransfer.getAmount().add(deltaAmount);
    final var transferResult = result.getTransfer();
    assertThat(transferResult.getAmount(), is(transferAmount));
    assertThat(transferResult.isChange(), is(true));


    final var ruleAmount = langzeitinvest1Rule.getAmount().add(deltaAmount);
    final var ruleResult = result.getRule();
    assertThat(ruleResult.getAmount(), is(ruleAmount));
    assertThat(ruleResult.isChange(), is(true));
  }

  @Test
  void whenEditSpendingGivenMsciWorldSpendingAndChangeTransferThenTransferShouldBeUpdated() {
    final var editedMsciWorldSpending = new SpendingEditDto();
    editedMsciWorldSpending.setId(msciWorldSpending.getId());
    editedMsciWorldSpending.setDescription(msciWorldSpending.getDescription());
    editedMsciWorldSpending.setCategory(msciWorldSpending.getCategory());
    editedMsciWorldSpending.setRuleId(msciWorldSpending.getRule().getId());
    editedMsciWorldSpending.setAmount(msciWorldSpending.getAmount());
    editedMsciWorldSpending.setTransferId(bitcoinTransfer.getId()); // change transfer

    underTest.editSpending(editedMsciWorldSpending);

    final var result = spendingRepository.findById(msciWorldSpending.getId()).orElse(null);
    assertThat(result.getTransfer().getId(), is(bitcoinTransfer.getId()));

    final var resultBitcoinTransfer = result.getTransfer();
    assertThat(resultBitcoinTransfer.getAmount(), is(bitcoinTransfer.getAmount().add(msciWorldSpending.getAmount())));
    assertThat(resultBitcoinTransfer.isChange(), is(true));

    final var resultEtfTransfer = transferRepository.findById(etfTransfer.getId()).get();
    final var resultEtfTransferAmount = etfTransfer.getAmount().subtract(msciWorldSpending.getAmount());
    assertThat(resultEtfTransfer.getAmount(), is(resultEtfTransferAmount));
    assertThat(resultEtfTransfer.isChange(), is(true));
  }

  @Test
  void whenEditSpendingGivenMsciWorldSpendingAndChangeRuleThenRuleShouldBeUpdated() {
    final var editedMsciWorldSpending = new SpendingEditDto();
    editedMsciWorldSpending.setId(msciWorldSpending.getId());
    editedMsciWorldSpending.setDescription(msciWorldSpending.getDescription());
    editedMsciWorldSpending.setCategory(msciWorldSpending.getCategory());
    editedMsciWorldSpending.setAmount(msciWorldSpending.getAmount());
    editedMsciWorldSpending.setTransferId(msciWorldSpending.getTransfer().getId());
    editedMsciWorldSpending.setRuleId(zockenRule.getId()); // change rule

    underTest.editSpending(editedMsciWorldSpending);

    final var result = spendingRepository.findById(msciWorldSpending.getId()).orElse(null);
    assertThat(result.getRule().getId(), is(zockenRule.getId()));

    final var resultZockenRule = result.getRule();
    assertThat(resultZockenRule.getAmount(), is(zockenRule.getAmount().add(msciWorldSpending.getAmount())));
    assertThat(resultZockenRule.isChange(), is(true));

    final var resultLangzeitinvest1Rule = ruleRepository.findById(langzeitinvest1Rule.getId()).get();
    assertThat(resultLangzeitinvest1Rule.getAmount(), is(langzeitinvest1Rule.getAmount().subtract(msciWorldSpending.getAmount())));
    assertThat(resultLangzeitinvest1Rule.isChange(), is(true));
  }

  @Test
  void whenEditSpendingGivenMsciWorldSpendingAndChangeDetailsThenSaveIt() {
    final var editedMsciWorldSpending = new SpendingEditDto();
    editedMsciWorldSpending.setId(msciWorldSpending.getId());
    editedMsciWorldSpending.setAmount(msciWorldSpending.getAmount());
    editedMsciWorldSpending.setTransferId(msciWorldSpending.getTransfer().getId());
    editedMsciWorldSpending.setRuleId(zockenRule.getId());
    editedMsciWorldSpending.setDescription("MSCI World edited"); // change description
    editedMsciWorldSpending.setCategory(Category.SPORT); // change category

    underTest.editSpending(editedMsciWorldSpending);

    final var result = spendingRepository.findById(msciWorldSpending.getId()).orElse(null);

    assertThat(result.getDescription(), is("MSCI World edited"));
    assertThat(result.getCategory(), is(Category.SPORT));
  }
}