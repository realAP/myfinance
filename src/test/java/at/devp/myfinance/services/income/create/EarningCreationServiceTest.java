package at.devp.myfinance.services.income.create;

import at.devp.myfinance.entity.Earning;
import at.devp.myfinance.repositories.EarningRepository;
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
class EarningCreationServiceTest {

  @Mock
  private EarningRepository earningRepository;

  @InjectMocks
  private EarningCreationService underTest;

  @Test
  void whenCreateEarningGivenCreateEarningDtoThenStoreDatabaseEntry() {
    final var earningCreationDto = new EarningCreationDto();
    earningCreationDto.setAmount(new BigDecimal("100"));
    earningCreationDto.setDescription("test");

    underTest.createEarning(earningCreationDto);

    final var earningCaptor = ArgumentCaptor.forClass(Earning.class);

    verify(earningRepository).save(earningCaptor.capture());
    assertThat(earningCaptor.getValue().getAmount(), is(new BigDecimal("100")));
    assertThat(earningCaptor.getValue().getDescription(), is("test"));
  }

}