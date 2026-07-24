package com.app.dualshare.dto;

import lombok.Data;


public record FriendResponseDTO(
        String username,
        String photoURL,
        boolean enabled
) {


}
