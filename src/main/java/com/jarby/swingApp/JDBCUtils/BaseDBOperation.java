package com.jarby.swingApp.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jarby.swingApp.entity.Book;

public class BaseDBOperation {
	
	DBConnection dbc = null;
	
	public BaseDBOperation() {
		dbc = new DBConnection();		
	}
	
	/**
	* Function description： execute sql script and implement the operations to book table
	* Author：Huimin Liu
	* Create time：May 21, 2017 12:22:54 PM
	* Version：V1.0 
	*/
	public int execUpdate(String sql) throws SQLException{
		int result=0;
		Statement statement=null;
		Connection conn = dbc.getConnection();
		try{
			statement=conn.createStatement();
			result=statement.executeUpdate(sql);			
		}catch(SQLException e){
			System.out.println(e);
		}
		finally{
			if(statement!=null){
				statement.close();				
			}
			dbc.closeConnection();
		}
		return result;		
	}
	
	/**
	* Function description：search book records according to specific conditions
	* Author：Huimin Liu
	* Create time：May 21, 2017 12:24:10 PM
	* Version：V1.0 
	*/
	public List<Book> Query (String sqlString) throws SQLException{
		List<Book> list=new ArrayList<Book>();
		Statement statement=null;
		ResultSet rs=null;
		Connection conn = dbc.getConnection();
		try{
			statement=conn.createStatement();
			rs=statement.executeQuery(sqlString);
			while(rs.next()){
				Book s=new Book();
				s.setBookid(Integer.parseInt(rs.getString("")));
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			if(statement!=null){
				statement.close();
			}
			if(rs!=null){
				rs.close();
			}
			dbc.closeConnection();
		}
		return list;
	}

}
