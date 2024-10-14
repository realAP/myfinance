package at.devp.myfinance.converter;

import at.devp.myfinance.crud.income.read.IncomeDto;
import at.devp.myfinance.crud.rule.read.RuleDto;
import at.devp.myfinance.crud.transfer.read.TransferDto;
import at.devp.myfinance.entity.Income;
import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Transfer;
import org.springframework.stereotype.Service;

@Service
public class Converter {

    public TransferDto convert2TransferDto(final Transfer transfer) {
        final var transferDto = new TransferDto();
        transferDto.setId(transfer.getId());
        transferDto.setDateOfExecution(transfer.getDateOfExecution());
        transferDto.setDescription(transfer.getDescription());
        transferDto.setAmount(transfer.getAmount());
        transferDto.setOldAmount(transfer.getOldAmount());
        transferDto.setFromBankName(transfer.getFromBank().getName());
        transferDto.setFromBankNameId(transfer.getFromBank().getId());
        transferDto.setToBankName(transfer.getToBank().getName());
        transferDto.setToBankNameId(transfer.getToBank().getId());
        transferDto.setIsChange(transfer.isChange());
        return transferDto;
    }

    public RuleDto convert2RuleDto(final Rule rule) {
        final var ruleDto = new RuleDto();
        ruleDto.setId(rule.getId());
        ruleDto.setDescription(rule.getDescription());
        ruleDto.setAmount(rule.getAmount());
        ruleDto.setOldAmount(rule.getOldAmount());
        ruleDto.setFromSpaceName(rule.getFromSpace().getName());
        ruleDto.setToSpaceName(rule.getToSpace().getName());
        ruleDto.setIsChange(rule.isChange());
        ruleDto.setFromSpaceId(rule.getFromSpace().getId());
        ruleDto.setToSpaceId(rule.getToSpace().getId());
        ruleDto.setDateOfExecution(rule.getDateOfExecution());
        return ruleDto;
    }

    public IncomeDto convert2IncomeDto(final Income income) {
        final var incomeDto = new IncomeDto();
        incomeDto.setId(income.getId());
        incomeDto.setDescription(income.getDescription());
        incomeDto.setAmount(income.getAmount());
        return incomeDto;
    }
}
