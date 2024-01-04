package at.devp.myfinance.services.transfer;

import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.repositories.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferStatusService {

  private final TransferRepository transferRepository;

  private final SpendingRepository spendingRepository;

  public void updateStatus(final Long transferId) {
    final var spendings = spendingRepository.findAllSpendingsByTransferId(transferId);
    final var sumOfAllSpendingsOfRule = spendings.stream().mapToDouble(Spending::getAmount).sum();

    final var transfer = transferRepository.findById(transferId).orElseThrow(() -> new RuntimeException("Transfer not found"));
    transfer.setAmount(sumOfAllSpendingsOfRule);


    transfer.setChange(transfer.calculateHasChange());

    transfer.updateStatus();
    transferRepository.save(transfer);
  }

}
