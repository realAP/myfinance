package at.devp.myfinance.crud.category;


import at.devp.myfinance.crud.category.create.CategoryCreationDto;
import at.devp.myfinance.crud.category.create.CategoryCreationService;
import at.devp.myfinance.crud.category.read.CategoryDto;
import at.devp.myfinance.crud.category.read.CategoryReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fe/crud/categories")
public class CategoryController {

    private final CategoryCreationService categoryCreationService;
    private final CategoryReadService categoryReadService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryCreationDto categoryCreationDto) {
        categoryCreationService.createCategory(categoryCreationDto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping
    public List<CategoryDto> getCategoriesDtos() {
        return categoryReadService.getCategorieDtos();
    }
}
