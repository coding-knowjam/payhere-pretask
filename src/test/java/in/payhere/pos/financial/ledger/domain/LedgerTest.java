package in.payhere.pos.financial.ledger.domain;

import in.payhere.pos.financial.authentication.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class LedgerTest {

    private User user = User.create(1L, "hihi@naver.com", "password");
    private LedgerDetail ledgerDetail = LedgerDetail.create(2L, "판교 스타벅스에서 아메리카노 구입");

    @Test
    @DisplayName("가계부 생성 테스트")
    public void ledgerCreateTest() {
        //given
        //when
        Ledger ledger = Ledger.create(1L, "커피구입", new BigDecimal("5400"), LocalDateTime.now(), user, ledgerDetail);

        //then
        assertThat(ledger).isNotNull();
    }

    @Test
    @DisplayName("가계부 equals 성공 테스트")
    public void ledgerEqualsSuccessTest() {
        //given
        Ledger ledger = Ledger.create(1L, "커피구입", new BigDecimal("5400"), LocalDateTime.now(), user, ledgerDetail);
        //when
        Ledger otherLedger = Ledger.create(1L, "커피구입", new BigDecimal("5400"), LocalDateTime.now(), user, ledgerDetail);
        //then
        assertThat(ledger).isEqualTo(otherLedger);
    }

    @Test
    @DisplayName("가계부 equals 실패 테스트")
    public void ledgerEqualsFailTest() {
        //given
        Ledger ledger = Ledger.create(1L, "커피구입", new BigDecimal("5400"), LocalDateTime.now(), user, ledgerDetail);
        //when
        Ledger otherLedger = Ledger.create(2L, "커피구입", new BigDecimal("6700"), LocalDateTime.now(), user, ledgerDetail);
        //then
        assertThat(ledger).isNotEqualTo(otherLedger);
    }
}
