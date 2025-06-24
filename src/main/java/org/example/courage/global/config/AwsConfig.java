package org.example.courage.global.config;

import lombok.Getter;
import org.example.courage.domain.s3.AwsProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AwsProperties.class)
@Getter
public class AwsConfig {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    @Value("${cloud.aws.s3.region.staticRegion}")
    private String region;

    @Value("${cloud.aws.s3.stack.auto}")
    private boolean stackAuto;
}

