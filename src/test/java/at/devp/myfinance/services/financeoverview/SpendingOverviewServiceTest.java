package at.devp.myfinance.services.financeoverview;

import at.devp.myfinance.converter.Converter;
import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.types.CategoryEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpendingOverviewServiceTest {

  @Mock
  private SpendingRepository spendingRepository;

  @Mock
  private Converter converter;

  @InjectMocks
  private SpendingOverviewService underTest;

  private Spending investmentSpending1 = new Spending();
  private Spending investmentSpending2 = new Spending();
  private Spending vergnuegenSpending1 = new Spending();
  private Spending vergnuegenSpending2 = new Spending();

  private final Rule rule1 = new Rule();

  private final Transfer transfer1 = new Transfer();

  @BeforeEach
  void setUp() {
    rule1.setDescription("Rule1");
    transfer1.setDescription("Transfer1");

    investmentSpending1.setAmount(new BigDecimal("100.00"));
    investmentSpending1.setDescription("MSCI World");
    investmentSpending1.setCategory(CategoryEnum.INVESTITIONEN);
    investmentSpending1.setRule(rule1);
    investmentSpending1.setTransfer(transfer1);
    investmentSpending1.setId(1L);

    investmentSpending2.setAmount(new BigDecimal("200.00"));
    investmentSpending2.setDescription("MSCI EM");
    investmentSpending2.setCategory(CategoryEnum.INVESTITIONEN);
    investmentSpending2.setRule(rule1);
    investmentSpending2.setTransfer(transfer1);
    investmentSpending2.setId(2L);

    vergnuegenSpending1.setAmount(new BigDecimal("10.00"));
    vergnuegenSpending1.setDescription("Kino");
    vergnuegenSpending1.setCategory(CategoryEnum.VERGNUEGEN);
    vergnuegenSpending1.setRule(rule1);
    vergnuegenSpending1.setTransfer(transfer1);
    vergnuegenSpending1.setId(3L);

    vergnuegenSpending2.setAmount(new BigDecimal("20.00"));
    vergnuegenSpending2.setDescription("Konzert");
    vergnuegenSpending2.setCategory(CategoryEnum.VERGNUEGEN);
    vergnuegenSpending2.setRule(rule1);
    vergnuegenSpending2.setTransfer(transfer1);
    vergnuegenSpending2.setId(4L);
  }

  @Test
  void whenGivenTwoInvestmentAndTwoVergnuegenSpendingsThenReturnSpendingTableDto() {
    when(spendingRepository.findAll()).thenReturn(List.of(investmentSpending1, investmentSpending2, vergnuegenSpending1, vergnuegenSpending2));

    final var result = underTest.createOverview();

    final var resultInvestment = findSpendingTableDtoByCategory(result, CategoryEnum.INVESTITIONEN);
    assertThat(resultInvestment.getCategory(), is(CategoryEnum.INVESTITIONEN));
    assertThat(resultInvestment.getSpendingSumPerCategory(), is(investmentSpending1.getAmount().add(investmentSpending2.getAmount())));
    assertThat(resultInvestment.getSpendingRowDtos().size(), is(2));

    final var resultVergnuegen = findSpendingTableDtoByCategory(result, CategoryEnum.VERGNUEGEN);
    assertThat(resultVergnuegen.getCategory(), is(CategoryEnum.VERGNUEGEN));
    assertThat(resultVergnuegen.getSpendingSumPerCategory(), is(vergnuegenSpending1.getAmount().add(vergnuegenSpending2.getAmount())));
    assertThat(resultVergnuegen.getSpendingRowDtos().size(), is(2));
  }

  @Test
  void whenGivenNoSpendingsThenReturnEmptyList() {
    when(spendingRepository.findAll()).thenReturn(List.of());

    final var result = underTest.createOverview();

    assertThat(result.size(), is(0));
  }

  @Test
  void whenGivenSpendingsThenReturnSpendingRowDtos() {
    when(spendingRepository.findAll()).thenReturn(List.of(investmentSpending1, investmentSpending2, vergnuegenSpending1, vergnuegenSpending2));

    final var result = underTest.createOverview();

    final var resultInvestment = findSpendingTableDtoByCategory(result, CategoryEnum.INVESTITIONEN);
    assertThat(resultInvestment.getSpendingRowDtos().size(), is(2));
    assertThat(resultInvestment.getSpendingRowDtos().get(0).getId(), is(1L));
    assertThat(resultInvestment.getSpendingRowDtos().get(0).getAmount(), is(new BigDecimal("100.00")));
    assertThat(resultInvestment.getSpendingRowDtos().get(0).getDescription(), is("MSCI World"));
    assertThat(resultInvestment.getSpendingRowDtos().get(0).getCategory(), is(CategoryEnum.INVESTITIONEN.toString()));
    assertThat(resultInvestment.getSpendingRowDtos().get(0).getRuleDescription(), is("Rule1"));
    assertThat(resultInvestment.getSpendingRowDtos().get(0).getTransferDescription(), is("Transfer1"));

    assertThat(resultInvestment.getSpendingRowDtos().get(1).getId(), is(2L));
    assertThat(resultInvestment.getSpendingRowDtos().get(1).getAmount(), is(new BigDecimal("200.00")));
    assertThat(resultInvestment.getSpendingRowDtos().get(1).getDescription(), is("MSCI EM"));
    assertThat(resultInvestment.getSpendingRowDtos().get(1).getCategory(), is(CategoryEnum.INVESTITIONEN.toString()));
    assertThat(resultInvestment.getSpendingRowDtos().get(1).getRuleDescription(), is("Rule1"));
    assertThat(resultInvestment.getSpendingRowDtos().get(1).getTransferDescription(), is("Transfer1"));

    final var resultVergnuegen = findSpendingTableDtoByCategory(result, CategoryEnum.VERGNUEGEN);
    assertThat(resultVergnuegen.getSpendingRowDtos().size(), is(2));
    assertThat(resultVergnuegen.getSpendingRowDtos().get(0).getId(), is(3L));
    assertThat(resultVergnuegen.getSpendingRowDtos().get(0).getAmount(), is(new BigDecimal("10.00")));
    assertThat(resultVergnuegen.getSpendingRowDtos().get(0).getDescription(), is("Kino"));
    assertThat(resultVergnuegen.getSpendingRowDtos().get(0).getCategory(), is(CategoryEnum.VERGNUEGEN.toString()));
    assertThat(resultVergnuegen.getSpendingRowDtos().get(0).getRuleDescription(), is("Rule1"));

    assertThat(resultVergnuegen.getSpendingRowDtos().get(1).getId(), is(4L));
    assertThat(resultVergnuegen.getSpendingRowDtos().get(1).getAmount(), is(new BigDecimal("20.00")));
    assertThat(resultVergnuegen.getSpendingRowDtos().get(1).getDescription(), is("Konzert"));
    assertThat(resultVergnuegen.getSpendingRowDtos().get(1).getCategory(), is(CategoryEnum.VERGNUEGEN.toString()));
    assertThat(resultVergnuegen.getSpendingRowDtos().get(1).getRuleDescription(), is("Rule1"));
  }

  private SpendingCategoryBlockDto findSpendingTableDtoByCategory(List<SpendingCategoryBlockDto> spendingCategoryBlockDtos, CategoryEnum category) {
    return spendingCategoryBlockDtos.stream().filter(spendingCategoryBlockDto -> spendingCategoryBlockDto.getCategory().equals(category)).findFirst().orElse(null);
  }

}