package com.company;

import com.company.Cart.CartUI;
import com.company.Product.ProductsUI;
import com.company.login.Logout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {
    RoundButton btnProducts, btnCart, btnLogOut;
    public Dashboard(){

        setTitle("Dashboard");
        btnProducts = new RoundButton("Products");
        btnProducts.setFont(new Font("", Font.PLAIN, 14));
        btnProducts.setBounds(150,50,150,50);
        add(btnProducts);
        btnProducts.addActionListener(this);

        btnCart = new RoundButton("Cart");
        btnCart.setFont(new Font("", Font.PLAIN, 14));
        btnCart.setBounds(150,120,150,50);
        add(btnCart);
        btnCart.addActionListener(this);

        btnLogOut = new RoundButton("LogOut");
        btnLogOut.setFont(new Font("", Font.PLAIN, 14));
        btnLogOut.setBounds(150,190,150,50);
        add(btnLogOut);
        btnLogOut.addActionListener(this);

        setLayout(null);
        getContentPane().setBackground(new Color(248,248,248));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(570,260,450,350);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnProducts) {
            new ProductsUI();
            dispose();
        } else if (e.getSource() == btnCart) {
            new CartUI();
            dispose();
        } else if (e.getSource() == btnLogOut) {
            Logout.logout();
            dispose();
        }
    }

}

