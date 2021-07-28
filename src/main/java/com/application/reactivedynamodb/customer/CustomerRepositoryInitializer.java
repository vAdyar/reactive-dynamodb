package com.application.reactivedynamodb.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class CustomerRepositoryInitializer implements CommandLineRunner {
	
	private final DynamoDbAsyncClient asyncClient;
	private final DynamoDbEnhancedAsyncClient enhancedAsyncClient;
	
	
	public CustomerRepositoryInitializer( DynamoDbAsyncClient asyncClient, DynamoDbEnhancedAsyncClient enhancedAsyncClient ) {
		this.asyncClient = asyncClient;
		this.enhancedAsyncClient = enhancedAsyncClient;
	}
	
	@Override
	public void run( String... args ) {
		System.out.println("Creating tables if not present!!");
		CompletableFuture<ListTablesResponse> listTablesResponseCompletableFuture = asyncClient
				.listTables();
		CompletableFuture<List<String>> listCompletableFuture = listTablesResponseCompletableFuture
				.thenApply(ListTablesResponse::tableNames);
		
		listCompletableFuture
				.thenAccept(tables -> {
					if ( null != tables && !tables.contains(Customer.class.getSimpleName()) ) {
						DynamoDbAsyncTable<Customer> customer = enhancedAsyncClient
								.table(Customer.class.getSimpleName(), TableSchema.fromBean(Customer.class));
						
						customer.createTable();
					}
				});
	}
	
	
}
