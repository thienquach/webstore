package com.thienq.webstore.domain;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
public class Customer extends AbstractEntity {
	
	@Column(nullable = false)
	private String firstName, lastName;
	
	@Column(unique = true)
	private EmailAddress emailAddress;
	
	//One Customer can have multiple Addresses
	//the Addresses will be persisted, updated, deleted as well as the Customer
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	//this annotation will cause the persistent provider
	//to add a column 'customer_id' to Address table. This
	//additional column will then be used to refer to the 
	//Customer to allow joining table
	@JoinColumn(name = "customer_id")
	private Set<Address> addresses;
	
	/**
	 * Creates a new {@link Customer} from the given firstname and lastname.
	 * 
	 * @param firstname must not be {@literal null} or empty.
	 * @param lastname must not be {@literal null} or empty.
	 */
	public Customer(String firstName, String lastName) {

		Assert.hasText(firstName);
		Assert.hasText(lastName);

		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	protected Customer() {
		super();
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public EmailAddress getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(EmailAddress emailAddress) {
		this.emailAddress = emailAddress;
	}


	public Set<Address> getAddresses() {
		return Collections.unmodifiableSet(addresses);
	}


	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	
	public void addAddress(Address address){
		Assert.notNull(address);
		this.addresses.add(address);
	}

	
	
}
