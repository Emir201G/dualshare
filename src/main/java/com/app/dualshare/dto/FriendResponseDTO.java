package com.app.dualshare.dto;

public record FriendResponseDTO(
        String username,
        String photoURL,
        String shareCode,
        boolean enabled
) {


}
