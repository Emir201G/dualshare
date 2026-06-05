package com.app.dualshare.service.impl;

import com.app.dualshare.dto.FriendResponseDTO;
import com.app.dualshare.dto.UserResponseDTO;
import com.app.dualshare.exceptions.FirebaseAuthenticationException;
import com.app.dualshare.mapper.FriendMapper;
import com.app.dualshare.mapper.UserMapper;
import com.app.dualshare.model.User;
import com.app.dualshare.repository.UserRepository;
import com.app.dualshare.service.interfaces.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final FriendMapper friendMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, FriendMapper friendMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.friendMapper = friendMapper;
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
    public UserResponseDTO getUser(String firebaseUid) {

        User user = userRepository.findByFirebaseCode(firebaseUid)
                .orElseThrow(() -> new FirebaseAuthenticationException(firebaseUid));

        return userMapper.toResponseDTO(user);
    }

    @Override
    public FriendResponseDTO addFriend(String code, String firebaseUid) {

        User user = userRepository.findByFirebaseCode(firebaseUid)
                .orElseThrow(() -> new FirebaseAuthenticationException(firebaseUid));

        User friend = userRepository.findByFirebaseCode(code)
                .orElseThrow(() -> new FirebaseAuthenticationException(code));

        Set<User> friends = user.getFriends();
        if (friends == null) {
            friends = new HashSet<>();
        }

        friends.add(friend);

        user.setFriends(friends);

        userRepository.save(user);

        return friendMapper.toResponseDTO(user);
    }


}
