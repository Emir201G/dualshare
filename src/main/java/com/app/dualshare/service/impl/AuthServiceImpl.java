package com.app.dualshare.service.impl;

import com.app.dualshare.dto.TokenRequestDTO;
import com.app.dualshare.dto.UserResponseDTO;
import com.app.dualshare.enums.RoleType;
import com.app.dualshare.exceptions.FirebaseAuthenticationException;
import com.app.dualshare.mapper.UserMapper;
import com.app.dualshare.model.User;
import com.app.dualshare.repository.RoleRespository;
import com.app.dualshare.repository.UserRepository;
import com.app.dualshare.service.interfaces.IAuthService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements IAuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRespository roleRespository;

    public AuthServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleRespository roleRespository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRespository = roleRespository;
    }

    @Override
    public UserResponseDTO verifyAuthenticate(TokenRequestDTO tokenRequest) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance()
                    .verifyIdToken(tokenRequest.getFirebaseToken());

            String email = decodedToken.getEmail();
            String username = decodedToken.getName();

            User user = userRepository.findByEmail(email).orElseGet(
                    () -> {
                        User userNew = new User();
                        userNew.setEmail(email);
                        userNew.setUsername(username);
                        userNew.setRole(roleRespository.findByRoleType(RoleType.USER)
                                .orElseThrow((() -> new RuntimeException("User not found!"))));
                        userNew.setShareCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
                        userNew.setPhotoUrl(decodedToken.getPicture());
                        userNew.setEnabled(true);
                        userNew.setFirebaseCode(decodedToken.getUid());
                        return userRepository.save(userNew);
                    }
            );

            return userMapper.toResponseDTO(user);
        } catch (FirebaseAuthException e) {
            throw new FirebaseAuthenticationException(e.getMessage());
        }
    }


}