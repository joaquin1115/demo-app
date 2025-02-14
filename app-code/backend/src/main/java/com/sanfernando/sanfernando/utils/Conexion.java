package com.sanfernando.sanfernando.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
@NoArgsConstructor
@Service
public class Conexion {

    private static final Logger logger = LoggerFactory.getLogger(Conexion.class);
    private Connection con;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public void startConexion() {
        try {
            logger.info("Attempting to connect to database with URL: {}, username: {}", url, username);
            Connection con = DriverManager.getConnection(url, username, password);
            this.con = con;
            logger.info("Database connection established successfully");
        } catch (SQLException e) {
            logger.error("Failed to establish database connection: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to establish database connection", e);
        }
    }

    public Connection getCon() {
        if (con == null) {
            logger.info("Connection is null, attempting to reconnect");
            startConexion();
        }
        return con;
    }

    public void closeConexion() {
        try {
            if (this.con != null && !this.con.isClosed()) {
                this.con.close();
                logger.info("Database connection closed successfully");
            }
        } catch (SQLException e) {
            logger.error("Error closing connection: {}", e.getMessage(), e);
        }
    }
}