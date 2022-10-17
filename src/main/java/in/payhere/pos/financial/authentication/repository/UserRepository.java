package in.payhere.pos.financial.authentication.repository;

import in.payhere.pos.financial.authentication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
