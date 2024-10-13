package at.devp.myfinance.feature.financeOverview;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpendingRowDto {
    private Long id;
    private String description;
    private String ruleDescription;
    private Long ruleId;
    private String transferDescription;
    private Long transferId;
    private BigDecimal amount;
    private String category;
    private Long categoryId;
}
