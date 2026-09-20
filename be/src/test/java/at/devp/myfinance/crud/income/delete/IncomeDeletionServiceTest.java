package at.devp.myfinance.crud.income.delete;

import at.devp.myfinance.repositories.IncomeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IncomeDeletionServiceTest {

    @Mock
    private IncomeRepository incomeRepository;

    @InjectMocks
    private IncomeDeletionService underTest;


    @Test
    void whenDeleteByIdGivenIdOfEarningToDeleteThenDeleteIt() {
        underTest.deleteById(5L);

        verify(incomeRepository).deleteById(5L);
    }


}