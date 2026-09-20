package at.devp.myfinance.repositories;

import at.devp.myfinance.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {
}