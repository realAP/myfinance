package at.devp.myfinance.services.spending.edit;

import at.devp.myfinance.dto.SpendingEditDto;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.CategoryRepository;
import at.devp.myfinance.repositories.SpendingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SpendingEditManagerService {
    private final SpendingRepository spendingRepository;
    private final SpendingAmountService spendingAmountService;
    private final SpendingRuleService spendingRuleService;
    private final SpendingTransferService spendingTransferService;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void editSpending(final SpendingEditDto spendingEditDto) {
        final var spending = spendingRepository.findById(spendingEditDto.getId()).orElseThrow(() -> new IllegalArgumentException("Spending with id " + spendingEditDto.getId() + " not found"));

        if (checkForDescriptionChange(spendingEditDto, spending)) {
            spending.setDescription(spendingEditDto.getDescription());
        }

        if (checkForCategoryChange(spendingEditDto, spending)) {
            final var category = categoryRepository.findById(spendingEditDto.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found with id: "
                            + spendingEditDto.getCategoryId()));
            spending.setCategory(category);
        }

        if (checkForAmountChange(spendingEditDto, spending)) {
            spendingAmountService.setAmount(spendingEditDto, spending);
        }

        if (checkForRuleChange(spending, spendingEditDto)) {
            spendingRuleService.setRule(spendingEditDto, spending);
        }

        if (checkForTransferChange(spending, spendingEditDto)) {
            spendingTransferService.setTransfer(spendingEditDto, spending);
        }

        spendingRepository.save(spending);
    }

    private boolean checkForCategoryChange(SpendingEditDto spendingEditDto, Spending spending) {
        return !Objects.equals(spending.getCategory().getId(), spendingEditDto.getCategoryId());
    }

    private boolean checkForDescriptionChange(SpendingEditDto spendingEditDto, Spending spending) {
        return !Objects.equals(spending.getDescription(), spendingEditDto.getDescription());
    }

    private boolean checkForAmountChange(SpendingEditDto spendingEditDto, Spending spending) {
        return !Objects.equals(spending.getAmount(), spendingEditDto.getAmount());
    }

    private boolean checkForRuleChange(final Spending spending, final SpendingEditDto spendingEditDto) {
        return !Objects.equals(spending.getRule().getId(), spendingEditDto.getRuleId());
    }

    private boolean checkForTransferChange(final Spending spending, final SpendingEditDto spendingEditDto) {
        return !Objects.equals(spending.getTransfer().getId(), spendingEditDto.getTransferId());
    }

    public SpendingEditDto getSpendingEditDtoById(final Long id) {
        return spendingRepository.findById(id).map(spending -> {
            final var dto = new SpendingEditDto();
            dto.setId(spending.getId());
            dto.setAmount(spending.getAmount());
            dto.setDescription(spending.getDescription());
            dto.setCategoryId(spending.getCategory().getId());
            dto.setRuleId(spendingRepository.findRuleIdBySpendingId(spending.getId()));
            dto.setTransferId(spendingRepository.findTransferIdBySpendingId(spending.getId()));
            return dto;
        }).orElseThrow(() -> new IllegalArgumentException("Spending with id " + id + " not found"));
    }

}
