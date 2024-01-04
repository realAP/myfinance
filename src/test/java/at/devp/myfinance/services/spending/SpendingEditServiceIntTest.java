package at.devp.myfinance.services.spending;

import at.devp.myfinance.dto.SpendingCreationDto;
import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.repositories.TransferRepository;
import at.devp.myfinance.services.spending.createspending.SpendingCreatorService;
import at.devp.myfinance.types.Category;
import jakarta.persistence.Cache;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestEntityManager
class SpendingEditServiceIntTest {

  @Autowired
  private SpendingCreatorService spendingCreatorService;

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

  @Autowired
  private EntityManagerFactory entityManagerFactory;

  @Test
  @Transactional
  void createSpending_whenTransferAndRuleBothExist() {
    // start preperation
    final var transferId1 = testEntityManager.persistAndGetId(new Transfer());
    final var ruleId1 = testEntityManager.persistAndGetId(new Rule());

    final var spendingCreationDto = new SpendingCreationDto();
    spendingCreationDto.setTransferId((Long) transferId1);
    spendingCreationDto.setRuleId((Long) ruleId1);
    spendingCreationDto.setDescription("Test description");
    spendingCreationDto.setAmount(100D);
    spendingCreationDto.setCategory(Category.VERGNUEGEN);

    final var createdSpendingOverviewDto = spendingCreatorService.createSpending(spendingCreationDto);

    final var transferId2 = testEntityManager.persistAndGetId(new Transfer());
    final var ruleId2 = testEntityManager.persistAndGetId(new Rule());

    testEntityManager.flush();
    // end preperation

    final var editedSpendingCreationDto = new SpendingCreationDto();
    editedSpendingCreationDto.setId(createdSpendingOverviewDto.getId());
    editedSpendingCreationDto.setTransferId((Long) transferId2);
    editedSpendingCreationDto.setRuleId((Long) ruleId2);
    editedSpendingCreationDto.setDescription("Edited description");
    editedSpendingCreationDto.setAmount(200D);
    editedSpendingCreationDto.setCategory(Category.SPORT);

    underTest.editSpending(editedSpendingCreationDto);
    testEntityManager.flush();
    testEntityManager.clear();

    SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    Cache cache = sessionFactory.getCache();

    if (cache != null) {
      cache.evictAll();
    }

    final var result = spendingRepository.findById(createdSpendingOverviewDto.getId()).orElse(null);

    assertThat(result, is(notNullValue()));
    assertThat(result.getDescription(), is(editedSpendingCreationDto.getDescription()));
    assertThat(result.getAmount(), is(editedSpendingCreationDto.getAmount()));
    assertThat(result.getCategory(), is(editedSpendingCreationDto.getCategory()));
    assertThat(result.getRule().getId(), is(ruleId2));
    assertThat(result.getTransfer().getId(), is(transferId2));

    final var resultRule2 = ruleRepository.findById((Long) ruleId2).get();
    assertThat(resultRule2.getSpendings(),contains(result));
    assertThat(resultRule2.getAmount(),is(200d));

    final var resultTransfer2 = transferRepository.findById((Long) transferId2).get();
    assertThat(resultTransfer2.getSpendings(),contains(result));
    assertThat(resultTransfer2.getAmount(),is(200d));

    final var resultRule1 = ruleRepository.findById((Long) ruleId1).get();
    assertThat(resultRule1.getSpendings().isEmpty(), is(true));
    assertThat(resultRule1.getAmount(), is(0.0));
    assertThat(resultRule1.isChange(), is(false));

    final var resultTransfer1 = transferRepository.findById((Long) transferId1).get();
    assertThat(resultTransfer1.getSpendings().isEmpty(), is(true));
    assertThat(resultTransfer1.getAmount(), is(0.0));
    assertThat(resultTransfer1.isChange(), is(false));
  }

}