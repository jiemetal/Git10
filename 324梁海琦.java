package com.inspur.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbHelper {
	private static String url="jdbc:sqlserver://127.0.0.1:1433;databaseName=jspdb";
	private static String username="sa";
	private static String password="heqijie15";
	private static Connection conn = null;
	
	private DbHelper(){
        
    }
	
	public static Connection getConnection(){
        if(null == conn){
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn; 
    }
    public static void main(String[] args) {  //测试数据库是否连通
        System.err.println(getConnection());
        System.out.println("连接成功");
    }

}
