package com.capgemini.client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static Connection createConnection() throws ClassNotFoundException,SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String dbURL = "jdbc:mysql://localhost:3306/demodb";
		String userName = "root";
		String password = "pass";
		Connection connection = DriverManager.getConnection(dbURL, userName,
				password);
		return connection;

	}

	public static void insertRecord() throws ClassNotFoundException,SQLException {
		Connection connection = createConnection();
		System.out.println("Connected Successfully!!");
		String sql = "insert into Customer values(?,?,?,?)";
		int inp_id=0;
		String inp_name="";
		String inp_city="";
		double inp_amt=0;
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter Id:");
		inp_id=scanner.nextInt();
		System.out.println("Enter name");
		inp_name=scanner.next();
		System.out.println("Enter city");
		inp_city=scanner.next();
		System.out.println("Enter amount");
		inp_amt=scanner.nextDouble();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, inp_id);
		statement.setString(2, inp_name);
		statement.setString(3, inp_city);
		statement.setDouble(4,inp_amt);
		int r=statement.executeUpdate();
		System.out.println(r + " rows inserted");
		statement.close();
		connection.close();
	}

	public static void updateRecord() throws ClassNotFoundException,SQLException {
		Connection connection = createConnection();
		System.out.println("Connected Successfully!!");
		int id=0;
		double amount=0;
		
		String sql = "update Customer set c_amt=c_amt+? where c_id=?";
		Scanner scanner=new Scanner(System.in);
		System.out.println("enter the c_id you want to update");
		id=scanner.nextInt();
		System.out.println("enter the amount you want to add");
		amount=scanner.nextDouble();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1,id);
		
		statement.setDouble(2,amount);
	
		
		int r = statement.executeUpdate();
		System.out.println(r + " rows updated");
		statement.close();
		connection.close();
	}

	public static void deleteRecord() throws ClassNotFoundException,SQLException {
		Connection connection = createConnection();
		System.out.println("Connected Successfully!!");
		String sql = "delete from Customer where c_id=?";
		Scanner scanner=new Scanner(System.in);
		int id=0;
		System.out.print("Enter Id:");
		id=scanner.nextInt();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1,id);
		int r = statement.executeUpdate();
		System.out.println(r + " rows deleted");
		scanner.close();
		statement.close();
		connection.close();
	}

	public static void displayOneRecord()throws ClassNotFoundException, SQLException {
		Connection connection = createConnection();
		System.out.println("Connected Successfully!!");
		System.out.println("Enter id of the record you want to display");
		Scanner scanner=new Scanner(System.in);
		int id=0;
	
		id=scanner.nextInt();
		String sql = "select * from Customer where c_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();

		if (rs.next()) {

			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getString(3) + "\t");
			System.out.println(rs.getDouble(4));
		}
		statement.close();
		connection.close();
	}

	public static void displayAllRecord() throws ClassNotFoundException,SQLException {
		Connection connection = createConnection();
		System.out.println("Connected Successfully!!");
		String sql = "select * from Customer";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		while (rs.next()) {

			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getString(3) + "\t");
			System.out.println(rs.getDouble(4));
		}
		statement.close();
		connection.close();
	}

	public static void main(String[] args) throws ClassNotFoundException,SQLException {

	/*	updateRecord();*/
		displayOneRecord();
       /* deleteRecord();*/
      displayAllRecord();
        
	}
}
