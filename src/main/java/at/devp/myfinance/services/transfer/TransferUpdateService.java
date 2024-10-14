package at.devp.myfinance.services.transfer;

import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferUpdateService {

    private final TransferRepository transferRepository;

    public void addSpendingAndUpdate(final Transfer selectedTransfer, final Spending spending) {
        selectedTransfer.getSpendings().add(spending);
        selectedTransfer.updateAmountAndChange();
        transferRepository.save(selectedTransfer);
    }

    public void updateStatus(final Transfer transfer) {
        transfer.updateAmountAndChange();
        transferRepository.save(transfer);
    }
}
