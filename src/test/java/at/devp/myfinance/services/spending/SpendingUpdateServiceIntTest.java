package at.devp.myfinance.services.spending;

import at.devp.myfinance.dto.SpendingCreationDto;
import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.repositories.TransferRepository;
import at.devp.myfinance.services.spending.createspending.SpendingCreatorService;
import at.devp.myfinance.types.Category;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@ActiveProfiles("test")
class SpendingUpdateServiceIntTest {

  @Autowired
  private SpendingCreatorService spendingCreatorService;

  @Autowired
  private SpendingUpdateService underTest;

  @Autowired
  private SpendingRepository spendingRepository;

  @Autowired
  private RuleRepository ruleRepository;

  @Autowired
  private TransferRepository transferRepository;

  @Test
  @Transactional
  void createSpending_whenTransferAndRuleBothExist() {
    final var transfer1 = transferRepository.save(new Transfer());
    final var rule1 = ruleRepository.save(new Rule());

    final var spendingCreationDto = new SpendingCreationDto();
    spendingCreationDto.setTransferId(transfer1.getId());
    spendingCreationDto.setRuleId(rule1.getId());
    spendingCreationDto.setDescription("Test description");
    spendingCreationDto.setAmount(100D);
    spendingCreationDto.setCategory(Category.VERGNUEGEN);

    final var createdSpendingOverviewDto = spendingCreatorService.createSpending(spendingCreationDto);
    spendingRepository.flush();

    final var transfer2 = transferRepository.save(new Transfer());
    final var rule2 = ruleRepository.save(new Rule());

    final var editedSpendingCreationDto = new SpendingCreationDto();
    editedSpendingCreationDto.setId(createdSpendingOverviewDto.getId());
    editedSpendingCreationDto.setTransferId(transfer2.getId());
    editedSpendingCreationDto.setRuleId(rule2.getId());
    editedSpendingCreationDto.setDescription("Edited description");
    editedSpendingCreationDto.setAmount(200D);
    editedSpendingCreationDto.setCategory(Category.SPORT);

    underTest.updateSpending(editedSpendingCreationDto);
    spendingRepository.flush();

    final var result = spendingRepository.findById(createdSpendingOverviewDto.getId()).orElse(null);

    assertThat(result, is(notNullValue()));
    assertThat(result.getDescription(), is(editedSpendingCreationDto.getDescription()));
    assertThat(result.getAmount(), is(editedSpendingCreationDto.getAmount()));
    assertThat(result.getCategory(), is(editedSpendingCreationDto.getCategory()));

    final var transferResult = transferRepository.findById(transfer2.getId()).get();
    transferResult.calculateAmount();
    assertThat(transferResult.getAmount(), is(result.getAmount()));
    assertThat(transferResult.isChange(), is(true));

    final var ruleResult = result.getRule();
    assertThat(ruleResult.getAmount(), is(result.getAmount()));
    assertThat(ruleResult.isChange(), is(true));

    final var resultTransfer1 = transferRepository.findById(transfer1.getId()).get();
    //assertThat(resultTransfer1.isChange(), is(false));
    assertThat(resultTransfer1.getSpendings().isEmpty(), is(true));
    assertThat(resultTransfer1.getAmount(), is(0));

    final var resultRule1 = ruleRepository.findById(rule1.getId()).get();
    //assertThat(resultRule1.isChange(), is(false));
    assertThat(resultTransfer1.getSpendings().isEmpty(), is(true));
    assertThat(resultTransfer1.getAmount(), is(0));


  }

}