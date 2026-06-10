package com.app.dualshare.service.impl;

import com.app.dualshare.dto.CloudinaryResponseDTO;
import com.app.dualshare.dto.FriendRequestResponseDTO;
import com.app.dualshare.dto.FriendResponseDTO;
import com.app.dualshare.dto.UserResponseDTO;
import com.app.dualshare.enums.RequestStatus;
import com.app.dualshare.exceptions.*;
import com.app.dualshare.mapper.FriendMapper;
import com.app.dualshare.mapper.FriendRequestMapper;
import com.app.dualshare.mapper.UserMapper;
import com.app.dualshare.model.FriendRequest;
import com.app.dualshare.model.User;
import com.app.dualshare.repository.FriendRequestRepository;
import com.app.dualshare.repository.UserRepository;
import com.app.dualshare.service.interfaces.ICloudinaryService;
import com.app.dualshare.service.interfaces.IUserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final FriendMapper friendMapper;
    private final ICloudinaryService cloudinaryService;
    private final FriendRequestMapper friendRequestMapper;
    private final FriendRequestRepository friendRequestRepository;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           FriendMapper friendMapper,
                           ICloudinaryService cloudinaryService,
                           FriendRequestRepository friendRequestRepository,
                           FriendRequestMapper friendRequestMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.friendMapper = friendMapper;
        this.cloudinaryService = cloudinaryService;
        this.friendRequestRepository = friendRequestRepository;
        this.friendRequestMapper = friendRequestMapper;
    }

    @Transactional
    @Override
    public UserResponseDTO updateUsername(String firebaseUid, String username) {

        if (username == null || username.trim().isEmpty()) {

            throw new IllegalArgumentException("Invalid username provided");

        }

        User user = userRepository.findByFirebaseCode(firebaseUid)
                .orElseThrow(() -> new FirebaseAuthenticationException(firebaseUid));

        user.setUsername(username);

        userRepository.save(user);

        return userMapper.toResponseDTO(user);
    }

    @Override
    public UserResponseDTO getProfileMyUser(String firebaseUid) {

        User user = userRepository.findByFirebaseCode(firebaseUid)
                .orElseThrow(() -> new FirebaseAuthenticationException(firebaseUid));

        return userMapper.toResponseDTO(user);
    }

    @Override
    public String sendRequest(String code, String firebaseUid) {

        User sender = userRepository.findByFirebaseCode(firebaseUid)
                .orElseThrow(() -> new FirebaseAuthenticationException(firebaseUid));

        User receiver = userRepository.findByShareCode(code)
                .orElseThrow(() -> new UserNotFoundByShareCodeException(code));

        if (sender.getFriends().contains(receiver)) {
            throw new AlreadyFriendsException(receiver.getUsername());
        }

        if (friendRequestRepository.existsBySenderAndReceiverAndStatus(sender, receiver, RequestStatus.PENDING)) {
            throw new FriendRequestAlreadyExistsException(receiver.getUsername());
        }
        FriendRequest friendship = new FriendRequest();

        friendship.setSender(sender);
        friendship.setReceiver(receiver);
        friendship.setStatus(RequestStatus.PENDING);

        friendRequestRepository.save(friendship);

        return "Request sent";
    }

    @Override
    public void updateProfilePhoto(String firebaseUid, MultipartFile file) {

        User user = userRepository.findByFirebaseCode(firebaseUid)
                .orElseThrow(() -> new UserNotFoundByShareCodeException(firebaseUid));

        if (user.getPhotoPublicId() != null) {
            cloudinaryService.deletedFile(user.getPhotoPublicId(), "image");
        }

        CloudinaryResponseDTO cloudinaryResponseDTO = cloudinaryService.uploadFile(file);

        user.setPhotoPublicId(cloudinaryResponseDTO.getPublicId());

        user.setPhotoUrl(cloudinaryResponseDTO.getUrl());

        userRepository.save(user);

    }

    @Override
    public Set<FriendResponseDTO> getFriends(String firebaseUid) {

        User user = userRepository.findByFirebaseCode(firebaseUid)
                .orElseThrow(() -> new UserNotFoundByShareCodeException(firebaseUid));

        Set<User> friends = user.getFriends();

        return friendMapper.toResponseDTOList(friends);
    }

    @Override
    public String acceptFriendRequest(String firebaseUid, String code) {

        User receiver = userRepository.findByFirebaseCode(firebaseUid)
                .orElseThrow(() -> new UserNotFoundByUidException(firebaseUid));


        User sender = userRepository.findByShareCode(code)
                .orElseThrow(() -> new UserNotFoundByShareCodeException(code));

        FriendRequest friendship = friendRequestRepository.findBySenderAndReceiver(sender, receiver)
                .orElseThrow(() -> new FriendRequestAlreadyExistsException(receiver.getUsername()));

        receiver.getFriends().add(sender);
        sender.getFriends().add(receiver);

        friendRequestRepository.delete(friendship);
        return "Accept request sent";
    }

    @Transactional(readOnly = true)
    @Override
    public Set<FriendRequestResponseDTO> getFriendRequests(String firebaseUid) {

        User receiver = userRepository.findByFirebaseCode(firebaseUid)
                .orElseThrow(() -> new UserNotFoundByUidException(firebaseUid));

        return receiver.getFriendRequests().stream()
                .map(friendRequestMapper::toResponseDTO)
                .collect(Collectors.toSet());
    }

}
