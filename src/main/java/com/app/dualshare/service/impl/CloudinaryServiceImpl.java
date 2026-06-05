package com.app.dualshare.service.impl;

import com.app.dualshare.dto.CloudinaryResponseDTO;
import com.app.dualshare.exceptions.CloudinaryServiceException;
import com.app.dualshare.service.interfaces.ICloudinaryService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements ICloudinaryService {

    private final Cloudinary cloudinary;


    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public CloudinaryResponseDTO uploadFile(MultipartFile file) {

        try {
            Map<?, ?> result = cloudinary.uploader()
                    .upload(file.getBytes(), ObjectUtils.emptyMap());

            return new CloudinaryResponseDTO(
                    result.get("secure_url").toString(),
                    result.get("public_id").toString(),
                    result.get("resource_type").toString()
            );
        } catch (
                IOException e) {
            throw new CloudinaryServiceException(e.getMessage());
        }
    }

    @Override
    public void deletedFile(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

        } catch (
                IOException e) {
            throw new CloudinaryServiceException(e.getMessage());
        }
    }
}
