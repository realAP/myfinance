package at.devp.myfinance.controller.write;

import at.devp.myfinance.dto.TransferCreationDto;
import at.devp.myfinance.services.transfer.TransferCreatorService;
import at.devp.myfinance.services.transfer.TransferDeletionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TransferWritingController {
  private final TransferCreatorService transferCreatorService;
  private final TransferDeletionService transferDeletionService;

  @PostMapping("/newtransfer")
  public String newTransfer(@ModelAttribute TransferCreationDto transferCreationDto, Model model) {
    transferCreatorService.createTransfer(transferCreationDto);
    return "redirect:/transfers";
  }

  @PostMapping("/removetransfer/{id}")
  public String deleteRule(@PathVariable("id") Long id) {
    transferDeletionService.deleteById(id);
    return "redirect:/transfers";
  }

}
