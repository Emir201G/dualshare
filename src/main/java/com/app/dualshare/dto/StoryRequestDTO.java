package com.app.dualshare.dto;

import com.app.dualshare.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryRequestDTO {

    private MediaType mediaType;
}
