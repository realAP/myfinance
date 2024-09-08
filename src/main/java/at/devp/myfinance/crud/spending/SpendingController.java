package at.devp.myfinance.crud.spending;

import at.devp.myfinance.dto.SpendingCreationDto;
import at.devp.myfinance.crud.spending.create.SpendingCreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/fe/crud/spendings")
public class SpendingController {

    private final SpendingCreatorService spendingCreatorService;

    @PostMapping
    public ResponseEntity<?> createSpending(@RequestBody SpendingCreationDto spendingCreationDto) {
        spendingCreatorService.createSpending(spendingCreationDto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
