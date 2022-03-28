package com.company;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PostgreSQLConnection {
    /*static{
        try {
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();

            String sql =  "create table admin(
                username varch ar(50),
                password varchar(50)
            );";

            int rowsAffected = s.executeUpdate(sql);
            System.out.println(rowsAffected);
            */


            /*String sql =  "insert into admin values('Mayur','mstmstmst');";

            rowsAffected = s.executeUpdate(sql);
            System.out.println(rowsAffected);


            String sql =  "create table product( pid varchar(25) primary key, name varchar(50),n" +
                    "price float(2),\n" +
                    "quantity float(2),\n" +
                    "units varchar(10)\n" +
                    ");";

            int rowsAffected = s.executeUpdate(sql);
            System.out.println(rowsAffected);

            sql =  "create table gst(\n" +
                    "\tpid varchar(20) primary key,\n" +
                    "\tcgst float(2),\n" +
                    "\tsgst float(2)\n" +
                    ");";

            rowsAffected = s.executeUpdate(sql);
            System.out.println(rowsAffected);

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error while creating tables: "+e);
        }
    }*/

    private static Connection conn;


    public static Connection getConnection() throws Exception{

        //To Do : env of pass and username and hash password in pgadmin
        //String url = "jdbc:postgresql://localhost:5432/StoreCodeDb";
        String url = "jdbc:h2:~/test";

        String hostUsername = "postgres";
        String hostPassword = "YashPG@2000";

        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection(url, "sa", "");
        //s = conn.createStatement();
        return conn;
    }
}
