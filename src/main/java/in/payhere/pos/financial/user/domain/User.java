package in.payhere.pos.financial.user.domain;

import in.payhere.pos.common.domain.BaseEntity;
import in.payhere.pos.financial.ledger.domain.Ledger;
import in.payhere.pos.financial.ledger.domain.LedgerStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Ledger> ledgers = new ArrayList<>();


    // 생성자
    private User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = UserRole.ROLE_USER;
    }

    public static User create(String email, String password) {
        return create(null, email, password);
    }

    public static User create(Long id, String email, String password) {
        return new User(id, email, password);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
