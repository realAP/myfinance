package at.devp.myfinance.crud.income.create;

import at.devp.myfinance.entity.Income;
import at.devp.myfinance.repositories.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncomeCreationService {
  private final IncomeRepository incomeRepository;

  public void createIncome(IncomeCreationDto incomeCreationDto) {
    final var income = new Income();
    income.setAmount(incomeCreationDto.getAmount());
    income.setDescription(incomeCreationDto.getDescription());

    incomeRepository.save(income);
  }
}
