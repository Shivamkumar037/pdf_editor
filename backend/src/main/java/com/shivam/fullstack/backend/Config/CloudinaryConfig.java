package com.shivam.fullstack.backend.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dz6uwgiw8",
                "api_key", "749976993266581",
                "api_secret", "yCiglVYDBWp_4iPtidUVga-yprw"
        ));
    }
}
