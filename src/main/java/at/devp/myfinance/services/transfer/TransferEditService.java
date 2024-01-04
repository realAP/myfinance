package at.devp.myfinance.services.transfer;

import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferEditService {

  private final TransferDeletionService transferDeletionService;
  private final TransferUpdateService transferUpdateService;

  @Transactional
  public void editTransferAndUpdate(final Transfer oldTransfer, final Spending spending) {
    transferDeletionService.removeSpendingAndUpdate(oldTransfer, spending);
    transferUpdateService.addSpendingAndUpdate(spending);
  }
}
