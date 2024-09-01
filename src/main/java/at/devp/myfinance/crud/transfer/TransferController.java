package at.devp.myfinance.crud.transfer;

import at.devp.myfinance.crud.transfer.read.TransferReadService;
import at.devp.myfinance.dto.TransferCreationDto;
import at.devp.myfinance.crud.transfer.read.TransferDto;
import at.devp.myfinance.services.transfer.TransferChangeService;
import at.devp.myfinance.crud.transfer.create.TransferCreationService;
import at.devp.myfinance.services.transfer.TransferDeletionService;
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

  @PostMapping
  public ResponseEntity<?> createTransfer(@RequestBody TransferCreationDto transferCreationDto) {
    transferCreationService.createTransfer(transferCreationDto);
    return new ResponseEntity<>(null, HttpStatus.CREATED);
  }

  @GetMapping
  public List<TransferDto> getTransferDtos() {
    return transferReadService.getTransferDtos();
  }

  @DeleteMapping("/{id}")
  public void deleteTransfer(@PathVariable("id") Long id) {
    throw new UnsupportedOperationException("This endpoint is not implemented yet.");
    //transferDeletionService.deleteById(id);
  }

  // this is business logic has nothing todo with simple not intelligent crud... move this to special place
  // i want the crud folders to be stupid and bound to entities
  @PostMapping("/{id}/confirmchange")
  public void confirmChangeTransfer(@PathVariable("id") Long id) {
    throw new UnsupportedOperationException("This endpoint is not implemented yet.");
    //transferChangeService.confirmAmountChangeForTransfer(id);
  }

}
