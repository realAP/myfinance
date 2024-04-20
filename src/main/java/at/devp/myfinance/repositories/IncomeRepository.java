package at.devp.myfinance.repositories;

import at.devp.myfinance.entity.Earning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Earning, Long> {
}
