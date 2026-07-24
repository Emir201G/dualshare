package com.app.dualshare.dto;

import com.app.dualshare.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record UserResponseDTO(
        Long id,
        String username,
        String email,
        String shareCode,
        String photoUrl,
        String role,
        boolean enabled
) {

}
