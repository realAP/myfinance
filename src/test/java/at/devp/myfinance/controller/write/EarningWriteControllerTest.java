package at.devp.myfinance.controller.write;

import at.devp.myfinance.services.income.EarningDeletionService;
import at.devp.myfinance.services.income.create.EarningCreationDto;
import at.devp.myfinance.services.income.create.EarningCreationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
class EarningWriteControllerTest {

  @Mock
  private EarningCreationService earningCreationService;

  @Mock
  private EarningDeletionService earningDeletionService;

  @InjectMocks
  private EarningWriteController underTest;

  @Test
  void whenCreateEarningIsCalledThenRedirectToIncome() {
    final var result = underTest.createEarning(new EarningCreationDto());

    assertThat(result, is("redirect:/income"));
  }

  @Test
  void whenDeleteEarningIsCalledThenRedirectToIncome() {
    final var result = underTest.deleteEarning(1L);

    assertThat(result, is("redirect:/income"));
  }


}