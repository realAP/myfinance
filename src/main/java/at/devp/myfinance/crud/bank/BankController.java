package at.devp.myfinance.crud.bank;

import at.devp.myfinance.crud.bank.create.BankCreationDto;
import at.devp.myfinance.crud.bank.create.BankCreationService;
import at.devp.myfinance.crud.bank.read.BankDto;
import at.devp.myfinance.crud.bank.read.BankReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fe/crud/banks")
public class BankController {

    private final BankCreationService bankCreationService;
    private final BankReadService bankReadService;

    @PostMapping()
    public ResponseEntity<?> createBank(@RequestBody final BankCreationDto bankCreationDto) {
        bankCreationService.createBank(bankCreationDto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping()
    public List<BankDto> getBankDtos() {
        return bankReadService.getBanks();
    }
}
