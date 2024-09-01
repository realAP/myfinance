package at.devp.myfinance.crud.rule.read;

import lombok.Data;

@Data
// This DTO is used for the read operations on the Rule entity.
// add needed information from rule here and fe picks what is needed
public class RuleDto {
    private Long id;
    private String description;
}
