package in.payhere.pos.financial.ledger.domain;

import in.payhere.pos.financial.authentication.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class LedgerTest {

    private User user = User.create(1L, "hihi@naver.com", "password");
    private String title = "커피구입";
    private BigDecimal usedMoney = new BigDecimal("5400");
    private String content = "판교 스타벅스에서 아메리카노 구입";

    @Test
    @DisplayName("가계부 생성 테스트")
    public void ledgerCreateTest() {
        //given
        //when
        Ledger ledger = Ledger.create(1L, title, usedMoney, content, user);

        //then
        assertThat(ledger).isNotNull();
    }

    @Test
    @DisplayName("가계부 equals 성공 테스트")
    public void ledgerEqualsSuccessTest() {
        //given
        Ledger ledger = Ledger.create(1L, title, usedMoney, content, user);

        //when
        Ledger otherLedger = Ledger.create(1L, title, usedMoney, content, user);

        //then
        assertThat(ledger).isEqualTo(otherLedger);
    }

    @Test
    @DisplayName("가계부 equals 실패 테스트")
    public void ledgerEqualsFailTest() {
        //given
        Ledger ledger = Ledger.create(1L, title, usedMoney, content, user);

        //when
        BigDecimal otherUsedMoney = new BigDecimal("6700");
        String otherContent = "판교 이디아에서 아메리카노 구입";
        Ledger otherLedger = Ledger.create(2L, title, otherUsedMoney, otherContent, user);

        //then
        assertThat(ledger).isNotEqualTo(otherLedger);
    }

    @Test
    @DisplayName("가계부 수정 테스트")
    public void ledgerUpdateTest() {
        //given
        Ledger ledger = Ledger.create(1L, title, usedMoney, content, user);

        //when
        String otherTitle = "노트북 구입";
        BigDecimal otherUsedMoney = new BigDecimal("1300000");
        Ledger otherLedger = Ledger.create(otherTitle, otherUsedMoney);
        ledger.update(otherLedger);

        //then
        assertThat(ledger.getTitle()).isEqualTo(otherTitle);
        assertThat(ledger.getUsedMoney()).isEqualTo(otherUsedMoney);
    }

    @Test
    @DisplayName("가계부 삭제 및 복구 테스트")
    public void ledgerRemoveAndRestoreTest() {
        //given
        Ledger ledger = Ledger.create(1L, title, usedMoney, content, user);

        //when
        ledger.remove();

        //then
        assertThat(ledger.getStatus()).isEqualTo(LedgerStatus.DELETED);

        //when
        ledger.restore();

        //then
        assertThat(ledger.getStatus()).isEqualTo(LedgerStatus.DISPLAY);
    }

}
