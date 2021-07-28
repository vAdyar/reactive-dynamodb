package com.application.reactivedynamodb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClientBuilder;

import java.net.URI;

@Configuration
public class DynamoDbConfig {
	
	@Value("${aws.accessKey}")
	String accessKey;
	
	@Value("${aws.secretKey}")
	String secretKey;
	
	@Value("${dynamodb.endpoint:}")
	String dynamoEndpoint;
	
	@Bean
	public DynamoDbEnhancedAsyncClient getDynamoDbEnhancedAsyncClient() {
		return DynamoDbEnhancedAsyncClient.builder()
				.dynamoDbClient(dynamoDbAsyncClient(awsBasicCredentials()))
				.build();
	}
	
	@Bean
	DynamoDbAsyncClient dynamoDbAsyncClient( AwsBasicCredentials awsBasicCredentials ) {
		DynamoDbAsyncClientBuilder clientBuilder = DynamoDbAsyncClient.builder().region(Region.US_EAST_1);
		clientBuilder
				.credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials));
		
		if ( !dynamoEndpoint.isEmpty() ) {
			clientBuilder.endpointOverride(URI.create(dynamoEndpoint));
		}
		
		return clientBuilder.build();
	}
	
	@Bean
	AwsBasicCredentials awsBasicCredentials() {
		return AwsBasicCredentials.create(accessKey, secretKey);
	}
}