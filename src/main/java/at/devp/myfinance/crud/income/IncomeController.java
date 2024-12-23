package at.devp.myfinance.crud.income;

import at.devp.myfinance.crud.income.create.IncomeCreationDto;
import at.devp.myfinance.crud.income.create.IncomeCreationService;
import at.devp.myfinance.crud.income.delete.IncomeDeletionService;
import at.devp.myfinance.crud.income.edit.IncomeEditService;
import at.devp.myfinance.crud.income.read.IncomeDto;
import at.devp.myfinance.crud.income.read.IncomeReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fe/crud/incomes")
public class IncomeController {
    private final IncomeCreationService incomeCreationService;
    private final IncomeEditService incomeEditService;
    private final IncomeDeletionService incomeDeletionService;
    private final IncomeReadService incomeReadService;

    @PostMapping
    public ResponseEntity<?> createIncome(@RequestBody IncomeCreationDto incomeCreationDto) {
        incomeCreationService.createIncome(incomeCreationDto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> editIncome(@PathVariable Long id, @RequestBody IncomeCreationDto incomeCreationDto) {
        incomeEditService.editIncome(id, incomeCreationDto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/{id}/delete")
    public void deleteIncome(@PathVariable("id") Long id) {
        incomeDeletionService.deleteById(id);
    }

    @GetMapping()
    public List<IncomeDto> getIncomes() {
        return incomeReadService.getIncomes();
    }
}
