package at.devp.myfinance.crud.bank.create;

import at.devp.myfinance.entity.Bank;
import at.devp.myfinance.repositories.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankCreationService {

    private final BankRepository bankRepository;

    public void createBank(final BankCreationDto bankCreationDto) {
        final var bank = new Bank();
        bank.setName(bankCreationDto.getName());
        bankRepository.save(bank);
    }
}
