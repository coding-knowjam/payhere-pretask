package in.payhere.pos.financial.ledger.repository;

import static org.assertj.core.api.Assertions.assertThat;

import in.payhere.pos.financial.user.domain.User;
import in.payhere.pos.financial.user.repository.UserRepository;
import in.payhere.pos.financial.ledger.domain.Ledger;
import in.payhere.pos.financial.ledger.domain.LedgerStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class LedgerRepositoryTest {

    @Autowired
    private LedgerRepository ledgerRepository;

    @Autowired
    private UserRepository userRepository;

    private User user = User.create(null, "hihi@naver.com", "password");
    private String title = "커피구입";
    private BigDecimal usedMoney = new BigDecimal("5400");
    private String content = "판교 스타벅스에서 아메리카노 구입";

    @Test
    @DisplayName("가계부 저장 DB 테스트")
    public void ledgerInsertTest() {
        //given
        User saveUser = userRepository.save(user);
        Ledger ledger = Ledger.create(null, title, usedMoney, content, saveUser);

        //when
        Ledger saveLedger = ledgerRepository.save(ledger);
        Ledger findLedger = ledgerRepository.findById(saveLedger.getId()).orElse(ledger);

        //then
        assertThat(saveLedger).isEqualTo(findLedger);
    }

    @Test
    @DisplayName("가계부 수정 DB 테스트")
    public void ledgerUpdateTest() {
        //given
        User saveUser = userRepository.save(user);
        Ledger ledger = Ledger.create(null, title, usedMoney, content, saveUser);
        Ledger saveLedger = ledgerRepository.save(ledger);

        //when
        String otherTitle = "노트북 구입";
        BigDecimal otherUsedMoney = new BigDecimal("1300000");
        Ledger updateLedger = Ledger.create(otherTitle, otherUsedMoney);
        saveLedger.update(updateLedger);

        ledgerRepository.flush();
        Ledger findLedger = ledgerRepository.findById(saveLedger.getId()).orElse(ledger);

        //then
        assertThat(findLedger.getTitle()).isEqualTo(otherTitle);
        assertThat(findLedger.getUsedMoney()).isEqualTo(otherUsedMoney);
    }

    @Test
    @DisplayName("가계부 삭제 및 복구 DB 테스트")
    public void ledgerRemoveAndRestoreTest() {
        //given
        User saveUser = userRepository.save(user);
        Ledger ledger = Ledger.create(null, title, usedMoney, content, saveUser);
        Ledger saveLedger = ledgerRepository.save(ledger);

        //when
        saveLedger.remove();
        ledgerRepository.flush();
        Ledger removeLedger = ledgerRepository.findById(saveLedger.getId()).orElse(ledger);

        //then
        assertThat(removeLedger.getStatus()).isEqualTo(LedgerStatus.DELETED);

        //when
        saveLedger.restore();
        ledgerRepository.flush();
        Ledger restoreLedger = ledgerRepository.findById(saveLedger.getId()).orElse(ledger);

        //then
        assertThat(restoreLedger.getStatus()).isEqualTo(LedgerStatus.DISPLAY);
    }

    @Test
    @DisplayName("가계부 전체조회 DB 테스트")
    public void ledgerFindAllTest() {
        //given
        int beforeSize = ledgerRepository.findAll().size();

        User saveUser = userRepository.save(user);
        Ledger firstLedger = Ledger.create(null, title, usedMoney, content, saveUser);

        String secondTitle = "노트북 구입";
        BigDecimal secondUsedMoney = new BigDecimal("1300000");
        String secondContent = "애플에서 맥북 구입";
        Ledger secondLedger = Ledger.create(null, secondTitle, secondUsedMoney, secondContent, saveUser);

        String thirdTitle = "강의 구매";
        BigDecimal thirdUsedMoney = new BigDecimal("63200");
        String thirdContent = "인프런에서 스프링 강의 결제";
        Ledger thirdLedger = Ledger.create(null, thirdTitle, thirdUsedMoney, thirdContent, saveUser);

        List<Ledger> saveLedgers = ledgerRepository.saveAll(List.of(firstLedger, secondLedger, thirdLedger));

        //when
        List<Ledger> ledgers = ledgerRepository.findAll();

        //then
        assertThat(ledgers.size()).isEqualTo(beforeSize + saveLedgers.size());
    }

    @Test
    @DisplayName("특정가계부 상세조회 DB 테스트")
    public void ledgerFindOneTest() {
        //given
        User saveUser = userRepository.save(user);
        Ledger ledger = Ledger.create(null, title, usedMoney, content, saveUser);
        Ledger saveLedger = ledgerRepository.save(ledger);

        //when
        Ledger findLedger = ledgerRepository.findById(saveLedger.getId()).orElse(ledger);

        //then
        assertThat(findLedger.getId()).isEqualTo(saveLedger.getId());
        assertThat(findLedger.getTitle()).isEqualTo(saveLedger.getTitle());
        assertThat(findLedger.getUsedMoney()).isEqualTo(saveLedger.getUsedMoney());
        assertThat(findLedger.getContent()).isEqualTo(saveLedger.getContent());
        assertThat(findLedger.getUser()).isEqualTo(saveLedger.getUser());
    }

    @Test
    @DisplayName("특정고객의 가계부 목록조회 DB 테스트")
    public void ledgerFindByUserIdTest() {
        //given
        User firstSaveUser = userRepository.save(user);
        String otherEmail = "hello@google.com";
        String otherPassword = "hello";
        User secondSaveUser = userRepository.save(User.create(null, otherEmail, otherPassword));

        String otherTitle = "외식";
        BigDecimal otherUsedMoney = new BigDecimal("25000");
        String otherContent = "교촌치킨 판교점에서 허니콤보 결제";

        ledgerRepository.save(Ledger.create(null, title, usedMoney, content, firstSaveUser));
        Ledger secondLedger = ledgerRepository.save(Ledger.create(null, otherTitle, otherUsedMoney, otherContent, secondSaveUser));

        //when
        List<Ledger> secondUserLedgers = ledgerRepository.findByUser(secondSaveUser);

        //then
        assertThat(secondUserLedgers).contains(secondLedger);
    }
}
