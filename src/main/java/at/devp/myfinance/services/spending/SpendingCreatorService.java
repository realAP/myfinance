package at.devp.myfinance.services.spending.createspending;

import at.devp.myfinance.converter.Converter;
import at.devp.myfinance.dto.SpendingCreationDto;
import at.devp.myfinance.dto.SpendingOverviewDto;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.repositories.TransferRepository;
import at.devp.myfinance.services.rule.RuleUpdateService;
import at.devp.myfinance.services.transfer.TransferUpdateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpendingCreatorService {

  private final Converter converter;
  private final RuleRepository ruleRepository;
  private final RuleUpdateService ruleUpdateService;
  private final SpendingRepository spendingRepository;
  private final TransferRepository transferRepository;
  private final TransferUpdateService transferUpdateService;


  @Transactional
  public SpendingOverviewDto createSpending(final SpendingCreationDto spendingCreationDto) {
    final var spending = new Spending();
    spending.setDescription(spendingCreationDto.getDescription());
    spending.setAmount(spendingCreationDto.getAmount());
    spending.setCategory(spendingCreationDto.getCategory());

    final var transfer = transferRepository.findById(spendingCreationDto.getTransferId())
        .orElseThrow(() -> new IllegalArgumentException("Transfer not found with id: "
                                                        + spendingCreationDto.getTransferId()));

    final var rule = ruleRepository.findById(spendingCreationDto.getRuleId())
        .orElseThrow(() -> new IllegalArgumentException("Rule not found with id: "
                                                        + spendingCreationDto.getRuleId()));

    spending.setRule(rule);
    spending.setTransfer(transfer);

    transferUpdateService.addSpendingAndUpdate(spending);
    ruleUpdateService.addSpendingAndUpdate(spending);

    final var createdSpending = spendingRepository.save(spending);
    return converter.convert2SpendingOverviewDto(createdSpending);
  }
}
