package com.app.dualshare.dto;

import com.app.dualshare.enums.MediaType;
import com.app.dualshare.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


public record StoryResponseDTO(
        Long id,
        String mediaUrl,
        MediaType mediaType,
        LocalDateTime createdAt,
        LocalDateTime expiresAt
) {


}
