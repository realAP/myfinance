package at.devp.myfinance.services.spending.edit;

import at.devp.myfinance.dto.SpendingEditDto;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.TransferRepository;
import at.devp.myfinance.services.transfer.TransferEditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpendingTransferService {
  private final TransferRepository transferRepository;
  private final TransferEditService transferEditService;

  public void editSpendingTransfer(SpendingEditDto spendingEditDto, Spending spending) {
    final var oldTransfer = spending.getTransfer();
    final var selectedTransfer = transferRepository.findById(spendingEditDto.getTransferId()).orElseThrow(() -> new IllegalArgumentException("Transfer with id " + spendingEditDto.getTransferId() + " not found"));
    spending.setTransfer(selectedTransfer);
    transferEditService.editTransferAndUpdate(oldTransfer, selectedTransfer, spending);
  }
}
