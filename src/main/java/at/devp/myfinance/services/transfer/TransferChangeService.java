package at.devp.myfinance.services.transfer;

import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferChangeService {

  private final TransferRepository transferRepository;

  public void confirmAmountChangeForTransfer(final Long transferId) {
    final var transfer = transferRepository.findById(transferId).orElseThrow(() -> new IllegalArgumentException("Transfer not found"));
    transfer.setAmount(transfer.calculateAmount());
    transfer.setOldAmount(transfer.calculateAmount());
    transfer.setChange(false);
    transferRepository.save(transfer);
  }

  public Boolean checkForTransferChange(@NonNull final Transfer transfer) {
    final var oldAmount = transfer.getOldAmount();
    final var amount = transfer.getAmount();
    return !amount.equals(oldAmount);
  }


}
