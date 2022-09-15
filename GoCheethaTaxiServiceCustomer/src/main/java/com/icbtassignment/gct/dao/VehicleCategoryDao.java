package com.icbtassignment.gct.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import com.icbtassignment.gct.entities.VehicleCategory;
import com.icbtassignment.gct.utils.CustomException;

public class VehicleCategoryDao {

	public static List<VehicleCategory> getCategory(DataSource dataSource){
		 
		List<VehicleCategory> cities = new ArrayList<VehicleCategory>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from vehicle_category";
			stmt = con.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				String vehicle_category_Id = rset.getString("vehicle_category_Id");
				String vehicle_type_Name = rset.getString("vehicle_type_Name");
				String service_Charge = rset.getString("service_Charge");
				String charge_per_Km = rset.getString("charge_per_Km");
				VehicleCategory city = new VehicleCategory(vehicle_category_Id,vehicle_type_Name,service_Charge,charge_per_Km);
				cities.add(city);
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return cities;
	}
	
	public static List<VehicleCategory> getCategoryByCity(DataSource dataSource, String cityId){
		 
		List<VehicleCategory> cities = new ArrayList<VehicleCategory>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT driver_Id,driver.branch_Id,city.city_Name,driver.vehicle_category_Id,vehicle_category.vehicle_type_Name, vehicle_category.service_Charge,vehicle_category.charge_per_Km, driver_Name,phone_No,address,licence_No,password,vehicle_No FROM driver inner join branch on branch.branch_Id = driver.branch_Id inner join vehicle_category on vehicle_category.vehicle_category_Id = driver.vehicle_category_Id inner join city on city.city_Id = branch.city_Id where branch.city_Id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cityId);
			rset = stmt.executeQuery();
			
			while(rset.next()) {
				String vehicle_category_Id = rset.getString("vehicle_category_Id");
				String vehicle_type_Name = rset.getString("vehicle_type_Name");
				String service_Charge = rset.getString("service_Charge");
				String charge_per_Km = rset.getString("charge_per_Km");
				VehicleCategory city = new VehicleCategory(vehicle_category_Id,vehicle_type_Name,service_Charge,charge_per_Km);
				cities.add(city);
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return cities;
	}
	
	
	public static VehicleCategory get(DataSource dataSource,String vehiclecategoryId){
		 
		VehicleCategory vehiclecategories = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from vehicle_category where vehicle_category_Id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, vehiclecategoryId);
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				String vehicle_category_Id = rset.getString("vehicle_category_Id");
				String vehicle_type_Name = rset.getString("vehicle_type_Name");
				String service_Charge = rset.getString("service_Charge");
				String charge_per_Km = rset.getString("charge_per_Km");
				vehiclecategories = new VehicleCategory(vehicle_category_Id,vehicle_type_Name,service_Charge,charge_per_Km);
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return vehiclecategories;
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
