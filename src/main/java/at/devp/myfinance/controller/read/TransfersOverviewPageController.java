package at.devp.myfinance.controller.read;

import at.devp.myfinance.dto.TransferCreationDto;
import at.devp.myfinance.dto.TransferDto;
import at.devp.myfinance.services.transfer.TransferOverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class TransfersOverviewPageController {
  private final TransferOverviewService transferOverviewService;

  @GetMapping("/transfers")
  public String getTransfers(Model model) {
    final var transferDtos = transferOverviewService.createTransferOverview();

    model.addAttribute("transferDtos", transferDtos);
    model.addAttribute("transferDto", new TransferDto());
    model.addAttribute("transferCreationDto", new TransferCreationDto());

    return "transfer";
  }
}
