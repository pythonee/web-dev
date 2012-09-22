package com.mashup.utils;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class InitDataImport {
	
	public void readDataSetToDataBase(){
		
		String path = "C:/u.data";
		File file = new File(path);
		Connection conn = getConnection();
		String inserSQL = "INSERT INTO product_preference VALUES (default,?,?,?,default)";
		if (file.exists()) {
			if(file.isFile()){
				try {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					String line;
					String[] temp;
					PreparedStatement stam = conn.prepareStatement(inserSQL);
					int i = 0;
					while((line = reader.readLine()) != null){
						temp = line.split("\\s");
						stam.setString(1, temp[1]);
						stam.setString(2, temp[0]); // product
						stam.setString(3, temp[2]);					
						stam.executeUpdate();
						i++;
						
						if (i == 10000)
						{
							break;
						}
						System.out.println("Insert into success");
					}					
					System.out.println("Finish important");
				} 
				catch (Exception e) {
					e.printStackTrace();
				}finally{
					if (conn != null) {
						try {
							conn.close();
						} catch (Exception e) {
							// TODO: handle exception
						}
						
					}
				}
			}else if(file.isDirectory()){
				String[] dir = file.list();
				String output = "Directory contents:\n";
				for (int i = 0; i < dir.length; i++) {
					output += dir[i] + "\n";
				}
			}
		}else {
			System.out.println("file doesn't exists");
		}
		
	}
	
	public Connection getConnection(){
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String username = "root";
			
			String password = "root";
			
			String url = "jdbc:mysql://localhost:3306/bia";
			
			conn = DriverManager.getConnection(url,username,password);
			
		} catch (ClassNotFoundException clsnf) {
			
			System.out.println("Class Not Found");
			clsnf.printStackTrace();
			
		}catch (SQLException sql){
			
			System.out.println("MySql Connect Error");
			sql.printStackTrace();
		}	
		
		return conn;
	}
	
	public static void main(String[] args){
		InitDataImport init = new InitDataImport();
		
		init.readDataSetToDataBase();
		
	}
}
