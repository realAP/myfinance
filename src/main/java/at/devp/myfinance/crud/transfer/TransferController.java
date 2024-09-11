package at.devp.myfinance.crud.transfer;

import at.devp.myfinance.crud.transfer.create.TransferCreationDto;
import at.devp.myfinance.crud.transfer.create.TransferCreationService;
import at.devp.myfinance.crud.transfer.edit.TransferEditService2;
import at.devp.myfinance.crud.transfer.read.TransferDto;
import at.devp.myfinance.crud.transfer.read.TransferReadService;
import at.devp.myfinance.services.transfer.TransferChangeService;
import at.devp.myfinance.crud.transfer.delete.TransferDeletionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/fe/crud/transfers")
public class TransferController {
    private final TransferCreationService transferCreationService;
    private final TransferDeletionService transferDeletionService;
    private final TransferChangeService transferChangeService;
    private final TransferReadService transferReadService;
    private final TransferEditService2 transferEditService;

    @PostMapping
    public ResponseEntity<?> createTransfer(@RequestBody TransferCreationDto transferCreationDto) {
        transferCreationService.createTransfer(transferCreationDto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping
    public List<TransferDto> getTransferDtos() {
        return transferReadService.getTransferDtos();
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> editTransfer(@PathVariable Long id, @RequestBody TransferCreationDto transferEditDto) {
        transferEditService.editTransfer(id, transferEditDto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteTransfer(@PathVariable("id") Long id) {
        transferDeletionService.deleteById(id);
    }

    @PostMapping("/{id}/confirmchange")
    public void confirmChangeTransfer(@PathVariable("id") Long id) {
        transferChangeService.confirmAmountChangeForTransfer(id);
    }

}
