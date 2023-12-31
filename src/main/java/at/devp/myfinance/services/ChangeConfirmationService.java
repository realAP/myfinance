package at.devp.myfinance.services;

import at.devp.myfinance.services.ruleservice.RuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeConfirmationService {

  private final RuleService ruleService;

}
