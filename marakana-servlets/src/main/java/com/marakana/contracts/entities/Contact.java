package com.marakana.contracts.entities;

public class Contact {
	
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	// ------------------
	
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	// ------------------	
	
	private Long addressId;
	
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	
	// =============================
	
	public Contact(){}	
	
	// ------------------
	
	public Contact(String name, Long addressId) {
		this.name = name;
		this.addressId = addressId;
	}

}
