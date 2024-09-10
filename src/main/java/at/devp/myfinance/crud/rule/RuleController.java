package at.devp.myfinance.crud.rule;

import at.devp.myfinance.crud.rule.create.RuleCreationDto;
import at.devp.myfinance.crud.rule.create.RuleCreationService;
import at.devp.myfinance.crud.rule.edit.RuleEditService2;
import at.devp.myfinance.crud.rule.read.RuleDto;
import at.devp.myfinance.crud.rule.read.RuleReadService;
import at.devp.myfinance.services.rule.RuleChangeService;
import at.devp.myfinance.services.transfer.TransferChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/fe/crud/rules")
public class RuleController {
  private final RuleCreationService ruleCreationService;
  private final RuleReadService ruleReadService;
  private final RuleEditService2 ruleEditService;
  private final RuleChangeService ruleChangeService;

  @PostMapping
  public ResponseEntity<?> createRule(@RequestBody RuleCreationDto ruleCreationDto) {
    ruleCreationService.createRule(ruleCreationDto);
    return new ResponseEntity<>(null, HttpStatus.CREATED);
  }

  @PostMapping("/{id}")
  public ResponseEntity<?> editRule(@PathVariable Long id, @RequestBody RuleCreationDto ruleCreationDto) {
    ruleEditService.editRule(id, ruleCreationDto);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }


  @GetMapping
  public List<RuleDto> getRuleDtos() {
    return ruleReadService.getRuleDtos();
  }

  @PostMapping("/{id}/confirmchange")
  public void confirmChangeTransfer(@PathVariable("id") Long id) {
    ruleChangeService.confirmAmountChangeForRule(id);
  }
}
