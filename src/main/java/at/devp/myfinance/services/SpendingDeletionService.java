package at.devp.myfinance.services;

import at.devp.myfinance.repositories.SpendingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpendingDeletionService {

  private final SpendingRepository spendingRepository;
  private final RuleStatusService ruleStatusService;
  private final TransferStatusService transferStatusService;

  @Transactional
  public void deleteById(final Long spendingId) {
    final var ruleId = spendingRepository.findRuleIdBySpendingId(spendingId);
    final var transferId = spendingRepository.findTransferIdBySpendingId(spendingId);
    spendingRepository.deleteById(spendingId);
    spendingRepository.flush();
    ruleStatusService.updateStatus(ruleId);
    transferStatusService.updateStatus(transferId);
  }
}
