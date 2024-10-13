package at.devp.myfinance.crud.transfer.edit;

import at.devp.myfinance.crud.transfer.create.TransferCreationDto;
import at.devp.myfinance.repositories.BankRepository;
import at.devp.myfinance.repositories.TransferRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransferEditService {
    private final TransferRepository transferRepository;
    private final BankRepository bankRepository;

    @Transactional
    public void editTransfer(final Long transferId, final TransferCreationDto transferCreationDto) {
        final var transfer = transferRepository.findById(transferId).orElseThrow(() -> new IllegalArgumentException("Transfer with id " + transferId + " not found"));

        if (!Objects.equals(transfer.getDescription(), transferCreationDto.getDescription())) {
            transfer.setDescription(transferCreationDto.getDescription());
        }

        if (!Objects.equals(transfer.getDateOfExecution(), transferCreationDto.getDateOfExecution())) {
            transfer.setDateOfExecution(transferCreationDto.getDateOfExecution());
        }

        if (!Objects.equals(transfer.getFromBank().getId(), transferCreationDto.getFromBankId())) {
            final var fromBank = bankRepository.findById(transferCreationDto.getFromBankId()).orElseThrow(() -> new IllegalArgumentException("From bank not found"));
            transfer.setFromBank(fromBank);
        }

        if (!Objects.equals(transfer.getToBank().getId(), transferCreationDto.getToBankId())) {
            final var toBank = bankRepository.findById(transferCreationDto.getToBankId()).orElseThrow(() -> new IllegalArgumentException("To bank not found"));
            transfer.setToBank(toBank);
        }

        transferRepository.save(transfer);
    }


}
