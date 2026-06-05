package com.app.dualshare.dto;

import com.app.dualshare.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String shareCode;
    private String photoUrl;
    private RoleType role;
    private String token;
    private boolean enabled;
}
