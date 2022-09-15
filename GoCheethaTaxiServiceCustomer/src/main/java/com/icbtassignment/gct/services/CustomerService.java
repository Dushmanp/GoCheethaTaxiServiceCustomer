package com.icbtassignment.gct.services;

import javax.sql.DataSource;

import com.icbtassignment.gct.dao.CityDao;
import com.icbtassignment.gct.dao.CustomerDao;
import com.icbtassignment.gct.entities.City;
import com.icbtassignment.gct.entities.Customer;

public class CustomerService {
	
	public static boolean checkCustomerPhoneNo(DataSource dataSource, String phone_No) {
		int phoneNo ;
			
		phoneNo =	CustomerDao.checkCustomerPhoneNo(dataSource,phone_No);
		
		if(phoneNo == 0) {
			
			return true ;
		}
		else {
			return false;
		}
		
	}
	
	public static boolean checkCredinatial(DataSource dataSource, Customer customer) {
		int checkCredinatial ;
			
		checkCredinatial =	CustomerDao.checkCredinatial(dataSource,customer);
		
		if(checkCredinatial == 1) {
			
			return true ;
		}
		else {
			return false;
		}
		
	}
	
	
	public static Customer getCustomer(DataSource dataSource, String phoneNo) {
		
		return CustomerDao.getCustomer(dataSource, phoneNo);
	}
	
	public static void addCustomer(DataSource dataSource, Customer customer) {
		CustomerDao.addCustomer(dataSource,customer);
		
	}

	public static void updateCustomerPassword(DataSource dataSource, String password,String customerId) {
		
		CustomerDao.updateCustomerPassword( dataSource, password, customerId);
		
		
		
	}
	

}
