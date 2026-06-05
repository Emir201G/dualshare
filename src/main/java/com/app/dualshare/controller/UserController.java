package com.app.dualshare.controller;

import com.app.dualshare.dto.FriendResponseDTO;
import com.app.dualshare.dto.UpdateUsernameDTO;
import com.app.dualshare.dto.UserResponseDTO;
import com.app.dualshare.service.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private final IUserService userService;


    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PutMapping("/profile/update-username")
    public ResponseEntity<UserResponseDTO> updateUsername(@RequestBody UpdateUsernameDTO usernameDTO,
                                                          Authentication authentication
    ) {
        String firebaseUdi = authentication.getName();
        UserResponseDTO userResponseDTO = userService.updateUsername(firebaseUdi, usernameDTO.getUsername());
        return ResponseEntity.ok(userResponseDTO);
    }


    @GetMapping("/profile/user")
    public ResponseEntity<UserResponseDTO> getUser(Authentication authentication) {

        String firebaseUdi = authentication.getName();

        UserResponseDTO userResponseDTO = userService.getUser(firebaseUdi);

        return ResponseEntity.ok(userResponseDTO);
    }

    @PostMapping("/profile/add")
    public ResponseEntity<FriendResponseDTO> addFriend(@RequestParam String shareCode,
                                                       Authentication authentication) {

        FriendResponseDTO friendResponseDTO = userService.addFriend(shareCode, authentication.getName());

        return ResponseEntity.ok(friendResponseDTO);
    }

}
