package com.app.dualshare.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dhiwbjwtk",
                "api_key", "243421854899132",
                "api_secret", "MLVWg20OePFUEFwSDYRLH3EaY4E"));
    }
}
