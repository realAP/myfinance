package at.devp.myfinance.crud.rule.read;

import at.devp.myfinance.converter.Converter;
import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.repositories.RuleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RuleReadServiceTest {

    @Mock
    private RuleRepository ruleRepository;

    @Mock
    private Converter converter;

    @InjectMocks
    private RuleReadService underTest;

    @Test
    void whenGetRuleDtosGivenUnsortedThenReturnItSortedByDescription() {
        final var rule1 = new Rule();
        final var rule2 = new Rule();
        final var rule3 = new Rule();

        final var ruleDto1 = new RuleDto();
        ruleDto1.setId(1L);
        ruleDto1.setDescription("Zebra");

        final var ruleDto2 = new RuleDto();
        ruleDto2.setId(2L);
        ruleDto2.setDescription("Lion");

        final var ruleDto3 = new RuleDto();
        ruleDto3.setId(3L);
        ruleDto3.setDescription("Ape");

        when(ruleRepository.findAll()).thenReturn(List.of(rule2, rule3, rule1));
        when(converter.convert2RuleDto(rule1)).thenReturn(ruleDto1);
        when(converter.convert2RuleDto(rule2)).thenReturn(ruleDto2);
        when(converter.convert2RuleDto(rule3)).thenReturn(ruleDto3);

        final var result = underTest.getRuleDtos();

        assertEquals(3, result.size());
        assertEquals("Ape", result.get(0).getDescription());
        assertEquals("Lion", result.get(1).getDescription());
        assertEquals("Zebra", result.get(2).getDescription());
    }


}