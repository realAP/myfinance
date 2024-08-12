package at.devp.myfinance.controller_fe.read;

import at.devp.myfinance.services.bank.read.BankDto;
import at.devp.myfinance.services.bank.read.BankReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class BankReadController {

    private final BankReadService bankReadService;

    @GetMapping("/fe/read/banks")
    public List<BankDto> getBankDtos() {
        return bankReadService.getBanks();
    }
}
