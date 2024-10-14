package at.devp.myfinance.crud.rule.read;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RuleDto {
    private Long id;
    private String description;
    private Boolean isChange;
    private BigDecimal amount;
    private BigDecimal oldAmount;
    private Long fromSpaceId;
    private String fromSpaceName;
    private Long toSpaceId;
    private String toSpaceName;
    private LocalDate dateOfExecution;
}