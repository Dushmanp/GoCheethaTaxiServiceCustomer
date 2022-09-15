package com.icbtassignment.gct.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import com.icbtassignment.gct.entities.Street;
import com.icbtassignment.gct.utils.CustomException;


public class StreetDao {

	
	public static List<Street> getStreet(DataSource dataSource, String cityId){
		 
		List<Street> streets = new ArrayList<Street>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT street_Id,street_Name,street.city_Id,city.city_Name FROM street"
					+ " INNER JOIN city on street.city_Id =city.city_Id";
			stmt = con.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				String street_Id = rset.getString("street_Id");
				String street_Name = rset.getString("street_Name");
				String city_Id = rset.getString("city_Id");
				String city_Name = rset.getString("city_Name");
				Street street = new Street(street_Id,street_Name,city_Id,city_Name);
				streets.add(street);
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return streets;
	}
	
	public static List<Street> getStreetByCity(DataSource dataSource, String cityId){
		 
		List<Street> streets = new ArrayList<Street>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT street_Id,street_Name,street.city_Id,city.city_Name FROM street"
					+ " INNER JOIN city on street.city_Id =city.city_Id where street.city_Id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cityId);
			rset = stmt.executeQuery();
			
			while(rset.next()) {
				String street_Id = rset.getString("street_Id");
				String street_Name = rset.getString("street_Name");
				String city_Id = rset.getString("city_Id");
				String city_Name = rset.getString("city_Name");
				Street street = new Street(street_Id,street_Name,city_Id,city_Name);
				streets.add(street);
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return streets;
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
			// TODO Auto-generated catch block
			throw new CustomException(e.getMessage());
		}
		
	}

}
