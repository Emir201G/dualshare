package com.app.dualshare.mapper;

import com.app.dualshare.dto.UserResponseDTO;
import com.app.dualshare.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "role.roleType",target = "role")
    UserResponseDTO toResponseDTO(User user);
}
