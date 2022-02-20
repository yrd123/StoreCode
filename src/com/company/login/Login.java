package com.company.login;

import com.company.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener {


    JPlaceholderTextField usernameInput,passwordInput;
    JButton btnLogin;

    public Login(){

        //Build Form


        setTitle("Login");
        /*JLabel lblLogin = new JLabel("Log In");
        lblLogin.setFont(new Font("Helvetica Neue",Font.BOLD,18));
        lblLogin.setHorizontalAlignment(JLabel.CENTER);
        lblLogin.setBounds(0,10,422, 50);
        lblLogin.setForeground(Color.BLACK);
        add(lblLogin);*/

        /*JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Helvetica Neue",Font.PLAIN,15));
        lblPassword.setVerticalAlignment(JLabel.CENTER);
        lblPassword.setBounds(95,118,80, 50);
        lblPassword.setForeground(Color.BLACK);
        add(lblPassword);*/

        usernameInput = new JPlaceholderTextField("Username");
        //usernameInput.setText("Enter Username");
        usernameInput.setBounds(65,80,320, 40);
        add(usernameInput);

        passwordInput = new JPlaceholderTextField("Password");
        //passwordInput.setText("Enter Password"); Helvetica Neue
        passwordInput.setBounds(65,150,320, 40);
        add(passwordInput);

        Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);

        btnLogin = new RoundButton("Login");
        btnLogin.setFont(new Font("",Font.PLAIN, 14));
        btnLogin.setBorder(emptyBorder);
        btnLogin.setBounds(170,220,80,40);
        //btnLogin.setBackground(new Color(177, 177, 177));
        add(btnLogin);

        setLayout(null);
        getContentPane().setBackground(new Color(248, 248, 248));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        setUndecorated(true);
//
//        setShape(new RoundRectangle2D.Double(10,20,450,350, 10, 10));
//        setSize(450,350);
        setBounds(570,260,450,350);
        setVisible(true);

        btnLogin.addActionListener(this);  // Call performed action on click
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == btnLogin) {
            if(login(usernameInput.getText(), passwordInput.getText())) {
                new Dashboard();
                dispose();
            }
            else
                JOptionPane.showMessageDialog(null, "Invalid username and password");
        }
    }

    private boolean login(String username, String password){

        String query = "select * from admin where username='" + username + "' and password='" + password + "';";
        try{
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);

            if(rs.next()) {
                LoginSession.Username = rs.getString("username");
                LoginSession.isLoggedIn = true;
                conn.close();
                return true;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return false;
    }
}
