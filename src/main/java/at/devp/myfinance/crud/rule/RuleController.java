package at.devp.myfinance.crud.rule;

import at.devp.myfinance.crud.rule.create.RuleCreationDto;
import at.devp.myfinance.crud.rule.create.RuleCreationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/fe/crud/rules")
public class RuleController {
  private final RuleCreationService ruleCreationService;

  @PostMapping
  public ResponseEntity<?> createRule(@RequestBody RuleCreationDto ruleCreationDto) {
    ruleCreationService.createRule(ruleCreationDto);
    return new ResponseEntity<>(null, HttpStatus.CREATED);
  }
}
