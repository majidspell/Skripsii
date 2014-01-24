/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skripsi.majid.db;

import com.skripsi.majid.service.PemilikService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Majid
 */
public class DBConnection {

    private Connection conn = null;
    private static DBConnection dbConn = null;
    static final String DB_URL = "jdbc:mysql://localhost:3306/skripsi";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
   
    private static PemilikService pemilikService;


    public static PemilikService getPemilikService() throws SQLException {
        if (pemilikService == null) {
            DBConnection conn = DBConnection.getInstance();
            pemilikService = new PemilikService(conn.getCon());
        }
        return pemilikService;
    }

    private DBConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
    }

    public static DBConnection getInstance() {
        DBConnection conn = null;
        if (dbConn == null) {
            dbConn = new DBConnection();
            conn = dbConn;
        } else {
            conn = dbConn;
        }
        return conn;
    }

    public Connection getCon() throws SQLException {
        return this.conn;
    }
}
