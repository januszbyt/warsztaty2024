/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.warsztaty2024;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author s40483
 */
public class DatabaseConnection {
    public static Connection getConnection(){
        Connection connection = null;
        
        try {
            String url="jdbc:mysql://localhost:3306/pracownicydb";
            String username = "root";
            String password = "";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Udane polaczenie z baza!");          
            }
        catch (SQLException e){
            System.err.println("Connection error: " + e.getMessage());
            
        }
        return connection;
    }
}
