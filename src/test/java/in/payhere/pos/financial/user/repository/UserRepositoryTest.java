package in.payhere.pos.financial.user.repository;

import in.payhere.pos.financial.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("User insert 테스트")
    public void userInsertTest() {
        //given
        User user = User.create(null,"hihi@naver.com", "password");

        //when
        User saveUser = userRepository.save(user);
        User findUser = userRepository.findById(saveUser.getId()).orElse(user);

        //then
        assertThat(saveUser).isEqualTo(findUser);

    }
}
