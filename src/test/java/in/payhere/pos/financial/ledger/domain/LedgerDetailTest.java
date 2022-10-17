package in.payhere.pos.financial.ledger.domain;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LedgerDetailTest {

    @Test
    @DisplayName("가계부 상세내역 생성 테스트")
    public void ledgerDetailsCreateTest() {
        //given
        //when
        LedgerDetail ledgerDetail = LedgerDetail.create(2L, "판교 스타벅스에서 아메리카노 구입");

        //then
        assertThat(ledgerDetail).isNotNull();
    }

    @Test
    @DisplayName("가계부 상세내역 equals 성공 테스트")
    public void ledgerDetailsEqualsSuccessTest() {
        //given
        LedgerDetail ledgerDetail = LedgerDetail.create(2L, "판교 스타벅스에서 아메리카노 구입");
        //when
        LedgerDetail otherLedgerDetail = LedgerDetail.create(2L, "판교 스타벅스에서 아메리카노 구입");
        //then
        assertThat(ledgerDetail).isEqualTo(otherLedgerDetail);
    }

    @Test
    @DisplayName("가계부 상세내역 equals 실패 테스트")
    public void ledgerDetailsEqualsFailTest() {
        //given
        LedgerDetail ledgerDetail = LedgerDetail.create(2L, "판교 스타벅스에서 아메리카노 구입");
        LedgerDetail otherLedgerDetail = LedgerDetail.create(1L, "판교 스타벅스에서 아메리카노 구입");
        //when
        //then
        assertThat(ledgerDetail).isNotEqualTo(otherLedgerDetail);
    }
}
