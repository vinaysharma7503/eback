package com.ems.app.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of("us-west-2"))  // Set your AWS region
                .build();
    }
}
