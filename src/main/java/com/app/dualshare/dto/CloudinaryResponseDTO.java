package com.app.dualshare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record CloudinaryResponseDTO(
        String url,
        String publicId,
        String resourceType
) {

}
