package com.joaocdfarias.notification.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@Configuration
public class AmazonSnsConfiguration {

  @Value("${aws.accessKey}")
  private String accessKey;

  @Value("${aws.secretKey}")
  private String secretKey;

  @Bean
  public AWSCredentials awsCredentials() {
    return new BasicAWSCredentials(accessKey, secretKey);
  }

  @Bean
  public AmazonSNS amazonSNS() {
    AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(awsCredentials());
    return AmazonSNSClientBuilder.standard()
        .withCredentials(credentialsProvider)
        .withRegion(Regions.SA_EAST_1)
        .build();
  }
}
