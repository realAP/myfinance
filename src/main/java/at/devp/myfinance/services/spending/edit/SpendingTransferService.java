package at.devp.myfinance.services.spending.edit;

import at.devp.myfinance.dto.SpendingEditDto;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.TransferRepository;
import at.devp.myfinance.services.transfer.TransferEditService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpendingTransferService {
  private final TransferRepository transferRepository;
  private final TransferEditService transferEditService;

  @Transactional
  public void setTransfer(SpendingEditDto spendingEditDto, Spending spending) {
    final var selectedTransfer = transferRepository.findById(spendingEditDto.getTransferId()).orElseThrow(() -> new IllegalArgumentException("Transfer with id " + spendingEditDto.getTransferId() + " not found"));
    final var oldTransfer = spending.getTransfer();
    spending.setTransfer(selectedTransfer);
    transferEditService.editTransferAndUpdate(oldTransfer, selectedTransfer, spending);
  }
}
