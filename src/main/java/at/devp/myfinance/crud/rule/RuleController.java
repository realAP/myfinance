package at.devp.myfinance.crud.rule;

import at.devp.myfinance.crud.rule.create.RuleCreationDto;
import at.devp.myfinance.crud.rule.create.RuleCreationService;
import at.devp.myfinance.crud.rule.read.RuleDto;
import at.devp.myfinance.crud.rule.read.RuleReadService;
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

  @PostMapping
  public ResponseEntity<?> createRule(@RequestBody RuleCreationDto ruleCreationDto) {
    ruleCreationService.createRule(ruleCreationDto);
    return new ResponseEntity<>(null, HttpStatus.CREATED);
  }

  @GetMapping
  public List<RuleDto> getRuleDtos() {
    return ruleReadService.getRuleDtos();
  }
}
