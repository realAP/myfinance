package at.devp.myfinance.services;

import at.devp.myfinance.repositories.SpendingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpendingDeletionService {

  private final SpendingRepository spendingRepository;

  @Transactional
  public void deleteById(final Long spendingId) {
    final var spending = spendingRepository.findById(spendingId).orElseThrow(() -> new RuntimeException("Spending not found"));
    spending.removeRuleAndUpdateStatus();
    spending.removeTransferAndUpdateStatus();
    spendingRepository.deleteById(spendingId);
  }
}
