package at.devp.myfinance.controller.read;

import at.devp.myfinance.services.income.IncomeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class IncomeOverviewPageControllerTest {
  @Mock
  private IncomeService incomeService;

  @InjectMocks
  private IncomeOverviewPageController underTest;

  @Test
  void whenGetIncomeOverviewIsCalledThenReturnIncomePage() {
    final var result = underTest.getIncomeOverview(mock(Model.class));

    assertThat(result, is("income"));
  }

}