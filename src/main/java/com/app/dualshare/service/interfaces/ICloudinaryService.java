package com.app.dualshare.service.interfaces;

import com.app.dualshare.dto.CloudinaryResponseDTO;
import com.app.dualshare.exceptions.CloudinaryServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ICloudinaryService {

    CloudinaryResponseDTO uploadFile(MultipartFile file);

    void deletedFile(String publicId, String resourceType);



}
