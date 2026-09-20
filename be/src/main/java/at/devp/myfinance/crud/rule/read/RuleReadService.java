package at.devp.myfinance.crud.rule.read;

import at.devp.myfinance.converter.Converter;
import at.devp.myfinance.repositories.RuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RuleReadService {

    private final RuleRepository ruleRepository;
    private final Converter converter;

    public List<RuleDto> getRuleDtos() {
        return ruleRepository
                .findAll()
                .stream()
                .map(converter::convert2RuleDto)
                .sorted(Comparator.comparing(RuleDto::getDescription))
                .toList();
    }
}
