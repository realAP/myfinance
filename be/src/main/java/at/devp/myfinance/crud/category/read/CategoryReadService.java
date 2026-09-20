package at.devp.myfinance.crud.category.read;

import at.devp.myfinance.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryReadService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getCategorieDtos() {
        return categoryRepository.findAll().stream().map(category -> {
            final var categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            return categoryDto;
        }).toList();
    }

}
