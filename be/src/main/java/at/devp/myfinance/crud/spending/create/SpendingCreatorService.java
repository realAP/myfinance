package at.devp.myfinance.crud.spending.create;

import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.CategoryRepository;
import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.repositories.TransferRepository;
import at.devp.myfinance.services.rule.RuleUpdateService;
import at.devp.myfinance.services.transfer.TransferUpdateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpendingCreatorService {

    private final RuleRepository ruleRepository;
    private final RuleUpdateService ruleUpdateService;
    private final SpendingRepository spendingRepository;
    private final TransferRepository transferRepository;
    private final TransferUpdateService transferUpdateService;
    private final CategoryRepository categoryRepository;


    @Transactional
    public void createSpending(final SpendingCreationDto spendingCreationDto) {
        final var spending = new Spending();
        spending.setDescription(spendingCreationDto.getDescription());
        spending.setAmount(spendingCreationDto.getAmount());

        final var category = categoryRepository.findById(spendingCreationDto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: "
                        + spendingCreationDto.getCategoryId()));
        spending.setCategory(category);


        final var transfer = transferRepository.findById(spendingCreationDto.getTransferId())
                .orElseThrow(() -> new IllegalArgumentException("Transfer not found with id: "
                        + spendingCreationDto.getTransferId()));

        final var rule = ruleRepository.findById(spendingCreationDto.getRuleId())
                .orElseThrow(() -> new IllegalArgumentException("Rule not found with id: "
                        + spendingCreationDto.getRuleId()));

        spending.setRule(rule);
        ruleUpdateService.addSpendingAndUpdate(rule, spending);

        spending.setTransfer(transfer);
        transferUpdateService.addSpendingAndUpdate(transfer, spending);

        spendingRepository.save(spending);
    }
}
