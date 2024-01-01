package at.devp.myfinance.services.transfer;

import at.devp.myfinance.repositories.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferStatusService {

  private final TransferRepository transferRepository;

  public void updateStatus(final Long transferId) {
    final var transfer = transferRepository.findById(transferId).orElseThrow(() -> new RuntimeException("Transfer not found"));
    transfer.updateStatus();
    transferRepository.save(transfer);
  }

}
