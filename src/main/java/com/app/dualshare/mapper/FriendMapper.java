package com.app.dualshare.mapper;

import com.app.dualshare.dto.FriendResponseDTO;
import com.app.dualshare.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FriendMapper {

    FriendResponseDTO toResponseDTO(User user);
}
