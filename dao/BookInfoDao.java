package com.wyp.dao;

import java.util.List;



import java.sql.Date;
import java.util.*;


import com.wyp.model.BookInfo;
import com.wyp.model.BookType;
import com.wyp.util.JDBC;
import java.sql.ResultSet;

public class BookInfoDao {
	public static List selectBookInfo(){
		List list = new ArrayList();
		String sql = "select * from bookinfo";
		ResultSet rs = JDBC.executeQuery(sql);
		try{
			while(rs.next()){
				BookInfo bookinfo=new BookInfo();
				bookinfo.setISBN(rs.getString("ISBN"));
				bookinfo.setTypeId(rs.getString("typeid"));
				bookinfo.setBookName(rs.getString("bookname"));
				bookinfo.setWriter(rs.getString("writer"));
				bookinfo.setTranslator(rs.getString("translator"));
				bookinfo.setPublisher(rs.getString("publisher"));
				bookinfo.setDate(rs.getDate("date"));
				bookinfo.setPrice(rs.getDouble("price"));
				list.add(bookinfo); 
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			JDBC.close();
			return list;
		
	}



	public static List selectBookCategory(){
		List list=new ArrayList();
		String sql = "select * from booktype";
		ResultSet rs = JDBC.executeQuery(sql);
		try{
			while(rs.next()){
				BookType bookType=new BookType();
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



	public static int Insertbook(String ISBN,String typeId,String bookname,
		String writer,String translator,String publisher,Date date,Double price){
	int i=0;
	try{
		String sql="insert into bookinfo(ISBN,typeId,bookname,writer,translator,"+
	        "publisher,date,price) values('"+ISBN+"','"+typeId+"','"+bookname+"',"+
		    "'"+writer+"','"+translator+"','"+publisher+"','"+date+"',"+price+")";
		System.out.println(publisher);
		    i=JDBC.executeUpdate(sql);
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	JDBC.close();
	return i;
	}



	public static int Updatebook(String ISBN,String typeId,String bookname,
		     String writer,String translator,String publisher,Date date,Double price){
		int i=0;
		try{
		String sql="update bookinfo set ISBN='"+ISBN+"',typeId='"+typeId+"',bookname='"
		    +bookname+"',writer='"+writer+"',translator='"+translator+"',publisher='"
		    +publisher+"',date='"+date+"',price="+price+" where ISBN='"+ISBN+"'";
		    i=JDBC.executeUpdate(sql);
	    }catch(Exception e){
		     e.printStackTrace();
	     }
	    JDBC.close();
	    return i;
	}



	public static List selectBookInfo(String ISBN){
		List list = new ArrayList();
		String sql = "select * from bookinfo where ISBN='"+ISBN+"'";
		ResultSet rs = JDBC.executeQuery(sql);
		try{
			while(rs.next()){
				BookInfo bookinfo=new BookInfo();
				bookinfo.setISBN(rs.getString("ISBN"));
				bookinfo.setTypeId(rs.getString("typeid"));
				bookinfo.setBookName(rs.getString("bookname"));
				bookinfo.setWriter(rs.getString("writer"));
				bookinfo.setTranslator(rs.getString("translator"));
				bookinfo.setPublisher(rs.getString("publisher"));
				bookinfo.setDate(rs.getDate("date"));
				bookinfo.setPrice(rs.getDouble("price"));
				list.add(bookinfo); 
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			JDBC.close();
			return list;
		
	}


}
