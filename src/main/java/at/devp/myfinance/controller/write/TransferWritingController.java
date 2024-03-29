package at.devp.myfinance.controller.write;

import at.devp.myfinance.dto.TransferCreationDto;
import at.devp.myfinance.services.transfer.TransferChangeService;
import at.devp.myfinance.services.transfer.TransferCreatorService;
import at.devp.myfinance.services.transfer.TransferDeletionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/transfers")
public class TransferWritingController {
  private final TransferCreatorService transferCreatorService;
  private final TransferDeletionService transferDeletionService;
  private final TransferChangeService transferChangeService;

  @PostMapping
  public String createTransfer(@ModelAttribute TransferCreationDto transferCreationDto) {
    transferCreatorService.createTransfer(transferCreationDto);
    return "redirect:/transfers";
  }

  @PostMapping("/{id}/delete")
  public String deleteTransfer(@PathVariable("id") Long id) {
    transferDeletionService.deleteById(id);
    return "redirect:/transfers";
  }

  @PostMapping("/{id}/confirmchange")
  public String confirmChangeTransfer(@PathVariable("id") Long id) {
    transferChangeService.confirmAmountChangeForTransfer(id);
    return "redirect:/transfers";
  }

}
