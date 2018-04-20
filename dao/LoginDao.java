package com.wyp.dao;



import com.wyp.model.Operater;
import com.wyp.util.*;
import java.sql.ResultSet;

public class LoginDao {
	public static Operater check(String name,String password){
		Operater operater = new Operater();
		String sql = "select * from operater where name='" + name 
		        +"'and password='" + password + "'and admin=1";
	    ResultSet rs = JDBC.executeQuery(sql);
	    try{
	    	while(rs.next()){
	    		operater.setId(rs.getString("id"));
	    		operater.setName(rs.getString("name"));
	    		operater.setGrade(rs.getString("admin"));
	    		operater.setPassword(rs.getString("password"));
	    	}
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    JDBC.close();        //关闭连接
	    return operater;
	}

}
