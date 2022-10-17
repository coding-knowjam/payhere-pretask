package in.payhere.pos.financial.ledger.repository;

import static org.assertj.core.api.Assertions.assertThat;

import in.payhere.pos.financial.authentication.domain.User;
import in.payhere.pos.financial.authentication.repository.UserRepository;
import in.payhere.pos.financial.ledger.domain.Ledger;
import in.payhere.pos.financial.ledger.domain.LedgerDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class LedgerRepositoryTest {

    @Autowired
    private LedgerRepository ledgerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LedgerDetailRepository ledgerDetailRepository;

    @Test
    @DisplayName("가계부 insert 테스트")
    public void ledgerInsertTest() {
        //given
        User user = User.create(null, "hihi@naver.com", "password");
        LedgerDetail ledgerDetail = LedgerDetail.create(null, "판교 스타벅스에서 아메리카노 구입");

        User saveUser = userRepository.save(user);
        LedgerDetail saveLedgerDetail = ledgerDetailRepository.save(ledgerDetail);
        Ledger ledger = Ledger.create(null, "커피구입", new BigDecimal("5400"), LocalDateTime.now(), saveUser, saveLedgerDetail);

        //when
        Ledger saveLedger = ledgerRepository.save(ledger);
        Ledger findLedger = ledgerRepository.findById(saveLedger.getId()).orElse(ledger);

        //then
        assertThat(saveLedger).isEqualTo(findLedger);


    }
}
