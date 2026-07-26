package com.app.dualshare.dto;

import com.app.dualshare.model.Story;
import com.app.dualshare.model.User;

public record SendStoryToDTO(
        Long id,
        Story story,
        User receiver
) {
}
