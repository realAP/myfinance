package at.devp.myfinance.crud.transfer.delete;

import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferDeletionService {

  private final TransferRepository transferRepository;

  public void deleteById(final Long transferId) {
    transferRepository.deleteById(transferId);
  }

  public void removeSpendingAndUpdate(final Transfer transfer, final Spending spending) {
    transfer.getSpendings().remove(spending);
    transfer.updateAmountAndChange();
    transferRepository.save(transfer);
  }
}
