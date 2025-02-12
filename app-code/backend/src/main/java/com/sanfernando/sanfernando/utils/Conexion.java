package com.sanfernando.sanfernando.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class Conexion {

    private Connection con;
    
    @Value("${DB_HOST}")
    private String dbHost;
    
    @Value("${DB_PORT}")
    private String dbPort;
    
    @Value("${DB_DATABASE}")
    private String dbName;
    
    @Value("${DB_USERNAME}")
    private String username;
    
    @Value("${DB_PASSWORD}")
    private String password;
    
    public void startConexion() {
        try {
            String url = String.format("jdbc:postgresql://%s:%s/%s", dbHost, dbPort, dbName);
            Connection con = DriverManager.getConnection(url, username, password);
            this.con = con;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConexion() {
        try {
            if (this.con != null && !this.con.isClosed()) {
                this.con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
