package at.devp.myfinance.crud.income.edit;

import at.devp.myfinance.crud.income.create.IncomeCreationDto;
import at.devp.myfinance.repositories.IncomeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IncomeEditService {

    private final IncomeRepository incomeRepository;

    @Transactional
    public void editIncome(final Long incomeId, final IncomeCreationDto incomeCreationDto) {
        final var income = incomeRepository.findById(incomeId).orElseThrow(() -> new IllegalArgumentException("Income with incomeId " + incomeId + "not found"));

        if (!Objects.equals(income.getAmount(), incomeCreationDto.getAmount())) {
            income.setAmount(incomeCreationDto.getAmount());
        }

        if (!Objects.equals(income.getDescription(), incomeCreationDto.getDescription())) {
            income.setDescription(incomeCreationDto.getDescription());
        }
        incomeRepository.save(income);
    }
}
