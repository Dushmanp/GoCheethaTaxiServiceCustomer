package com.icbtassignment.gct.entities;

import javax.validation.constraints.NotBlank;

public class Customer {

	private String customer_Id;
	
	@NotBlank(message = "is required")
	private String customer_Name;
	
	@NotBlank(message = "is required")
	private String phone_No;
	
	@NotBlank(message = "is required")
	private String password;
	
	@NotBlank(message = "is required")
	private String email;
	
	public Customer(){

	}

	public Customer(String customer_Id) {
		super();
		this.customer_Id = customer_Id;
	}

	public Customer(String phone_No, String password) {
		super();
		this.phone_No = phone_No;
		this.password = password;
	}
	
	public Customer(String customer_Id, String customer_Name, String phone_No,String email) {
		super();
		this.customer_Id = customer_Id;
		this.customer_Name = customer_Name;
		this.phone_No = phone_No;
		this.email = email;
		
	}
	
	public Customer(String customer_Id, String customer_Name, String phone_No, String password,String email) {
		super();
		this.customer_Id = customer_Id;
		this.customer_Name = customer_Name;
		this.phone_No = phone_No;
		this.password = password;
		this.email = email;
	}

	public String getCustomer_Id() {
		return customer_Id;
	}

	public void setCustomer_Id(String customer_Id) {
		this.customer_Id = customer_Id;
	}

	public String getCustomer_Name() {
		return customer_Name;
	}

	public void setCustomer_Name(String customer_Name) {
		this.customer_Name = customer_Name;
	}

	public String getPhone_No() {
		return phone_No;
	}

	public void setPhone_No(String phone_No) {
		this.phone_No = phone_No;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [customer_Id=" + customer_Id + ", customer_Name=" + customer_Name + ", phone_No=" + phone_No
				+ ", password=" + password + ", email=" + email + "]";
	}

	
	
}
