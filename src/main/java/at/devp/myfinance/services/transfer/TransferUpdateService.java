package at.devp.myfinance.services.transfer;

import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.repositories.TransferRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferUpdateService {

  private final TransferRepository transferRepository;

  private final SpendingRepository spendingRepository;

  public void addSpendingAndUpdate(final Long transferId) {
    final var spendings = spendingRepository.findAllSpendingsByTransferId(transferId);
    final var sumOfAllSpendingsOfRule = spendings.stream().mapToDouble(Spending::getAmount).sum();

    final var transfer = transferRepository.findById(transferId).orElseThrow(() -> new RuntimeException("Transfer not found"));
    transfer.setAmount(sumOfAllSpendingsOfRule);


    transfer.setChange(transfer.calculateHasChange());

    transfer.updateAmountAndChange();
    transferRepository.save(transfer);
  }

  public void addSpendingAndUpdate(final Spending spending) {
    final var transfer = spending.getTransfer();
    transfer.getSpendings().add(spending);

    final var sumOfSpendings = transfer.getSpendings().stream().mapToDouble(Spending::getAmount).sum();
    transfer.setAmount(sumOfSpendings);

    final var hasChange = sumOfSpendings != transfer.getOldAmount();
    transfer.setChange(hasChange);

    transferRepository.save(transfer);
  }


  @Transactional
  public void editTransferAndUpdate(final Transfer oldTransfer, final Transfer selectedTransfer, final Spending spending) {
    oldTransfer.getSpendings().remove(spending);
    oldTransfer.updateAmountAndChange();
    transferRepository.save(oldTransfer);

    selectedTransfer.getSpendings().add(spending);
    selectedTransfer.updateAmountAndChange();
    transferRepository.save(selectedTransfer);
  }
}
