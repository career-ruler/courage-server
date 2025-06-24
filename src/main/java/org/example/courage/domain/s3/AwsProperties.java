package org.example.courage.domain.s3;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cloud.aws")
public class AwsProperties {

    private Credentials credentials;
    private S3 s3;

    // Getter와 Setter 메서드 추가

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public S3 getS3() {
        return s3;
    }

    public void setS3(S3 s3) {
        this.s3 = s3;
    }

    // 내부 클래스 Credentials
    public static class Credentials {
        private String accessKey;
        private String secretKey;

        // Getter와 Setter 메서드 추가
        public String getAccessKey() {
            return accessKey;
        }

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }
    }

    // 내부 클래스 S3
    public static class S3 {
        private String bucketName;
        private Region region;

        // Getter와 Setter 메서드 추가
        public String getBucketName() {
            return bucketName;
        }

        public void setBucketName(String bucketName) {
            this.bucketName = bucketName;
        }

        public Region getRegion() {
            return region;
        }

        public void setRegion(Region region) {
            this.region = region;
        }

        // 내부 클래스 Region
        public static class Region {
            private String staticRegion;

            public String getStaticRegion() {
                return staticRegion;
            }

            public void setStaticRegion(String staticRegion) {
                this.staticRegion = staticRegion;
            }
        }
    }
}
