package at.devp.myfinance.services.transfer;

import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.repositories.TransferRepository;
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


}
