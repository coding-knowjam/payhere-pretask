package in.payhere.pos.financial.user.service;

import in.payhere.pos.financial.user.domain.User;
import in.payhere.pos.financial.user.dto.UserRequest;
import in.payhere.pos.financial.user.dto.UserResponse;
import in.payhere.pos.financial.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserResponse save (UserRequest joinRequest) {
        String encodePassword = bCryptPasswordEncoder.encode(joinRequest.getPassword());
        User user = joinRequest.toUser(encodePassword);
        User saveUser = userRepository.save(user);
        return UserResponse.of(saveUser);
    }
}
