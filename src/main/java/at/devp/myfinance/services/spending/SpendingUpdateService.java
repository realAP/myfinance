package at.devp.myfinance.services.spending;

import at.devp.myfinance.dto.SpendingCreationDto;
import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.repositories.TransferRepository;
import at.devp.myfinance.services.ruleservice.RuleService;
import at.devp.myfinance.services.transfer.TransferStatusService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpendingUpdateService {
  private final RuleService ruleService;
  private final TransferStatusService transferStatusService;
  private final SpendingRepository spendingRepository;

  private final RuleRepository ruleRepository;
  private final TransferRepository transferRepository;

  @Transactional
  public void updateSpending(final SpendingCreationDto spendingCreationDto) {
    final var spending = spendingRepository.findById(spendingCreationDto.getId())
        .orElseThrow(() -> new IllegalArgumentException("Spending with id " + spendingCreationDto.getId() + " not found"));

    spending.setAmount(spendingCreationDto.getAmount());
    spending.setDescription(spendingCreationDto.getDescription());
    spending.setCategory(spendingCreationDto.getCategory());

    final var oldRuleId = spending.getRule().getId();
    final var newRuleId = spendingCreationDto.getRuleId();
    if (oldRuleId != newRuleId) {
      final var rule = ruleRepository.findById(spendingCreationDto.getRuleId()).get();
      spending.setRule(rule);
    }

    final var oldTransferId = spending.getTransfer().getId();
    final var newTransferId = spendingCreationDto.getTransferId();
    if (oldTransferId != newTransferId) {
      final var transfer = transferRepository.findById(spendingCreationDto.getTransferId()).get();
      spending.setTransfer(transfer);
    }

    spendingRepository.save(spending);
    spendingRepository.flush();

    //update old rule and transfer
    ruleService.updateStatus(oldRuleId);
    transferStatusService.updateStatus(oldTransferId);

    //update current
    ruleService.updateStatus(spendingCreationDto.getRuleId());
    transferStatusService.updateStatus(spendingCreationDto.getTransferId());
  }

  private void updateOldRuleAndTransfer() {

  }

  private void updateCurrentRuleAndTransfer() {

  }

  public SpendingCreationDto getSpendingCreationDtoById(final Long id) {

    return spendingRepository.findById(id)
        .map(spending -> {
          SpendingCreationDto dto = new SpendingCreationDto();
          dto.setId(spending.getId());
          dto.setAmount(spending.getAmount());
          dto.setDescription(spending.getDescription());
          dto.setCategory(spending.getCategory());
          dto.setRuleId(spendingRepository.findRuleIdBySpendingId(spending.getId()));
          dto.setTransferId(spendingRepository.findTransferIdBySpendingId(spending.getId()));
          return dto;
        })
        .orElseThrow(() -> new IllegalArgumentException("Spending with id " + id + " not found"));

  }

}
