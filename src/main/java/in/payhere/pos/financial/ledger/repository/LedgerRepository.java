package in.payhere.pos.financial.ledger.repository;

import in.payhere.pos.financial.user.domain.User;
import in.payhere.pos.financial.ledger.domain.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LedgerRepository extends JpaRepository<Ledger, Long> {
    List<Ledger> findByUser(User user);
}
