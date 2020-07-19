package com.carrito.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Connection con;
    private static final String URL="jdbc:mysql://localhost:3306/bd_carrito";
    private static final String USER="root";
    private static final String PASS="";

      
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(URL,USER,PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error de Conexion BD "+e);
        }
        return con;
    }
}
