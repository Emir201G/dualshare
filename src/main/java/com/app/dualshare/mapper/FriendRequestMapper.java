package com.app.dualshare.mapper;

import com.app.dualshare.dto.FriendRequestResponseDTO;
import com.app.dualshare.model.FriendRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface FriendRequestMapper {
    @Mapping(source = "sender.username", target = "senderUsername")
    @Mapping(source = "sender.photoUrl", target = "senderPhotoUrl")
    @Mapping(source = "sender.shareCode", target = "senderCode")
    FriendRequestResponseDTO toResponseDTO(FriendRequest friendRequest);

    @Mapping(source = "sender.username", target = "senderUsername")
    @Mapping(source = "sender.photoUrl", target = "senderPhotoUrl")
    @Mapping(source = "sender.shareCode", target = "senderCode")
    Set<FriendRequestResponseDTO> toResponseSetDTO(Set<FriendRequest> friendRequests);
}
