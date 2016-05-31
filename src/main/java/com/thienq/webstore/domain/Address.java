package com.thienq.webstore.domain;

import org.springframework.util.Assert;

import javax.persistence.Entity;

@Entity
public class Address extends AbstractEntity {
	
	private String street, city, country;
	
	/**
	 * Creates a new {@link Address} from the given street, city and country.
	 * 
	 * @param street must not be {@literal null} or empty.
	 * @param city must not be {@literal null} or empty.
	 * @param country must not be {@literal null} or empty.
	 */
	public Address(String street, String city, String country) {

		Assert.hasText(street, "Street must not be null or empty!");
		Assert.hasText(city, "City must not be null or empty!");
		Assert.hasText(country, "Country must not be null or empty!");

		this.street = street;
		this.city = city;
		this.country = country;
	}
	
	protected Address() {

	}
	
	/**
	 * Returns a copy of the current {@link Address} instance which is a new entity in terms of persistence.
	 * 
	 * @return
	 */
	public Address getCopy() {
		return new Address(this.street, this.city, this.country);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	

}
