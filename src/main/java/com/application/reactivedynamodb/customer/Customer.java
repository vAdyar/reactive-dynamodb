package com.application.reactivedynamodb.customer;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Customer {
	
	private String customerID;
	
	private String fName;
	
	private String lName;
	
	private String contactNo;
	
	private Address address;
	
	private String createdTimeStamp;
	
	@DynamoDbPartitionKey
	@DynamoDbAttribute("CustomerID")
	public String getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID( String customerID ) {
		this.customerID = customerID;
	}
	
	@DynamoDbAttribute("CustomerFirstName")
	public String getfName() {
		return fName;
	}
	
	public void setfName( String fName ) {
		this.fName = fName;
	}
	
	@DynamoDbAttribute("CustomerLastName")
	public String getlName() {
		return lName;
	}
	
	public void setlName( String lName ) {
		this.lName = lName;
	}
	
	@DynamoDbAttribute("CustomerContactNumber")
	public String getContactNo() {
		return contactNo;
	}
	
	public void setContactNo( String contactNo ) {
		this.contactNo = contactNo;
	}
	
	@DynamoDbAttribute("CustomerAddress")
	public Address getAddress() {
		return address;
	}
	
	public void setAddress( Address address ) {
		this.address = address;
	}
	
	@DynamoDbAttribute("CustomerCreatedTime")
	public String getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	
	public void setCreatedTimeStamp( String createdTimeStamp ) {
		this.createdTimeStamp = createdTimeStamp;
	}
}