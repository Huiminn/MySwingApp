package com.huimin.swingApp.ModelConvert;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.mysql.jdbc.RowData;


/**
* Function description： 
* Author：Jarby
* Create time：May 26, 2017 9:38:40 PM
* Version：V1.0 
*/
public class ModelConvert {
	
	/**
	* Function description： convert ResultSet data to list<Map> 
	* Author：Huimin Liu
	* Create time：May 26, 2017 9:29:54 PM
	* Version：V1.0 
	*/
	public static List<Map<String, Object>> convertList(ResultSet rs){
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		Map<String, Object> rowData; 
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			
			//get column/fields numbers
			int columnCount = rsmd.getColumnCount();
			
			while(rs.next()){			
				rowData = new HashMap<String, Object>();
				for(int i=1; i <= columnCount; i++){					
					rowData.put(rsmd.getColumnName(i), rs.getObject(i));					
				}					
				dataList.add(rowData);			
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(!(rs==null)){
				try {
					rs.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		return dataList;
	}
	
	
	/**
	* Function description：  convert ResultSet data to Map
	* Author：Huimin Liu
	* Create time：May 26, 2017 9:38:43 PM
	* Version：V1.0 
	*/
	public static Map<String, Object> convertMap(ResultSet rs){
		
		Map<String, Object> dataMap = new TreeMap<String, Object>();
		
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			
			//get column/fields numbers
			int columnCount = rsmd.getColumnCount();
			while(rs.next()){
				for(int i=0; i < columnCount; i++)
					dataMap.put(rsmd.getColumnName(i), rs.getObject(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(!(rs==null)){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return dataMap;
	}
}
