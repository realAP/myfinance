package at.devp.myfinance.services.income;

import at.devp.myfinance.entity.Earning;
import at.devp.myfinance.repositories.EarningRepository;
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
class EarningServiceTest {

  @Mock
  private EarningRepository incomeRepository;
  @InjectMocks
  private IncomeService underTest;


  @Test
  void whenCreateIncomeOverviewGivenTwoEarningsThenReturnIncomeDto() {
    final var job = new Earning();
    job.setId(1L);
    job.setDescription("job");
    job.setAmount(new BigDecimal("100.02"));

    final var stock = new Earning();
    stock.setId(2L);
    stock.setDescription("stock");
    stock.setAmount(new BigDecimal("200.01"));

    when(incomeRepository.findAll()).thenReturn(List.of(job, stock));

    final var result = underTest.createIncomeOverview();

    assertThat(result.getSumOfEarnings(),is(new BigDecimal("300.03")));

    assertThat(result.getEarningDtos().get(0).getId(),is(1L));
    assertThat(result.getEarningDtos().get(0).getDescription(),is("job"));
    assertThat(result.getEarningDtos().get(0).getAmount(),is(new BigDecimal("100.02")));

    assertThat(result.getEarningDtos().get(1).getId(),is(2L));
    assertThat(result.getEarningDtos().get(1).getDescription(),is("stock"));
    assertThat(result.getEarningDtos().get(1).getAmount(),is(new BigDecimal("200.01")));
  }

}