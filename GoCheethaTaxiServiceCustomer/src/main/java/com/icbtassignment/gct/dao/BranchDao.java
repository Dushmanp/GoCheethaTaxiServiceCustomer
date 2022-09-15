package com.icbtassignment.gct.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.icbtassignment.gct.entities.Branch;
import com.icbtassignment.gct.utils.CustomException;

public class BranchDao {
	public static List<Branch> getBranch(DataSource dataSource, String cityId){
		 
		List<Branch> branchs = new ArrayList<Branch>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT branch_Id,branch_Name,branch.city_Id,city.city_Name FROM branch"
					+ " INNER JOIN city on branch.city_Id =city.city_Id";
			stmt = con.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				String branch_Id = rset.getString("branch_Id");
				String branch_Name = rset.getString("branch_Name");
				String city_Id = rset.getString("city_Id");
				String city_Name = rset.getString("city_Name");
				Branch branch = new Branch(branch_Id,branch_Name,city_Id,city_Name);
				branchs.add(branch);
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return branchs;
	}

	public static Branch get(DataSource dataSource, String branchId) {
		Branch branch = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from branch where branch_Id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, branchId);
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				String branch_Id = rset.getString("branch_Id");
				String branch_Name = rset.getString("branch_Name");
				String city_Id = rset.getString("city_Id");
				branch = new Branch(branch_Id,branch_Name,city_Id);
				
				
			}	
			
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return branch;
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