package com.wyp.dao;

import java.sql.Date;
import java.util.*;


import com.wyp.model.BookInfo;
import com.wyp.model.BookType;
import com.wyp.util.JDBC;
import java.sql.ResultSet;

public class BookTypeDao {
	public static int Insertbooktype(int id,String typename,
			int days,float fk){
			int i=0;
			try{
				String sql="insert into booktype(id,typeName,days,fk) values("+id+",'"+typename+"',"+days+","+fk+")";
				i=JDBC.executeUpdate(sql);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			JDBC.close();
			return i;
		}

		public static int Updatebooktype(int id,String typename,
			int days,float fk){
			int i=0;
			try{
				String sql="update booktype set id="+id+",typeName='"+typename+"',days="+days+",fk="+fk+" where id="+id+"";
		        i=JDBC.executeUpdate(sql);
			}catch(Exception e){
				e.printStackTrace();
			}
			JDBC.close();
			return i;
		}

		public static List selectbooktype(){
			List list=new ArrayList();
			String sql="select * from booktype";
			ResultSet rs=JDBC.executeQuery(sql);
			try{
				while(rs.next()){
					BookType bookType = new BookType();
		            bookType.setId(rs.getString("id"));
		            bookType.setTypeName(rs.getString("typeName"));
		            bookType.setDays(rs.getString("days"));
		            bookType.setFk(rs.getString("fk"));
		            list.add(bookType);
				}
			}catch(Exception e){
					e.printStackTrace();
				}
				JDBC.close();
			    return list;
		}
        public static List selectBookTypeid(int id){
			List list = new ArrayList();
			String sql = "select * from booktype where id="+id+"";
			ResultSet rs = JDBC.executeQuery(sql);
			try{
				while(rs.next()){
					BookType bookType = new BookType();
				    bookType.setId(rs.getString("id"));
				    bookType.setTypeName(rs.getString("typeName"));
				    bookType.setDays(rs.getString("days"));
				    bookType.setFk(rs.getString("fk"));
				    list.add(bookType);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			JDBC.close();
			return list;
		}

}
