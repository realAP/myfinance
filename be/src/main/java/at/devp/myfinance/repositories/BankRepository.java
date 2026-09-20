package at.devp.myfinance.repositories;

import at.devp.myfinance.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankRepository extends JpaRepository<Bank, Long> {
}
