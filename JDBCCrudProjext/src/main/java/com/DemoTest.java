package com;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.Connection;

public class DemoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Sucesso crl");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/v_db", "root", "Simplilearn");
			System.out.println("Conectei crl");
			
			PreparedStatement pstmt = con.prepareStatement("insert into product values(?,?,?)");
			Scanner sc = new Scanner(System.in);
			System.out.println("enter the product pid crl");
			int pid = sc.nextInt();
				pstmt.setInt(1, pid);
			
			System.out.println("Enter the pname crl");
			String pname = sc.next();
				pstmt.setString(2, pname);
			
			System.out.println("Enter the price crl");
			float price = sc.nextFloat();
				pstmt.setFloat(3, price);
			
			int res = pstmt.executeUpdate();		// DML insert, delete and update 
			
			if(res>0) {
				System.out.println("Record inserted successfully crl");
			}
		}catch(Exception e){
			System.err.println("FUODASSE");
		}
	}

}
