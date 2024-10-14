package at.devp.myfinance.crud.income.read;

import at.devp.myfinance.converter.Converter;
import at.devp.myfinance.repositories.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeReadService {
    private final IncomeRepository incomeRepository;
    private final Converter converter;


    public List<IncomeDto> getIncomes() {
        return incomeRepository
                .findAll()
                .stream()
                .map(converter::convert2IncomeDto)
                .toList();
    }
}
