package com.inspur.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import javax.servlet.http.Cookie;

import com.inspur.javabean.*;
public class shoppingdao {
	
	//管理员登录
public static int gmlogin(gmloginjavabean gml) {
		
		int result=-1;
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433; DatabaseName=jspdb","sa","heqijie15");
			String str="select count(*) LoginName from AdminTb where LoginName=? and LoginPwd=?";
			pstmt = conn.prepareStatement(str);
			pstmt.setString(1, gml.getGmusername());
			pstmt.setString(2, gml.getGmpassword());
			rs=pstmt.executeQuery();
			 
			
			while(rs.next()) {
				result=rs.getInt(1);
				
			}
			if(result>0) {
				return 1;
			}else {
				return 0;
			}
			
			
			
			}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
		
		
	}
	//
	//登录验证
	public static int shoppingdao(Logina lg) {
		
		int result=-1;
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433; DatabaseName=jspdb","sa","heqijie15");
			String str="select count(*) LoginName from CustomerTb where LoginName=? and LoginPwd=?";
			pstmt = conn.prepareStatement(str);
			pstmt.setString(1, lg.getUsername1());
			pstmt.setString(2, lg.getPwd1());
			rs=pstmt.executeQuery();
			 
			
			while(rs.next()) {
				result=rs.getInt(1);
				
				
			}
			if(result>0) {
				return 1;
			}else {
				return 0;
			}
			
			
			
			}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
		
		
	}
	
	//注册
	
	public static int  zhuce(Logina lg1) {
		int flag=-1;
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 
		 if(lg1.getPwd1().equals(lg1.getPwd2())) {
			
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433; DatabaseName=jspdb","sa","heqijie15");
			String str="insert into CustomerTb values(?,?)";
			pstmt = conn.prepareStatement(str);
			pstmt.setString(1, lg1.getUsername1());
			pstmt.setString(2, lg1.getPwd1());
			
			pstmt.executeUpdate();
		
			
			
			
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
		flag=1;
		
	}
		 else {
			 	 
		 }
		return flag;
	}
	
	//查询全部书籍信息
	
	public  List<queall> allbook() {
		 List<queall> qas= new ArrayList<queall>();
		
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433; DatabaseName=jspdb","sa","heqijie15");
			String str="select *  from BookinformationTb ";
			pstmt = conn.prepareStatement(str);
			rs=pstmt.executeQuery();
			while(rs.next()) 
			{
				queall qa=new queall();
				qa.setBookid(rs.getInt("BookID"));
				qa.setBooktypeid(rs.getInt("BookTypeId"));
				qa.setBookname(rs.getString("BookName"));
				qa.setBookprice(rs.getString("BookPrice"));
				qa.setBookauthor(rs.getString("BookAuthor"));
				qa.setBooktanslor(rs.getString("BookTanslor"));
				qa.setBookisbn(rs.getString("Bookisbn"));
				qa.setBookstoremount(rs.getString("BookStoremount"));
				qa.setBookpress(rs.getString("BookPress"));
				qa.setBookpubdate(rs.getString("BookPubDate"));
				qa.setBookstoretime(rs.getString("BookStoretime"));
				qa.setBookpic(rs.getString("bookpic"));
				
			
				qas.add(qa);
				
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
		return qas;
		
}
	
	
	//根据id查书籍
	
		public  queall queid(int  bookid) {
			
			Connection conn = null;
			 PreparedStatement pstmt = null;
			 ResultSet rs=null;
			 queall ql=null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433; DatabaseName=jspdb","sa","heqijie15");
				String str="select *  from BookinformationTb where BookID=?"  ;
				pstmt = conn.prepareStatement(str);
				pstmt.setInt(1, bookid);
				rs=pstmt.executeQuery();
				if(rs.next()) 
				{
					 ql=new queall();
					 
					ql.setBooktypeid(rs.getInt("BookTypeId"));
					ql.setBookname(rs.getString("BookName"));
					ql.setBookprice(rs.getString("BookPrice"));
					ql.setBookauthor(rs.getString("BookAuthor"));
					ql.setBooktanslor(rs.getString("BookTanslor"));
					ql.setBookisbn(rs.getString("Bookisbn"));
					ql.setBookstoremount(rs.getString("BookStoremount"));
					ql.setBookpress(rs.getString("BookPress"));
					ql.setBookpubdate(rs.getString("BookPubDate"));
					ql.setBookstoretime(rs.getString("BookStoretime"));
					ql.setBookpic(rs.getString("bookpic"));
					
					
				
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
			return ql;
			
	}
		
		//查询用户全部个人信息
		public List<customerifo> customerifo() {
			 List<customerifo> cus= new ArrayList<customerifo>();
				
				Connection conn = null;
				 PreparedStatement pstmt = null;
				 ResultSet rs=null;
				 
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433; DatabaseName=jspdb","sa","heqijie15");
					String str="select *  from CustomerinfoTb ";
					pstmt = conn.prepareStatement(str);
					rs=pstmt.executeQuery();
					while(rs.next()) 
					{
						customerifo cuss=new customerifo();
						cuss.setCustomerinfoid(rs.getInt("customerinfoid"));
						cuss.setCustomerName(rs.getString("CustomerName"));												
						cuss.setTruename(rs.getString("truename"));						
						cuss.setSex(rs.getString("sex"));					
						cuss.setPhone(rs.getString("phone"));						
						cuss.setCusaddress(rs.getString("cusaddress"));						
						cuss.setWechat(rs.getString("wechat"));						
						cuss.setQq(rs.getString("qq"));
						cuss.setEmail(rs.getString("email"));
					//System.out.println(rs.getInt("customerinfoid"));
						cus.add(cuss);
						
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
				return cus;
		}
		
		
		//修改个人信息
		public boolean updatecus(int customerinfoid,customerifo cusifo) {
			Connection conn = null;
			 PreparedStatement pstmt = null;
			 
			 
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433; DatabaseName=jspdb","sa","heqijie15");
				String str="update CustomerinfoTb set CustomerName=?,Truename=?,Sex=?,Phone=?,CusAddress=?,Wechat=?,QQ=?,Email=? where CustomerinfoID=?";
				pstmt = conn.prepareStatement(str);
				
				pstmt.setString(1, cusifo.getCustomerName());
				pstmt.setString(2, cusifo.getTruename());
				pstmt.setString(3, cusifo.getSex());
				pstmt.setString(4, cusifo.getPhone());
				pstmt.setString(5, cusifo.getCusaddress());
				pstmt.setString(6, cusifo.getWechat());
				pstmt.setString(7, cusifo.getQq());
				pstmt.setString(8, cusifo.getEmail());
				
				pstmt.setInt(9, customerinfoid);
				
				pstmt.executeUpdate();
				
			
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
			return true;
		}
		
		
		
		//根据图书类别查书籍 
		public  List<quebookid> booktype(int booktypeid) {
			List<quebookid> quebid= new ArrayList<quebookid>();
				Connection conn = null;
				 PreparedStatement pstmt = null;
				 ResultSet rs=null;
				 
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433; DatabaseName=jspdb","sa","heqijie15");
					String str="select *  from BookinformationTb where BookTypeid=? ";
					pstmt = conn.prepareStatement(str);
					pstmt.setInt(1, booktypeid);
					rs=pstmt.executeQuery();
					while(rs.next()) 
					{	
						quebookid bp=new quebookid();
						
						bp.setBookid(rs.getInt("bookid"));
						bp.setBooktypeid(rs.getInt("BookTypeId"));
						bp.setBookname(rs.getString("BookName"));
						bp.setBookprice(rs.getString("BookPrice"));
						bp.setBookauthor(rs.getString("BookAuthor"));
						bp.setBooktanslor(rs.getString("BookTanslor"));
						bp.setBookisbn(rs.getString("Bookisbn"));
						bp.setBookstoremount(rs.getString("BookStoremount"));
						bp.setBookpress(rs.getString("BookPress"));
						bp.setBookpubdate(rs.getString("BookPubDate"));
						bp.setBookstoretime(rs.getString("BookStoretime"));
						
						
						
						quebid.add(bp);
						
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
				return quebid;
				
		}
		
		
		//根据书名查询
		public  List<quebookid> bookname(String bookname) {
			List<quebookid> quebid1= new ArrayList<quebookid>();
				Connection conn = null;
				 PreparedStatement pstmt = null;
				 ResultSet rs=null;
				 
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433; DatabaseName=jspdb","sa","heqijie15");
					String str="select *  from BookinformationTb where BookName=? ";
					pstmt = conn.prepareStatement(str);
					pstmt.setString(1, bookname);
					rs=pstmt.executeQuery();
					while(rs.next()) 
					{	
						quebookid bp=new quebookid();
						
						bp.setBookid(rs.getInt("bookid"));
						bp.setBooktypeid(rs.getInt("BookTypeId"));
						bp.setBookname(rs.getString("BookName"));
						bp.setBookprice(rs.getString("BookPrice"));
						bp.setBookauthor(rs.getString("BookAuthor"));
						bp.setBooktanslor(rs.getString("BookTanslor"));
						bp.setBookisbn(rs.getString("Bookisbn"));
						bp.setBookstoremount(rs.getString("BookStoremount"));
						bp.setBookpress(rs.getString("BookPress"));
						bp.setBookpubdate(rs.getString("BookPubDate"));
						bp.setBookstoretime(rs.getString("BookStoretime"));
						
						
						
						quebid1.add(bp);
						
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
				return quebid1;
		//查询全部类型
		/*public  List<btype> booktype() {
			List<btype> bty= new ArrayList<btype>();
			Connection conn = null;
			 PreparedStatement pstmt = null;
			 ResultSet rs=null;
			 
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433; DatabaseName=jspdb","sa","heqijie15");
				String str="select *  from BooktypeinfoTb  ";
				pstmt = conn.prepareStatement(str);
				rs=pstmt.executeQuery();
				while(rs.next()) 
				{	
					 btype bp=new btype();
					bp.setBooktypeid(rs.getInt("booktypeid"));
					bp.setBooktypename(rs.getString("booktypename"));
					
				//System.out.println(rs.getInt("booktypeid"));
				//System.out.println(rs.getString("booktypename"));
					bty.add(bp);
					
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
			return bty;
			
	}
		*/
}
}



