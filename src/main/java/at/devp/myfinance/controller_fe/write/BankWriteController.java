package at.devp.myfinance.controller_fe.write;

import at.devp.myfinance.services.bank.create.BankCreationDto;
import at.devp.myfinance.services.bank.create.BankCreationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class BankWriteController {

    private final BankCreationService bankCreationService;

    @PostMapping("/fe/write/banks")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> createBank(@RequestBody final BankCreationDto bankCreationDto) {
        bankCreationService.createBank(bankCreationDto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}
