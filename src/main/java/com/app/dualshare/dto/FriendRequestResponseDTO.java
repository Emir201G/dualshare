package com.app.dualshare.dto;


public record FriendRequestResponseDTO(
        Long id,
        String senderUsername,
        String senderPhotoUrl,
        String senderCode
) {


}
