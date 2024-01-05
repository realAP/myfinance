package at.devp.myfinance.services.spending;

import at.devp.myfinance.dto.SpendingCreationDto;
import at.devp.myfinance.dto.SpendingOverviewDto;
import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

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

  private SpendingOverviewDto createdSpendingOverviewDto;

  private Long movieTransfer;
  private Long movieRule;

  private Long transferId2;

  private Long ruleId2;

  private SpendingCreationDto spotifySpendingCreationDto;

  private Spending msciWorld;
  private Spending msciEm;

  private Spending lebensversicherung;

  @BeforeEach
  void setUp() {
    testEntityManager.clear();
    movieTransfer = (Long) testEntityManager.persistAndGetId(new Transfer());
    movieRule = (Long) testEntityManager.persistAndGetId(new Rule());

    final var netflixSpendingCreationDto = new SpendingCreationDto();
    netflixSpendingCreationDto.setTransferId(movieTransfer);
    netflixSpendingCreationDto.setRuleId(movieRule);
    netflixSpendingCreationDto.setDescription("netflix");
    netflixSpendingCreationDto.setAmount(BigDecimal.valueOf(9.12));
    netflixSpendingCreationDto.setCategory(Category.VERGNUEGEN);

    spotifySpendingCreationDto = new SpendingCreationDto();
    spotifySpendingCreationDto.setTransferId(movieTransfer);
    spotifySpendingCreationDto.setRuleId(movieRule);
    spotifySpendingCreationDto.setDescription("Test description");
    spotifySpendingCreationDto.setAmount(BigDecimal.valueOf(100D));
    spotifySpendingCreationDto.setCategory(Category.VERGNUEGEN);

    createdSpendingOverviewDto = spendingCreatorService.createSpending(spotifySpendingCreationDto);
    spotifySpendingCreationDto.setId(createdSpendingOverviewDto.getId());

    transferId2 = (Long) testEntityManager.persistAndGetId(new Transfer());
    ruleId2 = (Long) testEntityManager.persistAndGetId(new Rule());

    testEntityManager.flush();
    testEntityManager.clear();
  }

  @AfterEach
  void tearDown() {
    testEntityManager.clear();
  }

  @Test
  @Transactional
  void whenEditSpendingGivenSpendingChangeTransferAndTransferThenReturnEditedSpendingAndChangeAmountOfOldAndNewTransferAndSpending() {
    final var editedSpendingCreationDto = new SpendingCreationDto();
    editedSpendingCreationDto.setId(createdSpendingOverviewDto.getId());
    editedSpendingCreationDto.setTransferId(transferId2);
    editedSpendingCreationDto.setRuleId(ruleId2);
    editedSpendingCreationDto.setDescription("Edited description");
    editedSpendingCreationDto.setAmount(new BigDecimal("200.00"));
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
    assertThat(resultRule2.getSpendings(), contains(result));
    assertThat(resultRule2.getAmount(), is(editedSpendingCreationDto.getAmount()));

    final var resultTransfer2 = transferRepository.findById((Long) transferId2).get();
    assertThat(resultTransfer2.getSpendings(), contains(result));
    assertThat(resultTransfer2.getAmount(), is(editedSpendingCreationDto.getAmount()));

    final var resultRule1 = ruleRepository.findById((Long) movieRule).get();
    assertThat(resultRule1.getSpendings().isEmpty(), is(true));
    assertThat(resultRule1.getAmount(), is(new BigDecimal("0.00")));
    assertThat(resultRule1.getOldAmount(),is(new BigDecimal("0.00")));
    assertThat(resultRule1.isChange(), is(false));

    final var resultTransfer1 = transferRepository.findById((Long) movieTransfer).get();
    assertThat(resultTransfer1.getSpendings().isEmpty(), is(true));
    assertThat(resultTransfer1.getAmount(), is(new BigDecimal("0.00")));
    assertThat(resultTransfer1.isChange(), is(false));
  }

  @Test
  @Transactional
  void whenEditSpendingGivenSpendingAndChangeAmountOfSpendingThenRuleAndTransferShouldUpdateAmount() {
    final var editedSpendingCreationDto = new SpendingCreationDto();
    editedSpendingCreationDto.setId(spotifySpendingCreationDto.getId());
    editedSpendingCreationDto.setTransferId(spotifySpendingCreationDto.getTransferId());
    editedSpendingCreationDto.setRuleId(spotifySpendingCreationDto.getRuleId());
    editedSpendingCreationDto.setDescription(spotifySpendingCreationDto.getDescription());
    editedSpendingCreationDto.setCategory(spotifySpendingCreationDto.getCategory());
    editedSpendingCreationDto.setAmount(new BigDecimal("1337.12"));

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
    assertThat(result.getAmount(), is(editedSpendingCreationDto.getAmount()));

    final var resultRule1 = ruleRepository.findById(movieRule).get();
    assertThat(resultRule1.getSpendings(), contains(result));
    assertThat(resultRule1.getAmount(), is(editedSpendingCreationDto.getAmount()));
    assertThat(resultRule1.isChange(), is(true));

    final var resultTransfer1 = transferRepository.findById(movieTransfer).get();
    assertThat(resultTransfer1.getSpendings(), contains(result));
    assertThat(resultTransfer1.getAmount(), is(editedSpendingCreationDto.getAmount()));
    assertThat(resultTransfer1.isChange(), is(true));
  }

  @Test
  @Transactional
  void whenEditSpendingGivenSpendingAndChangeDescriptionAndCategoryThenUpdateSpending() {
    final var editedSpendingCreationDto = new SpendingCreationDto();
    editedSpendingCreationDto.setId(spotifySpendingCreationDto.getId());
    editedSpendingCreationDto.setTransferId(spotifySpendingCreationDto.getTransferId());
    editedSpendingCreationDto.setRuleId(spotifySpendingCreationDto.getRuleId());
    editedSpendingCreationDto.setDescription("Edited description");
    editedSpendingCreationDto.setCategory(Category.INVESTITIONEN);
    editedSpendingCreationDto.setAmount(spotifySpendingCreationDto.getAmount());

    underTest.editSpending(editedSpendingCreationDto);
    testEntityManager.flush();
    testEntityManager.clear();

    final var result = spendingRepository.findById(createdSpendingOverviewDto.getId()).orElse(null);

    assertThat(result, is(notNullValue()));
    assertThat(result.getDescription(), is(editedSpendingCreationDto.getDescription()));
    assertThat(result.getCategory(), is(editedSpendingCreationDto.getCategory()));
  }

  @Test
  @Transactional
  void whenEditSpendingGivenSpendingAndChangeTransferThenUpdateSpending() {
    final var editedSpendingCreationDto = new SpendingCreationDto();
    editedSpendingCreationDto.setId(spotifySpendingCreationDto.getId());
    editedSpendingCreationDto.setTransferId(transferId2);
    editedSpendingCreationDto.setRuleId(spotifySpendingCreationDto.getRuleId());
    editedSpendingCreationDto.setDescription(spotifySpendingCreationDto.getDescription());
    editedSpendingCreationDto.setCategory(spotifySpendingCreationDto.getCategory());
    editedSpendingCreationDto.setAmount(spotifySpendingCreationDto.getAmount());

    underTest.editSpending(editedSpendingCreationDto);
    testEntityManager.flush();
    testEntityManager.clear();

    final var result = spendingRepository.findById(createdSpendingOverviewDto.getId()).orElse(null);

    assertThat(result, is(notNullValue()));
    assertThat(result.getTransfer().getId(), is(transferId2));

    final var resultTransfer2 = transferRepository.findById(transferId2).get();
    assertThat(resultTransfer2.getSpendings(), contains(result));
    assertThat(resultTransfer2.getAmount(), is(spotifySpendingCreationDto.getAmount()));
  }

  @Test
  @Transactional
  void whenEditSpendingGivenSpendingAndChangeRuleThenUpdateSpending() {
    final var editedSpendingCreationDto = new SpendingCreationDto();
    editedSpendingCreationDto.setId(spotifySpendingCreationDto.getId());
    editedSpendingCreationDto.setTransferId(spotifySpendingCreationDto.getTransferId());
    editedSpendingCreationDto.setRuleId(ruleId2);
    editedSpendingCreationDto.setDescription(spotifySpendingCreationDto.getDescription());
    editedSpendingCreationDto.setCategory(spotifySpendingCreationDto.getCategory());
    editedSpendingCreationDto.setAmount(spotifySpendingCreationDto.getAmount());

    underTest.editSpending(editedSpendingCreationDto);
    testEntityManager.flush();
    testEntityManager.clear();

    final var result = spendingRepository.findById(createdSpendingOverviewDto.getId()).orElse(null);

    assertThat(result, is(notNullValue()));
    assertThat(result.getRule().getId(), is(ruleId2));

    final var resultRule2 = ruleRepository.findById(ruleId2).get();
    assertThat(resultRule2.getSpendings(), contains(result));
    assertThat(resultRule2.getAmount(), is(spotifySpendingCreationDto.getAmount()));
  }

}