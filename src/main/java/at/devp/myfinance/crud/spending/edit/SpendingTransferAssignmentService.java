package at.devp.myfinance.crud.spending.edit;

import at.devp.myfinance.crud.transfer.delete.TransferDeletionService;
import at.devp.myfinance.dto.SpendingEditDto;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.TransferRepository;
import at.devp.myfinance.services.transfer.TransferUpdateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpendingTransferAssignmentService {
  private final TransferRepository transferRepository;
  private final TransferDeletionService transferDeletionService;
  private final TransferUpdateService transferUpdateService;

  @Transactional
  public void setTransfer(SpendingEditDto spendingEditDto, Spending spending) {
    final var selectedTransfer = transferRepository.findById(spendingEditDto.getTransferId()).orElseThrow(() -> new IllegalArgumentException("Transfer with id " + spendingEditDto.getTransferId() + " not found"));
    final var oldTransfer = spending.getTransfer();
    spending.setTransfer(selectedTransfer);
    editTransferAndUpdate(oldTransfer, selectedTransfer, spending);
  }


  private void editTransferAndUpdate(final Transfer oldTransfer, final Transfer selectedTransfer, final Spending spending) {
    transferDeletionService.removeSpendingAndUpdate(oldTransfer, spending);
    transferUpdateService.addSpendingAndUpdate(selectedTransfer, spending);
  }
}
