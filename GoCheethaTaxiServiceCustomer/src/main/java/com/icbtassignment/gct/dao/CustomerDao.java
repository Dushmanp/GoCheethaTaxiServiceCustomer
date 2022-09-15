package com.icbtassignment.gct.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.icbtassignment.gct.entities.City;
import com.icbtassignment.gct.entities.Customer;
import com.icbtassignment.gct.utils.CustomException;

public class CustomerDao {
	
	
	public static Customer getCustomer(DataSource dataSource, String phoneNo) {
		Customer cust = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from customer where phone_No = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, phoneNo);
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				String customer_Id = rset.getString("customer_Id");
				String customer_Name = rset.getString("customer_Name");
				String phone_No = rset.getString("phone_No");
				String email = rset.getString("email");
				cust = new Customer(customer_Id,customer_Name,phone_No,email);
				
				
			}	
			
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return cust;
	}
	
	
	public static int checkCustomerPhoneNo(DataSource dataSource, String phone_No) {
			
			Connection con = null;
			PreparedStatement stmt = null;
			ResultSet rset  = null;
			String sql = null;
			int returnVal = 0;
			
			try {
				con = dataSource.getConnection();
				sql= "select * from customer where phone_No = ?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, phone_No);
				rset= stmt.executeQuery();
				
				if(rset.next()) {
					returnVal = 1;
					
				}	
				else {
					returnVal = 0;
				}
				
				
			} catch (Exception e) {
				throw new CustomException(e.getMessage());
			}finally {
				
				close(con,stmt,null);
			}
			
			return returnVal;
		}
	
	public static int checkCredinatial(DataSource dataSource,Customer customer) {
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		int returnVal = 0;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from customer where phone_No = ? and password=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, customer.getPhone_No());
			stmt.setString(2, customer.getPassword());
			rset= stmt.executeQuery();
			
			if(rset.next()) {
				returnVal = 1;
				
			}	
			else {
				returnVal = 0;
			}
			
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return returnVal;
	}

	
	public static void addCustomer(DataSource dataSource, Customer customer) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "insert into customer (customer_Id,customer_Name,phone_No,password,email) values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer.getCustomer_Id());
			pstmt.setString(2, customer.getCustomer_Name());
			pstmt.setString(3, customer.getPhone_No());
			pstmt.setString(4, customer.getPassword());
			pstmt.setString(5, customer.getEmail());
			pstmt.execute();
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
				close(con,pstmt,null);
		}
		
	}
	

	public static void updateCustomerPassword(DataSource dataSource, String password,String customerId) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				con = dataSource.getConnection();
				sql= "UPDATE customer SET passwords=? WHERE customer_Id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, password);
				pstmt.setString(2, customerId);
			
				pstmt.execute();
				
			} catch (Exception e) {
				throw new CustomException(e.getMessage());
			}finally {
				
					close(con,pstmt,null);
			}
			
		}
		
	private static void close(Connection con,Statement stmt,ResultSet rset) {
		try {
			if(rset != null)
			{
				rset.close();
			}
			if(stmt != null)
			{
				stmt.close();
			}
			
			if(con != null)
			{
				con.close();
			}
			
		} catch (SQLException e) {
			throw new CustomException(e.getMessage());
		}
		
	}




}
