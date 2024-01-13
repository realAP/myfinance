package at.devp.myfinance.services.spending;

import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.services.rule.RuleDeletionService;
import at.devp.myfinance.services.transfer.TransferDeletionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpendingDeletionService {

  private final RuleDeletionService ruleDeletionService;
  private final SpendingRepository spendingRepository;
  private final TransferDeletionService transferDeletionService;

  @Transactional
  public void deleteById(final Long spendingId) {
    final var spending = spendingRepository.findById(spendingId).orElseThrow(() -> new RuntimeException("Spending not found"));
    spendingRepository.deleteById(spendingId);

    final var transfer = spending.getTransfer();
    if (transfer != null) {
      transferDeletionService.removeSpendingAndUpdate(transfer, spending);
    }

    final var rule = spending.getRule();
    if (rule != null) {
      ruleDeletionService.removeSpendingAndUpdate(rule, spending);
    }
  }
}
