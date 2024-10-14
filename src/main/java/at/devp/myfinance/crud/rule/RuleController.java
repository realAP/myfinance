package at.devp.myfinance.crud.rule;

import at.devp.myfinance.crud.rule.create.RuleCreationDto;
import at.devp.myfinance.crud.rule.create.RuleCreationService;
import at.devp.myfinance.crud.rule.delete.RuleDeletionService;
import at.devp.myfinance.crud.rule.edit.RuleChangeService;
import at.devp.myfinance.crud.rule.edit.RuleEditService;
import at.devp.myfinance.crud.rule.read.RuleDto;
import at.devp.myfinance.crud.rule.read.RuleReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/fe/crud/rules")
public class RuleController {
    private final RuleCreationService ruleCreationService;
    private final RuleReadService ruleReadService;
    private final RuleEditService ruleEditService;
    private final RuleChangeService ruleChangeService;
    private final RuleDeletionService ruleDeletionService;

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

    @DeleteMapping("/{id}")
    public void deleteRule(@PathVariable("id") Long id) {
        ruleDeletionService.deleteRule(id);
    }
}
