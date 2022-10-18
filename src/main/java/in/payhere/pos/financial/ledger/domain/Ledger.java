package in.payhere.pos.financial.ledger.domain;

import in.payhere.pos.common.domain.BaseEntity;
import in.payhere.pos.financial.authentication.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
public class Ledger extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ledger_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private BigDecimal usedMoney;

    private String content;

    @Enumerated(EnumType.STRING)
    private LedgerStatus status;


    // 생성자
    private Ledger(Long id, String title, BigDecimal usedMoney, String content) {
        this.id = id;
        this.title = title;
        this.usedMoney = usedMoney;
        this.content = content;
        this.status = LedgerStatus.DISPLAY;
    }

    private void setUser(User user) {
        this.user = user;
        user.getLedgers().add(this);
    }

    public static Ledger create(String title, BigDecimal usedMoney) {
        return create(null, title, usedMoney, null, null);
    }

    public static Ledger create(Long id, String title, BigDecimal usedMoney, String content, User user) {
        Ledger ledger = new Ledger(id, title, usedMoney, content);
        if (user != null) {
            ledger.setUser(user);
        }
        return ledger;
    }

    public void update(Ledger updateLedger) {
        if (StringUtils.hasText(updateLedger.getTitle())) {
            this.title = updateLedger.getTitle();
        }

        if (updateLedger.getUsedMoney() != null) {
            this.usedMoney = updateLedger.getUsedMoney();
        }
    }

    public void remove() {
        this.status = LedgerStatus.DELETED;
    }

    public void restore() {
        this.status = LedgerStatus.DISPLAY;
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
