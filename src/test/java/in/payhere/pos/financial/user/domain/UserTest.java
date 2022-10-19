package in.payhere.pos.financial.user.domain;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    @DisplayName("고객 create 테스트")
    public void userCreateTest() {
        //given
        User user = User.create(1L, "hihi@naver.com", "password");
        //when
        //then
        assertThat(user).isNotNull();
    }

    @Test
    @DisplayName("고객 equals 성공 테스트")
    public void userEqualsSuccessTest() {
        //given
        User user = User.create(1L, "hihi@naver.com", "password");
        //when
        User otherUser = User.create(1L, "hihi@naver.com", "password");
        //then
        assertThat(user).isEqualTo(otherUser);
    }

    @Test
    @DisplayName("고객 equals 실패 테스트")
    public void userEqualsFailTest() {
        //given
        User user = User.create(1L, "hihi@naver.com", "password");
        //when
        User otherUser = User.create(4L, "hello@naver.com", "password");
        //then
        assertThat(user).isNotEqualTo(otherUser);
    }
}
