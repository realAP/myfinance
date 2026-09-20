package at.devp.myfinance.crud.income.create;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncomeCreationDto {
    private String description;
    private BigDecimal amount;
}
