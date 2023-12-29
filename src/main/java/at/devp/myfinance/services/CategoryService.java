package at.devp.myfinance.services;

import at.devp.myfinance.dto.CategorieDto;
import at.devp.myfinance.types.Category;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService {
  public List<CategorieDto> createCategories() {
    return Arrays.stream(Category.values()).map(category -> {
      final var categorieDto = new CategorieDto();
      categorieDto.setCategorie(category.toString());
      categorieDto.setId(category.ordinal());
      return categorieDto;
    }).toList();
  }
}
