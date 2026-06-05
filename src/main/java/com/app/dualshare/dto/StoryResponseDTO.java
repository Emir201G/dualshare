package com.app.dualshare.dto;

import com.app.dualshare.enums.MediaType;
import com.app.dualshare.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryResponseDTO {

    private Long id;
    private String mediaUrl;
    private MediaType mediaType;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
}
