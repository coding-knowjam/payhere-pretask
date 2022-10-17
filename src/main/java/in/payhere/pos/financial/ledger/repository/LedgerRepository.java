package in.payhere.pos.financial.ledger.repository;

import in.payhere.pos.financial.ledger.domain.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerRepository extends JpaRepository<Ledger, Long> {

}
