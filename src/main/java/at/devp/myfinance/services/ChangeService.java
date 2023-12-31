package at.devp.myfinance.services;

import at.devp.myfinance.repositories.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeService {

  private final TransferRepository transferRepository;


  public void confirmAmountChangeForTransfer(final Long transferId) {
    final var transfer = transferRepository.findById(transferId).orElseThrow(() -> new IllegalArgumentException("Transfer not found"));
    transfer.setAmount(transfer.calculateAmount());
    transfer.setOldAmount(transfer.calculateAmount());
    transfer.setChange(false);

    transferRepository.save(transfer);
  }


}
