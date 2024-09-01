package at.devp.myfinance.services.rule;

import at.devp.myfinance.crud.rule.create.RuleCreationDto;
import at.devp.myfinance.crud.rule.create.RuleCreationService;
import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.repositories.SpendingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class RuleCreationServiceIntTest {

  @Autowired
  private RuleCreationService underTest;

  @Autowired
  private RuleRepository ruleRepository;

  @Autowired
  private SpendingRepository spendingRepository;


  @Test
  void whenCreateRuleGivenRuleCreationDtoThenReturnCreatedRule() {
    assertThat(ruleRepository.findAll(), hasSize(0));

    final var ruleCreationDto = new RuleCreationDto();
    ruleCreationDto.setDescription("Spotify");
    ruleCreationDto.setFrom("Main");
    ruleCreationDto.setTo("Volksbank");
    ruleCreationDto.setDateOfExecution(LocalDate.of(2024, 8, 24));

    underTest.createRule(ruleCreationDto);
    final var result = ruleRepository.findAll();

    assertThat(result, hasSize(1));
    assertThat(result.get(0).getDescription(), is(ruleCreationDto.getDescription()));
    assertThat(result.get(0).getFrom(), is(ruleCreationDto.getFrom()));
    assertThat(result.get(0).getTo(), is(ruleCreationDto.getTo()));
    assertThat(result.get(0).getAmount().toString(), is("0.00"));
    assertThat(result.get(0).getOldAmount().toString(), is("0.00"));
    assertThat(result.get(0).isChange(), is(false));
    assertThat(result.get(0).getDateOfExecution(), is(LocalDate.of(2024, 8, 24)));
    assertThat(spendingRepository.findAll(), hasSize(0));
  }

}