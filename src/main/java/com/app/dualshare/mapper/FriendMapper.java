package com.app.dualshare.mapper;

import com.app.dualshare.dto.FriendResponseDTO;
import com.app.dualshare.dto.UserResponseDTO;
import com.app.dualshare.model.User;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface FriendMapper {

    FriendResponseDTO toResponseDTO(User user);

    Set<FriendResponseDTO> toResponseDTOList(Set<User> users);
}
