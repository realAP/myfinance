package at.devp.myfinance.crud.rule.edit;

import at.devp.myfinance.crud.rule.create.RuleCreationDto;
import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.repositories.SpaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RuleEditService {


    private final RuleRepository ruleRepository;
    private final SpaceRepository spaceRepository;

    @Transactional
    public void editRule(final Long ruleId, final RuleCreationDto ruleCreationDto) {
        final var rule = ruleRepository.findById(ruleId).orElseThrow(() -> new IllegalArgumentException("Rule with id " + ruleId + "not found"));

        if (!Objects.equals(rule.getDescription(), ruleCreationDto.getDescription())) {
            rule.setDescription(ruleCreationDto.getDescription());
        }
        if (!Objects.equals(rule.getDateOfExecution(), ruleCreationDto.getDateOfExecution())) {
            rule.setDateOfExecution(ruleCreationDto.getDateOfExecution());
        }
        if (!Objects.equals(rule.getFromSpace().getId(), ruleCreationDto.getFromSpaceId())) {
            final var space = spaceRepository.findById(ruleCreationDto.getFromSpaceId()).orElseThrow(() -> new IllegalArgumentException("From space not found"));
            rule.setFromSpace(space);
        }
        if (!Objects.equals(rule.getToSpace().getId(), ruleCreationDto.getToSpaceId())) {
            final var toSpace = spaceRepository.findById(ruleCreationDto.getToSpaceId()).orElseThrow(() -> new IllegalArgumentException("To space not found"));
            rule.setToSpace(toSpace);
        }
        ruleRepository.save(rule);
    }


}
