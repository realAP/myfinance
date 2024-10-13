package at.devp.myfinance.repositories;

import at.devp.myfinance.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}