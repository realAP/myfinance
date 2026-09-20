package at.devp.myfinance.crud.income.create;

import at.devp.myfinance.entity.Income;
import at.devp.myfinance.repositories.IncomeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class IncomeCreationServiceTest {

    @Mock
    private IncomeRepository incomeRepository;

    @InjectMocks
    private IncomeCreationService underTest;

    @Test
    void whenCreateEarningGivenCreateIncomeDtoThenStoreDatabaseEntry() {
        final var earningCreationDto = new IncomeCreationDto();
        earningCreationDto.setAmount(new BigDecimal("100"));
        earningCreationDto.setDescription("test");

        underTest.createIncome(earningCreationDto);

        final var earningCaptor = ArgumentCaptor.forClass(Income.class);

        verify(incomeRepository).save(earningCaptor.capture());
        assertThat(earningCaptor.getValue().getAmount(), is(new BigDecimal("100")));
        assertThat(earningCaptor.getValue().getDescription(), is("test"));
    }

}