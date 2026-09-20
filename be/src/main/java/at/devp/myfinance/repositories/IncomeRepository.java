package at.devp.myfinance.repositories;

import at.devp.myfinance.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}
