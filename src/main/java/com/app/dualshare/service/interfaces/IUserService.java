package com.app.dualshare.service.interfaces;

import com.app.dualshare.dto.FriendResponseDTO;
import com.app.dualshare.dto.TokenRequestDTO;
import com.app.dualshare.dto.UserResponseDTO;

public interface IUserService {
    public UserResponseDTO updateUsername(String firebaseUid,String username);

    public UserResponseDTO getUser(String firebaseUid);

    public FriendResponseDTO addFriend(String code,String firebaseUid);
}
