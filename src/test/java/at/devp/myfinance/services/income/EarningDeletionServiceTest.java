package at.devp.myfinance.services.income;

import at.devp.myfinance.repositories.EarningRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EarningDeletionServiceTest {

  @Mock
  private EarningRepository earningRepository;

  @InjectMocks
  private EarningDeletionService underTest;


  @Test
  void whenDeleteByIdGivenIdOfEarningToDeleteThenDeleteIt() {
    underTest.deleteById(5L);

    verify(earningRepository).deleteById(5L);
  }


}