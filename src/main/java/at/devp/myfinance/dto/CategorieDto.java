package at.devp.myfinance.dto;

import lombok.Data;

@Data
public class CategorieDto {
  private String categorie;
  private Integer id;
  private String defaultValue = "Categorie";
}
