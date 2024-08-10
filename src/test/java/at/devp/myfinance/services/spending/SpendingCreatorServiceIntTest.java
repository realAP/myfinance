package at.devp.myfinance.services.spending;

import at.devp.myfinance.dto.SpendingCreationDto;
import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.repositories.TransferRepository;
import at.devp.myfinance.types.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class SpendingCreatorServiceIntTest {

  @Autowired
  private SpendingCreatorService underTest;

  @Autowired
  private SpendingRepository spendingRepository;

  @Autowired
  private RuleRepository ruleRepository;

  @Autowired
  private TransferRepository transferRepository;

  @Test
  void whenCreateSpendingGivenTransferAndRuleBothExistThenCreateNewSpending() {
    final var transferToSave = new Transfer();
    transferToSave.setDateOfExecution(LocalDate.of(2024, 8, 24));
    final var transfer = transferRepository.save(transferToSave);

    final var ruleToSave = new Rule();
    ruleToSave.setDateOfExecution(LocalDate.of(2024, 8, 24));
    final var rule = ruleRepository.save(ruleToSave);

    final var spendingCreationDto = new SpendingCreationDto();
    spendingCreationDto.setTransferId(transfer.getId());
    spendingCreationDto.setRuleId(rule.getId());
    spendingCreationDto.setDescription("Test description");
    spendingCreationDto.setAmount(new BigDecimal("100.00"));
    spendingCreationDto.setCategory(Category.VERGNUEGEN);

    underTest.createSpending(spendingCreationDto);

    final var result = spendingRepository.findAll().stream().findFirst().orElse(null);

    assertThat(result, is(notNullValue()));
    assertThat(result.getDescription(), is(spendingCreationDto.getDescription()));
    assertThat(result.getAmount(), is(spendingCreationDto.getAmount()));
    assertThat(result.getCategory(), is(spendingCreationDto.getCategory()));

    final var transferResult = result.getTransfer();
    assertThat(transferResult.getAmount(), is(result.getAmount()));
    assertThat(transferResult.isChange(), is(true));

    final var ruleResult = result.getRule();
    assertThat(ruleResult.getAmount(), is(result.getAmount()));
    assertThat(ruleResult.isChange(), is(true));
  }
}