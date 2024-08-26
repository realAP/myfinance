package at.devp.myfinance.services.financeoverview;

import at.devp.myfinance.types.CategoryEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SpendingCategoryBlockDto {
  private CategoryEnum category;
  private List<SpendingRowDto> spendingRowDtos;
  private BigDecimal spendingSumPerCategory;
}
