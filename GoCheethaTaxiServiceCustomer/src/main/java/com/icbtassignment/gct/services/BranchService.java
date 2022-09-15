package com.icbtassignment.gct.services;

import java.util.List;

import javax.sql.DataSource;

import com.icbtassignment.gct.dao.BranchDao;
import com.icbtassignment.gct.entities.Branch;



public class BranchService {
	
	public static List<Branch> getBranch(DataSource dataSource,String city_Id){
		
		return BranchDao.getBranch(dataSource,city_Id);
	}


	
	
	
}
