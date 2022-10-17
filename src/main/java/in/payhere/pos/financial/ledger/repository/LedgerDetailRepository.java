package in.payhere.pos.financial.ledger.repository;

import in.payhere.pos.financial.ledger.domain.LedgerDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerDetailRepository extends JpaRepository<LedgerDetail, Long> {

}
