package at.devp.myfinance.crud.income.delete;

import at.devp.myfinance.repositories.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncomeDeletionService {
    private final IncomeRepository incomeRepository;

    public void deleteById(Long id) {
        incomeRepository.deleteById(id);
    }
}
