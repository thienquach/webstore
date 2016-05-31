package com.thienq.webstore.domain;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="product")
public class Product extends AbstractEntity {

	private String code;

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private BigDecimal unitPrice;
	private String description;
	private String manufacturer;
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	private long unitsInStock;
	private long unitsInOrder;
	private boolean discontinued;
	private String status;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> attributes = new HashMap<String, String>();

	/**
	 * Creates a new {@link Product} from the given name and description.
	 * 
	 * @param code
	 * @param name
	 *            must not be {@literal null} or empty.
	 * @param unitPrice
	 *            must not be {@literal null} or less than or equal to zero.
	 */
	public Product(String code, String name, BigDecimal unitPrice) {
		Assert.hasText(name, "Name must not be null or empty");
		Assert.isTrue(BigDecimal.ZERO.compareTo(unitPrice) < 0,
				"Price must be greater than zero");
		this.code = code;
		this.name = name;
		this.unitPrice = unitPrice;
	}

	public Product(String name, BigDecimal unitPrice) {
		this(null, name, unitPrice);
	}

	public Product() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public long getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public long getUnitsInOrder() {
		return unitsInOrder;
	}

	public void setUnitsInOrder(long unitsInOrder) {
		this.unitsInOrder = unitsInOrder;
	}

	public boolean isDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String condition) {
		this.status = condition;
	}

	public Map<String, String> getAttributes() {
		return Collections.unmodifiableMap(attributes);
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Sets the attribute with the given key to the given value.
	 * 
	 * @param key
	 *            must not be {@literal null} or empty.
	 * @param value
	 */
	public void addAttribute(String key, String value) {
		Assert.hasText(key);

		if (value == null) {
			this.attributes.remove(key);
		} else {
			this.attributes.put(key, value);
		}
	}

	@Override
	public String toString() {
		return "Product [code=" + code + ", name=" + name + ", unitPrice="
				+ unitPrice + ", description=" + description
				+ ", manufacturer=" + manufacturer + ", category=" + category
				+ ", unitsInStock=" + unitsInStock + ", unitsInOrder="
				+ unitsInOrder + ", discontinued=" + discontinued
				+ ", condition=" + status + ", attributes=" + attributes + "]";
	}

}

