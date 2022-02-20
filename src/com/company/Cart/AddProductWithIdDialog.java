package com.company.Cart;

import com.company.JPlaceholderTextField;
import com.company.Product.Product;
import com.company.RoundButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddProductWithIdDialog extends JDialog {

    JPlaceholderTextField txtProductId, txtCount;
    JComboBox txtCartNo;
    RoundButton btnAdd;
    String productId = "";
    Integer cartNo = 0;
    Integer count = 0;

    public AddProductWithIdDialog(Frame parent){

        super(parent, "Add product with Id");
        setLayout(null);
        setBounds(570,260,450,400);




        /*JLabel lblProductId = new JLabel("Enter Product Id");
        lblProductId.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        lblProductId.setBounds(80,40,400, 30);
        lblProductId.setHorizontalAlignment(JLabel.CENTER);
        panel.add(lblProductId);*/

        txtProductId = new JPlaceholderTextField("Enter Product Id");
        txtProductId.setBounds(65,50,320, 40);
        add(txtProductId);

        /*JLabel lblCount = new JLabel("Enter Count");
        lblCount.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        lblCount.setBounds(80,135,400, 30);
        lblCount.setHorizontalAlignment(JLabel.CENTER);
        panel.add(lblCount);*/

        txtCount = new JPlaceholderTextField("Enter Count");
        txtCount.setBounds(65,120,320, 40);
        add(txtCount);

        /*JLabel lblCartNo = new JLabel("Enter Cart No");
        lblCartNo.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        lblCartNo.setBounds(65,220,400, 30);
        lblCartNo.setHorizontalAlignment(JLabel.CENTER);
        add(lblCartNo);*/

        txtCartNo = new JComboBox(new Integer[]{1,2,3,4});
        txtCartNo.setBounds(65,190,320, 40);
        txtCartNo.setBackground(Color.WHITE);
        add(txtCartNo);

        btnAdd = new RoundButton("Add");
        btnAdd.setFont(new Font("",Font.PLAIN,14));
        btnAdd.setBounds(65,260,320,40);
        btnAdd.addActionListener(ae -> {
            String productId = txtProductId.getText();

            if(productId != null && !productId.equals("")) {
                if(Product.getProductWithId(productId) != null)
                    this.productId = productId;
            }
            else{
                JOptionPane.showMessageDialog(null, "Enter valid Product Id!");
                return;
            }
            try {
                count = Integer.parseInt(txtCount.getText());
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Enter valid count!");
                return;
            }
            cartNo = (Integer) txtCartNo.getSelectedItem();

            dispose();
        });
        add(btnAdd);

        getContentPane().setBackground(new Color(248,248,248));
    }

}
