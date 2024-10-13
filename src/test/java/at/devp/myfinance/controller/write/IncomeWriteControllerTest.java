package at.devp.myfinance.controller.write;

import at.devp.myfinance.crud.income.delete.IncomeDeletionService;
import at.devp.myfinance.crud.income.create.IncomeCreationDto;
import at.devp.myfinance.crud.income.create.IncomeCreationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
class IncomeWriteControllerTest {

  @Mock
  private IncomeCreationService incomeCreationService;

  @Mock
  private IncomeDeletionService incomeDeletionService;

  @InjectMocks
  private EarningWriteController underTest;

  @Test
  void whenCreateEarningIsCalledThenRedirectToIncome() {
    final var result = underTest.createEarning(new IncomeCreationDto());

    assertThat(result, is("redirect:/income"));
  }

  @Test
  void whenDeleteEarningIsCalledThenRedirectToIncome() {
    final var result = underTest.deleteEarning(1L);

    assertThat(result, is("redirect:/income"));
  }


}