package com.application.reactivedynamodb.customer;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class Address {
	
	private String city;
	
	private String state;
	
	private String country;
	//getters and setters
	
	
	public String getCity() {
		return city;
	}
	
	public void setCity( String city ) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState( String state ) {
		this.state = state;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry( String country ) {
		this.country = country;
	}
}