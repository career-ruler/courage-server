package org.example.courage.global.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.example.courage.domain.s3.AwsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    private final AwsProperties awsProperties;

    public S3Config(AwsProperties awsProperties) {
        this.awsProperties = awsProperties;
    }

    @Bean
    public AmazonS3 amazonS3() {
        if (awsProperties.getCredentials() == null ||
                awsProperties.getCredentials().getAccessKey() == null ||
                awsProperties.getCredentials().getSecretKey() == null) {
            throw new IllegalArgumentException("AWS 자격 증명이 설정되지 않았습니다.");
        }

        if (awsProperties.getS3() == null ||
                awsProperties.getS3().getRegion() == null ||
                awsProperties.getS3().getRegion().getStaticRegion() == null) {
            throw new IllegalArgumentException("AWS 리전이 설정되지 않았습니다.");
        }

        AWSCredentials credentials = new BasicAWSCredentials(
                awsProperties.getCredentials().getAccessKey(),
                awsProperties.getCredentials().getSecretKey()
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(awsProperties.getS3().getRegion().getStaticRegion())
                .build();
    }

}
