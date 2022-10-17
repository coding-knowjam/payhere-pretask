package in.payhere.pos.financial.ledger.domain;

import in.payhere.pos.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class LedgerDetail extends BaseEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ledgerDetail_id")
    private Long id;

    private String content;


    // 생성자
    private LedgerDetail(Long id, String content){
        this.id = id;
        this.content = content;
    }

    public static LedgerDetail create(Long id, String content) {
        return new LedgerDetail(id, content);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LedgerDetail that = (LedgerDetail) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
