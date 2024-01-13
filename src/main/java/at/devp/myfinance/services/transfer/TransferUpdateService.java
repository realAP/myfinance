package at.devp.myfinance.services.transfer;

import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferUpdateService {

  private final TransferRepository transferRepository;

  public void addSpendingAndUpdate(final Transfer selectedTransfer, final Spending spending) {
    selectedTransfer.getSpendings().add(spending);

    final var sumOfSpendings = selectedTransfer.getSpendings().stream().map(Spending::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    selectedTransfer.setAmount(sumOfSpendings);

    selectedTransfer.setChange(sumOfSpendings != selectedTransfer.getOldAmount());

    transferRepository.save(selectedTransfer);
  }

  public void updateStatus(final Transfer transfer) {
    transfer.updateAmountAndChange();
    transferRepository.save(transfer);

  }
}
