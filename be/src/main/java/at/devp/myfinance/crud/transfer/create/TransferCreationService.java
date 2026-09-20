package at.devp.myfinance.crud.transfer.create;

import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.BankRepository;
import at.devp.myfinance.repositories.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferCreationService {
    private final TransferRepository transferRepository;
    private final BankRepository bankRepository;

    public void createTransfer(final TransferCreationDto transferCreationDto) {
        final var transfer = new Transfer();

        final var fromBank = bankRepository.findById(transferCreationDto.getFromBankId()).orElseThrow(() -> new IllegalArgumentException("From bank not found"));
        transfer.setFromBank(fromBank);

        final var toBank = bankRepository.findById(transferCreationDto.getToBankId()).orElseThrow(() -> new IllegalArgumentException("To bank not found"));
        transfer.setToBank(toBank);

        transfer.setDescription(transferCreationDto.getDescription());
        transfer.setDateOfExecution(transferCreationDto.getDateOfExecution());

        transferRepository.save(transfer);
    }
}
