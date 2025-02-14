package com.sanfernando.sanfernando.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Service
public class Conexion {

    private Connection con;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public void startConexion() {
        try {
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