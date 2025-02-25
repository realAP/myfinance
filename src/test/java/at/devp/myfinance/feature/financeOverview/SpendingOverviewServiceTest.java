package at.devp.myfinance.feature.financeOverview;

import at.devp.myfinance.converter.Converter;
import at.devp.myfinance.entity.Category;
import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.feature.sumOfIncome.SumOfIncomeService;
import at.devp.myfinance.repositories.SpendingRepository;
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
    private SumOfIncomeService sumOfIncomeService;

    @Mock
    private SpendingRepository spendingRepository;

    @Mock
    private Converter converter;

    @InjectMocks
    private SpendingOverviewService underTest;

    private final Spending investmentSpending1 = new Spending();
    private final Spending investmentSpending2 = new Spending();
    private final Spending vergnuegenSpending1 = new Spending();
    private final Spending vergnuegenSpending2 = new Spending();

    private final Rule rule1 = new Rule();

    private final Transfer transfer1 = new Transfer();

    private final Category investmentCategory = new Category();
    private final Category vergneugenCategory = new Category();

    private BigDecimal sumOfIncome;

    @BeforeEach
    void setUp() {
        rule1.setDescription("Rule1");
        transfer1.setDescription("Transfer1");

        investmentCategory.setName("Investment");
        vergneugenCategory.setName("Vergüngen");

        investmentSpending1.setAmount(new BigDecimal("100.00"));
        investmentSpending1.setDescription("1MSCI World");
        investmentSpending1.setCategory(investmentCategory);
        investmentSpending1.setRule(rule1);
        investmentSpending1.setTransfer(transfer1);
        investmentSpending1.setId(1L);

        investmentSpending2.setAmount(new BigDecimal("200.00"));
        investmentSpending2.setDescription("2MSCI EM");
        investmentSpending2.setCategory(investmentCategory);
        investmentSpending2.setRule(rule1);
        investmentSpending2.setTransfer(transfer1);
        investmentSpending2.setId(2L);

        vergnuegenSpending1.setAmount(new BigDecimal("10.00"));
        vergnuegenSpending1.setDescription("Kino");
        vergnuegenSpending1.setCategory(vergneugenCategory);
        vergnuegenSpending1.setRule(rule1);
        vergnuegenSpending1.setTransfer(transfer1);
        vergnuegenSpending1.setId(3L);

        vergnuegenSpending2.setAmount(new BigDecimal("20.00"));
        vergnuegenSpending2.setDescription("Zoo");
        vergnuegenSpending2.setCategory(vergneugenCategory);
        vergnuegenSpending2.setRule(rule1);
        vergnuegenSpending2.setTransfer(transfer1);
        vergnuegenSpending2.setId(4L);

        sumOfIncome = new BigDecimal("330.00");
    }

    @Test
    void whenGivenTwoInvestmentAndTwoVergnuegenSpendingsThenReturnListOfSpendingCategoryBlockDto() {
        when(spendingRepository.findAll()).thenReturn(List.of(investmentSpending1, investmentSpending2, vergnuegenSpending1, vergnuegenSpending2));
        when(sumOfIncomeService.getSum()).thenReturn(sumOfIncome);

        final var result = underTest.createOverview();

        final var resultInvestment = findSpendingTableDtoByCategory(result, investmentCategory);
        assertThat(resultInvestment.getCategory(), is(investmentCategory.getName()));
        assertThat(resultInvestment.getPercentageToIncome(), is(new BigDecimal("90.91")));
        assertThat(resultInvestment.getSpendingSumPerCategory(), is(investmentSpending1.getAmount().add(investmentSpending2.getAmount())));
        assertThat(resultInvestment.getSpendingRowDtos().size(), is(2));

        final var resultVergnuegen = findSpendingTableDtoByCategory(result, vergneugenCategory);
        assertThat(resultVergnuegen.getCategory(), is(vergneugenCategory.getName()));
        assertThat(resultVergnuegen.getPercentageToIncome(), is(new BigDecimal("9.09")));
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
        when(sumOfIncomeService.getSum()).thenReturn(sumOfIncome);

        final var result = underTest.createOverview();

        final var resultInvestment = findSpendingTableDtoByCategory(result, investmentCategory);
        assertThat(resultInvestment.getSpendingRowDtos().size(), is(2));
        assertThat(resultInvestment.getSpendingRowDtos().get(0).getId(), is(1L));
        assertThat(resultInvestment.getSpendingRowDtos().get(0).getAmount(), is(new BigDecimal("100.00")));
        assertThat(resultInvestment.getSpendingRowDtos().get(0).getDescription(), is("1MSCI World"));
        assertThat(resultInvestment.getSpendingRowDtos().get(0).getCategory(), is(investmentCategory.toString()));
        assertThat(resultInvestment.getSpendingRowDtos().get(0).getRuleDescription(), is("Rule1"));
        assertThat(resultInvestment.getSpendingRowDtos().get(0).getTransferDescription(), is("Transfer1"));

        assertThat(resultInvestment.getSpendingRowDtos().get(1).getId(), is(2L));
        assertThat(resultInvestment.getSpendingRowDtos().get(1).getAmount(), is(new BigDecimal("200.00")));
        assertThat(resultInvestment.getSpendingRowDtos().get(1).getDescription(), is("2MSCI EM"));
        assertThat(resultInvestment.getSpendingRowDtos().get(1).getCategory(), is(investmentCategory.toString()));
        assertThat(resultInvestment.getSpendingRowDtos().get(1).getRuleDescription(), is("Rule1"));
        assertThat(resultInvestment.getSpendingRowDtos().get(1).getTransferDescription(), is("Transfer1"));

        final var resultVergnuegen = findSpendingTableDtoByCategory(result, vergneugenCategory);
        assertThat(resultVergnuegen.getSpendingRowDtos().size(), is(2));
        assertThat(resultVergnuegen.getSpendingRowDtos().get(0).getId(), is(3L));
        assertThat(resultVergnuegen.getSpendingRowDtos().get(0).getAmount(), is(new BigDecimal("10.00")));
        assertThat(resultVergnuegen.getSpendingRowDtos().get(0).getDescription(), is("Kino"));
        assertThat(resultVergnuegen.getSpendingRowDtos().get(0).getCategory(), is(vergneugenCategory.toString()));
        assertThat(resultVergnuegen.getSpendingRowDtos().get(0).getRuleDescription(), is("Rule1"));

        assertThat(resultVergnuegen.getSpendingRowDtos().get(1).getId(), is(4L));
        assertThat(resultVergnuegen.getSpendingRowDtos().get(1).getAmount(), is(new BigDecimal("20.00")));
        assertThat(resultVergnuegen.getSpendingRowDtos().get(1).getDescription(), is("Zoo"));
        assertThat(resultVergnuegen.getSpendingRowDtos().get(1).getCategory(), is(vergneugenCategory.toString()));
        assertThat(resultVergnuegen.getSpendingRowDtos().get(1).getRuleDescription(), is("Rule1"));
    }


    @Test
    void whenCreateOverviewGivenSpendingsSortedAfterCategoryThenReturnItSortedAscendingInCategory() {
        when(spendingRepository.findAll()).thenReturn(List.of(investmentSpending1, vergnuegenSpending2, investmentSpending2, vergnuegenSpending1));
        when(sumOfIncomeService.getSum()).thenReturn(sumOfIncome);

        final var result = underTest.createOverview();

        assertThat(result.get(0).getCategory(), is(investmentCategory.getName()));
        assertThat(result.get(0).getSpendingRowDtos().get(0).getAmount(), is(new BigDecimal("100.00")));
        assertThat(result.get(0).getSpendingRowDtos().get(1).getAmount(), is(new BigDecimal("200.00")));

        assertThat(result.get(1).getCategory(), is(vergneugenCategory.getName()));
        assertThat(result.get(1).getSpendingRowDtos().get(0).getAmount(), is(new BigDecimal("10.00")));
        assertThat(result.get(1).getSpendingRowDtos().get(1).getAmount(), is(new BigDecimal("20.00")));
    }

    private SpendingCategoryBlockDto findSpendingTableDtoByCategory(List<SpendingCategoryBlockDto> spendingCategoryBlockDtos, Category category) {
        return spendingCategoryBlockDtos.stream().filter(spendingCategoryBlockDto -> spendingCategoryBlockDto.getCategory().equals(category.getName())).findFirst().orElse(null);
    }

    @Test
    void whenCalculateSumGivenSpendingsThenReturnItsSumOfSpendings() {
        investmentSpending1.setAmount(new BigDecimal("33.33"));
        investmentSpending2.setAmount(new BigDecimal("31.41"));
        vergnuegenSpending1.setAmount(new BigDecimal("0.01"));
        vergnuegenSpending2.setAmount(new BigDecimal("999.99"));
        when(spendingRepository.findAll()).thenReturn(List.of(investmentSpending1, investmentSpending2, vergnuegenSpending1, vergnuegenSpending2));

        final var result = underTest.calculateSumOfSpendings();

        assertThat(result, is(new BigDecimal("1064.74")));
    }
}