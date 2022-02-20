package com.company.Product;


import com.company.PostgreSQLConnection;
import com.company.SCUtils;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private String id;
    private String name;
    private double price;
    private double quantity;
    private String unit;

    //public static String[] productProperties = {"Id", "Name", "Price", "Quantity", "Units"};

    public Product(String id, String name, double price, double quantity, String unit) {
        this.id = id;
        this.name = name.toLowerCase();
        this.price = price;
        this.quantity = quantity;
        this.unit = unit.toLowerCase();
    }

    public static ResultSet getProducts(){

        String query = "Select * from product order by name";
        try{
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            return rs;
        }
        catch (Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            SCUtils.logErrors("GET PRODUCTS:\n"+exceptionAsString);
            JOptionPane.showMessageDialog(null,"Error in getting products!");
        }
        return null;
    }

    public static ArrayList<String> getProductIds(){
        ArrayList<String> productIds = new ArrayList<>();
        try{
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("Select pid from product");
            while(rs.next()){
                productIds.add(rs.getString("pid"));
            }
            s.close();
            conn.close();
        }
        catch (Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            SCUtils.logErrors("GET PRODUCT IDS:\n"+exceptionAsString);
            JOptionPane.showMessageDialog(null,"Error Getting Product Ids");
            //new AddCustomer();
        }

        return productIds;
        //.toArray(new String[]{})
    }

    public static String[] getProductWithId(String pid) {
        try{
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("Select * from product where pid='"+pid+"'");
            if(rs.next()) {
                String[] productArray = {pid, rs.getString("name"), rs.getString("price"), rs.getString("quantity"),  rs.getString("units")};
                s.close();
                conn.close();
                return productArray;
            }
            else{
                s.close();
                conn.close();
                throw new Exception();
            }

        }
        catch (Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            SCUtils.logErrors("GET PROD NAME WITH ID:\n"+exceptionAsString);
            JOptionPane.showMessageDialog(null,"Error Getting Product Name with given id");
            //new AddCustomer();
        }
        return null;
    }

    public static String getGST(String pid, int category) {
        try{
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();
            String query = "";
            if(category == 0)
                query = "Select cgst from gst where pid='"+pid+"'";
            else
                query = "Select sgst from gst where pid='"+pid+"'";

            ResultSet rs = s.executeQuery(query);
            if(rs.next()) {
                String gstValue;
                if(category == 0)
                    gstValue = rs.getString("cgst");
                else
                    gstValue = rs.getString("sgst");
                s.close();
                conn.close();
                return gstValue;
            }
            else{
                s.close();
                conn.close();
                throw new Exception();
            }

        }
        catch (Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            SCUtils.logErrors("GET GST:\n"+exceptionAsString);
            JOptionPane.showMessageDialog(null,"Error Getting GST with given id");
            //new AddCustomer();
        }
        return null;
    }
}
