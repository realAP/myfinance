package at.devp.myfinance.crud.spending;

import at.devp.myfinance.crud.spending.create.SpendingCreationDto;
import at.devp.myfinance.crud.spending.create.SpendingCreatorService;
import at.devp.myfinance.crud.spending.delete.SpendingDeletionService;
import at.devp.myfinance.crud.spending.edit.SpendingEditDto;
import at.devp.myfinance.crud.spending.edit.SpendingEditManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fe/crud/spendings")
public class SpendingController {

    private final SpendingCreatorService spendingCreatorService;
    private final SpendingEditManagerService spendingEditManagerService;
    private final SpendingDeletionService spendingDeletionService;

    @PostMapping
    public ResponseEntity<?> createSpending(@RequestBody SpendingCreationDto spendingCreationDto) {
        spendingCreatorService.createSpending(spendingCreationDto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public void editSpending(@PathVariable Long id, @RequestBody SpendingEditDto spendingEditDto) {
        spendingEditDto.setId(id);
        spendingEditManagerService.editSpending(spendingEditDto);
    }

    @DeleteMapping("/{id}")
    public void deleteSpending(@PathVariable("id") Long id) {
        spendingDeletionService.deleteById(id);
    }
}
