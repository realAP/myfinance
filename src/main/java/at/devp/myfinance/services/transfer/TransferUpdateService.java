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

  public void addSpendingAndUpdate(final Spending spending) {
    final var transfer = spending.getTransfer();
    transfer.getSpendings().add(spending);

    final var sumOfSpendings = transfer.getSpendings().stream().mapToDouble(Spending::getAmount).sum();
    transfer.setAmount(sumOfSpendings);

    transfer.setChange(sumOfSpendings != transfer.getOldAmount());

    transferRepository.save(transfer);
  }


  public void updateStatus(final Transfer transfer) {
    transfer.updateAmountAndChange();
    transferRepository.save(transfer);

  }

  @Transactional
  public void updateAll() {
    final var transfers = transferRepository.findAll();
    transfers.forEach(transfer -> {
      transfer.setAmount(transfer.calculateAmount());
      transfer.setChange(transfer.calculateHasChange());
    });
    transferRepository.saveAll(transfers);
  }
}
