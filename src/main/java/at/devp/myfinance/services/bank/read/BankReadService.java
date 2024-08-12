package at.devp.myfinance.services.bank.read;

import at.devp.myfinance.repositories.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankReadService {

    private final BankRepository bankRepository;

    public List<BankDto> getBanks() {
        return bankRepository.findAll().stream().map(bank -> {
            final var bankDto = new BankDto();
            bankDto.setId(bank.getId());
            bankDto.setName(bank.getName());
            return bankDto;
        }).toList();
    }
}
