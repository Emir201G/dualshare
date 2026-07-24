package com.app.dualshare.controller;

import com.app.dualshare.dto.FriendRequestResponseDTO;
import com.app.dualshare.dto.FriendResponseDTO;
import com.app.dualshare.dto.UpdateUsernameDTO;
import com.app.dualshare.dto.UserResponseDTO;
import com.app.dualshare.service.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

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
        UserResponseDTO userResponseDTO = userService.updateUsername(firebaseUdi, usernameDTO.username());
        return ResponseEntity.ok(userResponseDTO);
    }

    @PutMapping("/profile/update-photo")
    public ResponseEntity<?> updateProfilePhot(@RequestParam("storyFile") MultipartFile multipartFile
            , Authentication authentication) {
        String firebaseUdi = authentication.getName();
        userService.updateProfilePhoto(firebaseUdi, multipartFile);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/profile/user")
    public ResponseEntity<UserResponseDTO> getUser(Authentication authentication) {

        String firebaseUdi = authentication.getName();

        UserResponseDTO userResponseDTO = userService.getProfileMyUser(firebaseUdi);

        return ResponseEntity.ok(userResponseDTO);
    }

    @PostMapping("/profile/send-request")
    public ResponseEntity<?> sendRequest(
            @RequestParam("code") String code,
            Authentication authentication) {

        String firebaseUdi = authentication.getName();

        userService.sendRequest(code, firebaseUdi);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/profile/accept-request")
    public ResponseEntity<?> acceptFriendRequest(
            @RequestParam("code") String code,
            Authentication authentication
    ) {
        String firebaseUdi = authentication.getName();

        userService.acceptFriendRequest(firebaseUdi, code);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/profile/reject-request/")
    public ResponseEntity<?> rejectRequest(
            @RequestParam("code") String code,
            Authentication authentication
    ) {

        String firebaseUdi = authentication.getName();

        userService.rejectFriendRequest(firebaseUdi, code);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile/get-friends")
    public ResponseEntity<Set<FriendResponseDTO>> getFriends(
            Authentication authentication
    ) {
        String firebaseUdi = authentication.getName();

        return ResponseEntity.ok(userService.getFriends(firebaseUdi));
    }

    @GetMapping("/profile/get-friend-request")
    public ResponseEntity<Set<FriendRequestResponseDTO>> getFriendRequests(
            Authentication authentication
    ) {
        String firebaseUdi = authentication.getName();

        return ResponseEntity.ok(userService.getFriendRequests(firebaseUdi));
    }
}
