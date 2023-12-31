package at.devp.myfinance.controller;

import at.devp.myfinance.dto.TransferCreationDto;
import at.devp.myfinance.dto.TransferDto;
import at.devp.myfinance.services.ChangeService;
import at.devp.myfinance.services.TransferCreatorService;
import at.devp.myfinance.services.TransferDeletionService;
import at.devp.myfinance.services.TransferOverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TransferController {
  private final TransferOverviewService transferOverviewService;
  private final TransferCreatorService transferCreatorService;
  private final TransferDeletionService transferDeletionService;
  private final ChangeService changeService;

  @GetMapping("/transfers")
  public String getTransfers(Model model) {
    final var transferDtos = transferOverviewService.createTransferOverview();

    model.addAttribute("transferDtos", transferDtos);
    model.addAttribute("transferDto", new TransferDto());
    model.addAttribute("transferCreationDto", new TransferCreationDto());

    return "transfer";
  }

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

  @PostMapping("/confirmchangetransfer/{id}")
  public String confirmChange(@PathVariable("id") Long id) {
    changeService.confirmAmountChangeForTransfer(id);
    return "redirect:/transfers";
  }

}
