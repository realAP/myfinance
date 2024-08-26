package at.devp.myfinance.repositories;

import at.devp.myfinance.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
