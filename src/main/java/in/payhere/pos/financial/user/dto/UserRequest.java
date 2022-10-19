package in.payhere.pos.financial.user.dto;

import in.payhere.pos.financial.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRequest {
    private String email;
    private String password;

    public User toUser(String encodePassword) {
        return User.create(email, encodePassword);
    }

}
