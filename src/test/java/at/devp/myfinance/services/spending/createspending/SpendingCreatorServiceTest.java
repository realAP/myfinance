package at.devp.myfinance.services.spending.createspending;

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
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@ActiveProfiles("test")
class SpendingCreatorServiceTest {

  @Autowired
  private at.devp.myfinance.services.spending.createspending.SpendingCreatorService underTest;

  @Autowired
  private SpendingRepository spendingRepository;

  @Autowired
  private RuleRepository ruleRepository;

  @Autowired
  private TransferRepository transferRepository;

  @Test
  void createSpending_whenTransferAndRuleBothExist() {
    final var transfer = transferRepository.save(new Transfer());
    final var rule = ruleRepository.save(new Rule());

    final var spendingCreationDto = new SpendingCreationDto();
    spendingCreationDto.setTransferId(transfer.getId());
    spendingCreationDto.setRuleId(rule.getId());
    spendingCreationDto.setDescription("Test description");
    spendingCreationDto.setAmount(100D);
    spendingCreationDto.setCategory(Category.VERGNUEGEN);

    final var createdSpendingOverviewDto = underTest.createSpending(spendingCreationDto);

    final var result = spendingRepository.findById(createdSpendingOverviewDto.getId()).orElse(null);

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