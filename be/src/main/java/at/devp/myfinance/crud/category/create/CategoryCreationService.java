package at.devp.myfinance.crud.category.create;

import at.devp.myfinance.entity.Category;
import at.devp.myfinance.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryCreationService {

    private final CategoryRepository categoryRepository;

    public void createCategory(CategoryCreationDto categoryCreationDto) {
        final var category = new Category();
        category.setName(categoryCreationDto.getName());
        categoryRepository.save(category);
    }
}
