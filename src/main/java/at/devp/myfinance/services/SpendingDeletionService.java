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

  @Transactional
  public void deleteSpending(final Long spendingId) {
    final var ruleId = spendingRepository.findRuleIdBySpendingId(spendingId);
    spendingRepository.deleteById(spendingId);
    spendingRepository.flush();
    ruleStatusService.updateStatus(ruleId);
  }
}
