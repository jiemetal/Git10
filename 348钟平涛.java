package com.inspur.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.inspur.javabean.OrderTb;


public class OrderTbDao {
	
	
	//根据用户ID查询订单信息
	public List<OrderTb> OrderTb1 (int  CustomerID) {
		List<OrderTb> or=new ArrayList<OrderTb>();

		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433; DatabaseName=jspdb","sa","heqijie15");
			String str="select * from CartTB,BookinformationTb,OrderTb  where OrderTb.CustomerID=? and CartTb.CartID=OrderTb.CartID and BookinformationTb.BookID=CartTB.BookID";
			pstmt = conn.prepareStatement(str);
			pstmt.setInt(1, CustomerID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) 
			{
				OrderTb ct=new OrderTb();
				 
				ct.setCartID(rs.getInt("CartID"));//购物车ID
				ct.setCustomerID(rs.getInt("CustomerID"));//用户ID
				ct.setOrderTID(rs.getInt("OrderID"));//订单编号
				ct.setBookName(rs.getString("BookName"));//书名
				ct.setBookPrice(rs.getInt("BookPrice"));//价格
				ct.setNumber(rs.getInt("Number"));//数量
				ct.setOrderDate(rs.getString("OrderDate"));//时间
				ct.setOrderStatus(rs.getInt("OrderStatus"));//状态
				ct.setSum(ct.getBookPrice()*ct.getNumber());

				or.add(ct);
			
				
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
		
	}
		return or;
		
}
	
	
	
	public boolean addOrderTb(int CustomerID,int CartID) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433; DatabaseName=jspdb","sa","heqijie15");
			String str="insert into OrderTb (CustomerID,CartID) values (?,?)";
			pstmt=conn.prepareStatement(str);
			
			pstmt.setInt(1,CustomerID);
			pstmt.setInt(2,CartID);
			
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
		
		
	}

}
