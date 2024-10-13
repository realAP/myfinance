package at.devp.myfinance.services.income;

import at.devp.myfinance.converter.Converter;
import at.devp.myfinance.crud.income.read.IncomeReadService;
import at.devp.myfinance.entity.Income;
import at.devp.myfinance.repositories.IncomeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncomeReadServiceTest {

    @Mock
    private IncomeRepository incomeRepository;

    @Spy
    private Converter converter;

    @InjectMocks
    private IncomeReadService underTest;


    @Test
    void whenGetIncomesGivenTwoIncomeEntitiesThenReturnListOfIncomeDto() {
        final var job = new Income();
        job.setId(1L);
        job.setDescription("job");
        job.setAmount(new BigDecimal("100.02"));

        final var stock = new Income();
        stock.setId(2L);
        stock.setDescription("stock");
        stock.setAmount(new BigDecimal("200.01"));

        when(incomeRepository.findAll()).thenReturn(List.of(job, stock));

        final var result = underTest.getIncomes();

        assertThat(result.get(0).getId(), is(1L));
        assertThat(result.get(0).getDescription(), is("job"));
        assertThat(result.get(0).getAmount(), is(new BigDecimal("100.02")));

        assertThat(result.get(1).getId(), is(2L));
        assertThat(result.get(1).getDescription(), is("stock"));
        assertThat(result.get(1).getAmount(), is(new BigDecimal("200.01")));
    }
}