package at.devp.myfinance.repositories;

import at.devp.myfinance.entity.Earning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EarningRepository extends JpaRepository<Earning, Long> {
}
