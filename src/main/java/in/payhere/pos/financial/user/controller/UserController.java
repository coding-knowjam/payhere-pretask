package in.payhere.pos.financial.user.controller;

import in.payhere.pos.financial.user.dto.UserRequest;
import in.payhere.pos.financial.user.dto.UserResponse;
import in.payhere.pos.financial.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> join(@RequestBody UserRequest joinRequest) {
        UserResponse userResponse = userService.save(joinRequest);
        return ResponseEntity
                .created(URI.create("/users/" + userResponse.getId()))
                .build();
    }

}
