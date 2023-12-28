package at.devp.myfinance.services.createspending;

import at.devp.myfinance.dto.SpendingDto;
import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.SpendingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpendingService {

  private final SpendingRepository spendingRepository;


  public Spending createSpending(final SpendingDto spendingDto) {

    final var spending = new Spending();
    spending.setDescription(spendingDto.getDescription());
    spending.setAmount(spendingDto.getAmount());


    final var transfer = new Transfer();
    transfer.setDescription(spendingDto.getTransferDto().getDescription());
    spending.setTransfer(transfer);

    final var rule = new Rule();
    rule.setDescription(spendingDto.getRuleDto().getDescription());


    spending.setRule(rule);

    return spendingRepository.save(spending);
  }


}
