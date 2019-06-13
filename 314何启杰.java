package com.inspur.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.inspur.javabean.*;


public class Dao {
	public List<Model> SelectDao(){
		Connection conn=DbHelper.getConnection();
		String sql="select BookID,BookName,BookPrice,BookStoremount from BookinformationTb";
		List<Model> list=new ArrayList<>();
		PreparedStatement pst=null;
		ResultSet rst=null;
		try {
			pst=conn.prepareStatement(sql);
			rst=pst.executeQuery();
			while (rst.next()) {
				Model m=new Model();
				m.setBookID(rst.getInt("BookID"));
				m.setBookName(rst.getString("BookName"));
				m.setBookPrice(rst.getString("BookPrice"));
				m.setBookStoremount(rst.getInt("BookStoremount"));
				list.add(m);
			}
			rst.close();
			pst.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return list;
		
	}
	
	public SelectModel SelectById(int id) {
		Connection conn=DbHelper.getConnection();
		String sql  = "select * from BookinformationTb where BookId = "+id;
		SelectModel sml=null;
		PreparedStatement pst=null;
		ResultSet rst=null;
		try {
			pst=conn.prepareStatement(sql);
			rst=pst.executeQuery();
			while(rst.next()) {
				sml=new SelectModel();
				sml.setBookId(rst.getInt("BookId"));
				sml.setBookTypeId(rst.getInt("BookTypeId"));
				sml.setBookName(rst.getString("BookName"));
				sml.setBookStoretime(rst.getString("BookStoretime"));
				sml.setBookAuthor(rst.getString("BookAuthor"));
				sml.setBookTanslor(rst.getString("BookTanslor"));
				sml.setBookisbn(rst.getString("Bookisbn"));
				sml.setBookPrice(rst.getString("BookPrice"));
				sml.setBookStoremount(rst.getInt("BookStoremount"));
				sml.setBookPress(rst.getString("BookPress"));
				sml.setBookPubDate(rst.getString("BookPubDate"));
				sml.setBookPic(rst.getString("BookPic"));
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return sml;
		}
	
	
	
	public boolean updateDao(updateModel uml){  //修改
        String sql = "UPDATE BookinformationTb SET BookTypeID=?,BookName=?,BookStoretime=?,BookAuthor=?,BookTanslor=?,"
        		+ "Bookisbn=?,BookPrice=?,BookStoremount=?,BookPress=?,BookPubDate=?,BookPic=? WHERE BookId= ?";  //修改的SQL语句，根据ID修改
        Connection conn = DbHelper.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, uml.getBookTypeId());
            pst.setString(2, uml.getBookName());
            pst.setString(3, uml.getBookStoretime());
            pst.setString(4, uml.getBookAuthor());
            pst.setString(5, uml.getBookTanslor());
            pst.setString(6, uml.getBookisbn());
            pst.setString(7, uml.getBookPrice());
            pst.setInt(8, uml.getBookStoremount());
            pst.setString(9, uml.getBookPress());
            pst.setString(10, uml.getBookPubDate());
            pst.setString(11, uml.getBookPic());
            pst.setInt(12, uml.getBookId());
            
            
         
            int count = pst.executeUpdate();
            pst.close();  //关闭
            return count>0?true:false;  //是否修改的判断
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	public boolean addDao(addModel aml){  //添加信息
        String sql = "INSERT INTO BookinformationTb(BookTypeID,BookName,BookPrice,BookAuthor,"
        		+ "BookTanslor,Bookisbn,BookStoremount,BookPress,"
        		+ "BookPubDate,BookPic,BookStoretime) VALUES (?,?,?,?,?,?,?,?,?,?,?)";  //添加的SQL语句
        Connection conn = DbHelper.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, aml.getBookTypeId());
            pst.setString(2, aml.getBookName());
            pst.setString(3, aml.getBookPrice());
            pst.setString(4, aml.getBookAuthor());
            pst.setString(5, aml.getBookTanslor());
            pst.setString(6, aml.getBookisbn());
            pst.setInt(7, aml.getBookStoremount());
            pst.setString(8, aml.getBookPress());
            pst.setString(9, aml.getBookPubDate());
            pst.setString(10, aml.getBookPic());
            pst.setString(11, aml.getBookStoretime());
                 
            int count = pst.executeUpdate();
            pst.close();
            return count>0?true:false;  //是否添加的判断
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	
	public boolean deleteDao(int id){  //删除
        String sql = "delete from BookinformationTb where BookID=?";  //删除的SQL语句，根据ID删除
        Connection conn = DbHelper.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            int count = pst.executeUpdate();
            pst.close();
            return count>0?true:false;  //是否删除的判断
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	
	//Type
	public List<TypeModel> TypeSelectDao(){
		Connection conn=DbHelper.getConnection();
		String sql="select * from BooktypeinfoTb";
		List<TypeModel> list=new ArrayList<>();
		PreparedStatement pst=null;
		ResultSet rst=null;
		try {
			pst=conn.prepareStatement(sql);
			rst=pst.executeQuery();
			while (rst.next()) {
				TypeModel m=new TypeModel();
				m.setBookTypeID(rst.getInt("BookTypeID"));
				m.setBookTypeName(rst.getString("BookTypeName"));
				list.add(m);
			}
			rst.close();
			pst.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return list;
		
	}
	
	
	
	
	
	public boolean TypeUpdateDao(TypeUpdateModel uml){  //修改
        String sql = "UPDATE BooktypeinfoTb SET BookTypeName=? WHERE BookTypeID=?";  //修改的SQL语句，根据ID修改
        Connection conn = DbHelper.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, uml.getBookTypeName());
           
            pst.setInt(2, uml.getBookTypeID());
            
            
            System.out.println(uml.getBookTypeName());
            int count = pst.executeUpdate();
            pst.close();  //关闭
            return count>0?true:false;  //是否修改的判断
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	
	public TypeModel TypeSelectById(int id) {
		Connection conn=DbHelper.getConnection();
		String sql  = "select * from BooktypeinfoTb where BookTypeID = "+id;
		TypeModel tml=null;
		PreparedStatement pst=null;
		ResultSet rst=null;
		try {
			pst=conn.prepareStatement(sql);
			rst=pst.executeQuery();
			while(rst.next()) {
				tml=new TypeModel();
				tml.setBookTypeID(rst.getInt("BookTypeID"));
				tml.setBookTypeName(rst.getString("BookTypeName"));
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return tml;
		}
	
	
	
	
	public boolean addTypeDao(TypeModel tml){  //添加信息
        String sql = "INSERT INTO BooktypeinfoTb(BookTypeName) VALUES (?)";  //添加的SQL语句
        Connection conn = DbHelper.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, tml.getBookTypeName());
            
                 
            int count = pst.executeUpdate();
            pst.close();
            return count>0?true:false;  //是否添加的判断
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	
	public boolean TypeDeleteDao(int id){  //删除
        String sql = "delete from BooktypeinfoTb where BookTypeID=?";  //删除的SQL语句，根据ID删除
        Connection conn = DbHelper.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            int count = pst.executeUpdate();
            pst.close();
            return count>0?true:false;  //是否删除的判断
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	public List<orderModel> OrderSelectDao(){//查询订单表
		Connection conn=DbHelper.getConnection();
		String sql="select OrderID,OrderStatus,OrderDate from OrderTb";
		List<orderModel> list=new ArrayList<>();
		PreparedStatement pst=null;
		ResultSet rst=null;
		try {
			pst=conn.prepareStatement(sql);
			rst=pst.executeQuery();
			while (rst.next()) {
				orderModel m=new orderModel();
				m.setOrderID(rst.getInt("OrderID"));
				m.setOrderStatus(rst.getInt("OrderStatus"));
				m.setOrderDate(rst.getDate("OrderDate"));
				list.add(m);
			}
			rst.close();
			pst.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return list;
		
	}
	
	
	public OrderSelByIdModel OrderSelectById(int id) {//根据Id查询
		Connection conn=DbHelper.getConnection();
		String sql  = "select OrderID,OrderStatus from OrderTb where OrderID = "+id;
		OrderSelByIdModel osml=null;
		PreparedStatement pst=null;
		ResultSet rst=null;
		try {
			pst=conn.prepareStatement(sql);
			rst=pst.executeQuery();
			while(rst.next()) {
				osml=new OrderSelByIdModel();
				osml.setOrderID(rst.getInt("OrderID"));
				osml.setOrderStatus(rst.getInt("OrderStatus"));
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return osml;
	}
	
	
	
	public boolean OrderUpdateDao(OrderUpdateModel oml){  //订单表状态修改
        String sql = "UPDATE OrderTb SET OrderStatus=? WHERE OrderID=?";  //修改的SQL语句，根据ID修改
        Connection conn = DbHelper.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setInt(1, oml.getOrderStatus());
           
            pst.setInt(2, oml.getOrderID());
            
            
            System.out.println(oml.getOrderID());
            int count = pst.executeUpdate();
            pst.close();  //关闭
            return count>0?true:false;  //是否修改的判断
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	
	public boolean OrderDeleteDao(int id){  //删除
        String sql = "delete from OrderTb where OrderID=?";  //删除的SQL语句，根据ID删除
        Connection conn = DbHelper.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            int count = pst.executeUpdate();
            pst.close();
            return count>0?true:false;  //是否删除的判断
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
}
