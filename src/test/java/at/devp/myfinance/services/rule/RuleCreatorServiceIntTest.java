package at.devp.myfinance.services.rule;

import at.devp.myfinance.dto.RuleCreationDto;
import at.devp.myfinance.repositories.RuleRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ActiveProfiles("test")
class RuleCreatorServiceIntTest {

  @Autowired
  private RuleCreatorService underTest;

  @Autowired
  private RuleRepository ruleRepository;


  @Test
  @Transactional //Danger, i don't like this in tests
  void whenCreateRuleGivenRuleCreationDtoThenReturnCreatedRule() {
    assertThat(ruleRepository.findAll(), hasSize(0));

    final var ruleCreationDto = new RuleCreationDto();
    ruleCreationDto.setDescription("Spotify");
    ruleCreationDto.setFrom("Main");
    ruleCreationDto.setTo("Volksbank");

    underTest.createRule(ruleCreationDto);
    final var result = ruleRepository.findAll();

    assertThat(result, hasSize(1));
    assertThat(result.get(0).getDescription(), is(ruleCreationDto.getDescription()));
    assertThat(result.get(0).getFrom(), is(ruleCreationDto.getFrom()));
    assertThat(result.get(0).getTo(), is(ruleCreationDto.getTo()));
    assertThat(result.get(0).getAmount().toString(), is("0.00"));
    assertThat(result.get(0).getOldAmount().toString(), is("0.00"));
    assertThat(result.get(0).isChange(), is(false));
    assertThat(result.get(0).getSpendings(), hasSize(0));
  }

}