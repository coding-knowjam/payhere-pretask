package in.payhere.pos.financial.ledger.repository;

import in.payhere.pos.financial.ledger.domain.LedgerDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class LedgerDetailRepositoryTest {

    @Autowired
    private LedgerDetailRepository ledgerDetailRepository;

    @Test
    @DisplayName("가계부 상세내역 insert 테스트")
    public void ledgerDetailInsertTest() {
        //given
        LedgerDetail ledgerDetail = LedgerDetail.create(null, "판교 스타벅스에서 아메리카노 구입");

        //when
        LedgerDetail saveLedgerDetail = ledgerDetailRepository.save(ledgerDetail);

        LedgerDetail findLedgerDetail = ledgerDetailRepository.findById(saveLedgerDetail.getId()).orElse(ledgerDetail);

        //then
        assertThat(saveLedgerDetail).isEqualTo(findLedgerDetail);


    }
}
