package in.payhere.pos.financial.user.dto;

import in.payhere.pos.financial.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String email;

    public static UserResponse of (User user) {
        return new UserResponse(user.getId(), user.getEmail());
    }
}
