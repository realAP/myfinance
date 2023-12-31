package at.devp.myfinance.services.createspending;

import at.devp.myfinance.converter.Converter;
import at.devp.myfinance.dto.SpendingCreationDto;
import at.devp.myfinance.dto.SpendingOverviewDto;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.services.RuleStatusService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpendingCreatorService {

  private final SpendingRepository spendingRepository;
  private final RuleRepository ruleRepository;
  private final Converter converter;
  private final RuleStatusService ruleStatusService;


  @Transactional
  public SpendingOverviewDto createSpending(final SpendingCreationDto spendingCreationDto) {

    final var spending = new Spending();
    spending.setDescription(spendingCreationDto.getDescription());
    spending.setAmount(spendingCreationDto.getAmount());
    spending.setCategory(spendingCreationDto.getCategory());


    //final var transfer = new Transfer();
    //transfer.setDescription(spendingCreationDto.getTransferDto().getDescription());
    //spending.setTransfer(transfer);

    final var rule = ruleRepository.findById(spendingCreationDto.getRuleId()).get();
    spending.setRule(rule);
    final var createdSpending = spendingRepository.save(spending);

    ruleStatusService.updateStatus(spendingCreationDto.getRuleId());

    return converter.convert2SpendingOverviewDto(createdSpending);
  }
}
