package in.payhere.pos.financial.ledger.domain;

import in.payhere.pos.common.domain.BaseEntity;
import in.payhere.pos.financial.authentication.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Ledger extends BaseEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ledger_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private BigDecimal usedMoney;

    private LocalDateTime usedDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ledgerDetail_id")
    private LedgerDetail ledgerDetail;

    @Enumerated(EnumType.STRING)
    private LedgerStatus ledgerStatus;


    // 생성자
    private Ledger(Long id, String title, BigDecimal usedMoney, LocalDateTime usedDate, User user, LedgerDetail ledgerDetail) {
        this.id = id;
        this.title = title;
        this.usedMoney = usedMoney;
        this.usedDate = usedDate;
        this.user = user;
        this.ledgerDetail = ledgerDetail;
        this.ledgerStatus = LedgerStatus.NON_DELETED;
    }

    public static Ledger create(Long id, String title, BigDecimal usedMoney, LocalDateTime usedDate, User user, LedgerDetail ledgerDetail) {
        return new Ledger(id, title, usedMoney, usedDate, user, ledgerDetail);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ledger ledger = (Ledger) o;
        return Objects.equals(id, ledger.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
