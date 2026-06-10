package com.app.dualshare.service.interfaces;

import com.app.dualshare.dto.FriendRequestResponseDTO;
import com.app.dualshare.dto.FriendResponseDTO;
import com.app.dualshare.dto.UserResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface IUserService {
    UserResponseDTO updateUsername(String firebaseUid, String username);

    UserResponseDTO getProfileMyUser(String firebaseUid);

    String sendRequest(String code, String firebaseUid);

    void updateProfilePhoto(String firebaseUid, MultipartFile file);

    Set<FriendResponseDTO> getFriends(String firebaseUid);

    String acceptFriendRequest(String firebaseUid,String code);

    Set<FriendRequestResponseDTO> getFriendRequests(String firebaseUid);

}
