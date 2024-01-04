package at.devp.myfinance.services.transfer;

import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransferDeletionService {

  private final TransferRepository transferRepository;

  public void deleteById(final Long transferId) {
    transferRepository.deleteById(transferId);
  }

  public void removeSpendingAndUpdate(final Transfer transfer, final Spending spending) {
    transfer.getSpendings().remove(spending);
    final var sumOfSpendings = transfer.getSpendings().stream().filter(Objects::nonNull).mapToDouble(Spending::getAmount).sum();
    transfer.setAmount(sumOfSpendings);
    transfer.setChange(sumOfSpendings != transfer.getOldAmount());

    transferRepository.save(transfer);
  }
}
