package at.devp.myfinance.services.createspending;

import at.devp.myfinance.converter.Converter;
import at.devp.myfinance.dto.SpendingDto;
import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.SpendingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpendingCreatorSerivce {

  private final SpendingRepository spendingRepository;
  private final Converter converter;


  public SpendingDto createSpending(final SpendingDto spendingDto) {

    final var spending = new Spending();
    spending.setDescription(spendingDto.getDescription());
    spending.setAmount(spendingDto.getAmount());
    spending.setCategory(spendingDto.getCategory());


    final var transfer = new Transfer();
    transfer.setDescription(spendingDto.getTransferDto().getDescription());
    spending.setTransfer(transfer);

    final var rule = new Rule();
    rule.setDescription(spendingDto.getRuleDto().getDescription());


    spending.setRule(rule);

    final var createdSpending = spendingRepository.save(spending);
    return converter.convert2SpendingDto(createdSpending);
  }

  public void deleteSpending(final Long spendingId) {
    spendingRepository.deleteById(spendingId);
  }


}
