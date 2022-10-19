package in.payhere.pos.financial.ledger.dto;

import in.payhere.pos.financial.ledger.domain.Ledger;
import in.payhere.pos.financial.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LedgerRequest {
    private String title;
    private BigDecimal usedMoney;
    private String content;

    public Ledger toCreateLedger(User user) {
        return Ledger.create(title, usedMoney, content, user);
    }

    public Ledger toUpdateLedger() {
        return Ledger.create(title, usedMoney);
    }
}
