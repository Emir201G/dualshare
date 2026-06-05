package com.app.dualshare.service.interfaces;

import com.app.dualshare.dto.TokenRequestDTO;
import com.app.dualshare.dto.UserResponseDTO;
import com.google.api.client.auth.oauth2.TokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;

import java.io.IOException;

public interface IAuthService {
    UserResponseDTO verifyAuthenticate(TokenRequestDTO tokenRequest);
}
