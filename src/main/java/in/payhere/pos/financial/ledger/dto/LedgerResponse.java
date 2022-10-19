package in.payhere.pos.financial.ledger.dto;

import in.payhere.pos.financial.ledger.domain.Ledger;
import in.payhere.pos.financial.ledger.domain.LedgerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LedgerResponse {

    private Long id;
    private Long userId;
    private String title;
    private BigDecimal usedMoney;
    private String content;
    private LedgerStatus status;

    public static LedgerResponse of(Ledger ledger) {
        return new LedgerResponse(ledger.getId(), getUserId(ledger), ledger.getTitle(), ledger.getUsedMoney(), ledger.getContent(), ledger.getStatus());
    }

    private static Long getUserId(Ledger ledger) {
        return ledger.getUser().getId();
    }
}
