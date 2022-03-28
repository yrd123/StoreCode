package com.company.Cart;

import com.company.Dashboard;
import com.company.JPlaceholderTextField;
import com.company.Product.Product;
import com.company.RoundButton;
import com.company.SCUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;



import java.awt.Font;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class CartUI extends JFrame implements ActionListener {

    StringBuilder temp_barcode = new StringBuilder();
    ArrayList<String[]> cart1 = new ArrayList<>();
    ArrayList<String[]> cart2 = new ArrayList<>();
    ArrayList<String[]> cart3 = new ArrayList<>();
    ArrayList<String[]> cart4 = new ArrayList<>();

    JTable cart1Table, cart2Table, cart3Table, cart4Table;
    JPlaceholderTextField txtCart1Total, txtCart2Total, txtCart3Total, txtCart4Total, txtProductId, txtCart1Count, txtCart2Count, txtCart3Count, txtCart4Count;
    RoundButton btnCart1Bill, btnCart2Bill, btnCart3Bill, btnCart4Bill,btnCart1Clear, btnCart2Clear, btnCart3Clear, btnCart4Clear, btnAddProductWithId, btnBack;
    DefaultTableModel modelCart1, modelCart2, modelCart3, modelCart4;

    String[] columnNames = new String[]{"Sr","Id", "Name", "Price", "Quantity", "Units","Count","Amount"};
    public CartUI(){

        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Cart");


        /*JPanel panel = new JPanel();
        panel.setLayout(null);
        panel
        panel.setBackground(Color.BLACK );
        add(panel);*/


        Font my_font = new Font("",Font.PLAIN,11);
        Font bold_font = new Font("",Font.BOLD,11);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBounds(10,10,1415,40);
        //UI topPanel.setBounds(10,10,1520,40);
        topPanel.setBackground(Color.WHITE);
        add(topPanel);


        JPanel cart1Panel = new JPanel();
        cart1Panel.setLayout(null);
        cart1Panel.setBounds(10,60,700,360);
        //UI cart1Panel.setBounds(10,60,750,360);
        cart1Panel.setBackground(new Color(255,255,255));
        add(cart1Panel);

        JPanel cart2Panel = new JPanel();
        cart2Panel.setLayout(null);
        cart2Panel.setBounds(725,60,700,360);
        //UI cart2Panel.setBounds(775,60,750,360);
        cart2Panel.setBackground(new Color(255,255,255));
        add(cart2Panel);

        JPanel cart3Panel = new JPanel();
        cart3Panel.setLayout(null);
        cart3Panel.setBounds(10,430,700,370);
        //UI cart3Panel.setBounds(10,430,750,370);
        cart3Panel.setBackground(new Color(255,255,255));
        add(cart3Panel);

        JPanel cart4Panel = new JPanel();
        cart4Panel.setLayout(null);
        cart4Panel.setBounds(725,430,700,370);
        //UI cart4Panel.setBounds(775,430,750,370);
        cart4Panel.setBackground(new Color(255,255,255));
        add(cart4Panel);




        /*JLabel lblProductId = new JLabel("Product Id: ");
        lblProductId.setBounds(100,5,80,30);
        topPanel.add(lblProductId);

        txtProductId = new JPlaceholderTextField(30);
        txtProductId.setBounds(170,350,200,30);
        txtProductId.setEditable(false);
        txtProductId.setFocusable(false);
        topPanel.add(txtProductId);*/

        /*btnCart1Bill = new RoundButton("Print");
        btnCart1Bill.setBounds(400,350,100,30);
        topPanel.add(btnCart1Bill);
        btnCart1Bill.addActionListener(this);

        btnCart1Clear = new RoundButton("Clear");
        btnCart1Clear.setBounds(530,350,100,30);
        topPanel.add(btnCart1Clear);
        btnCart1Clear.addActionListener(this);*/

        btnAddProductWithId = new RoundButton("Add Product with Id");
        btnAddProductWithId.setBounds(390,5,300,30);
        btnAddProductWithId.setFont(my_font);

        topPanel.add(btnAddProductWithId);
        btnAddProductWithId.addActionListener(this);

        btnBack = new RoundButton("Back");
        btnBack.setBounds(725,5,300,30);
        btnBack.setFont(my_font);
        topPanel.add(btnBack);
        btnBack.addActionListener(this);



        UIManager.getDefaults().put("TableHeader.cellBorder" , BorderFactory.createEmptyBorder(0,3,0,0));

        JScrollPane sp1 = new JScrollPane();
        sp1.setBounds(15,15,675,295);
        //UI sp1.setBounds(15,15,725,295);
        modelCart1 = new DefaultTableModel(cart1.toArray(new String[0][0]), columnNames);
        cart1Table = new JTable(modelCart1){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        cart1Table.getTableHeader().setFont(bold_font);
        cart1Table.getTableHeader().setPreferredSize(new Dimension(0,40));
        cart1Table.getTableHeader().setBackground(new Color(243, 243, 243));
        ((DefaultTableCellRenderer)cart1Table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);
        cart1Table.setFillsViewportHeight(true);
        cart1Table.setBackground(new Color(250, 250, 250));
        cart1Table.setShowVerticalLines(false);
        cart1Table.setRowHeight(30);
        //cart1Table.setFont(my_font);
        cart1Table.setCellSelectionEnabled(false);
        cart1Table.setFocusable(false);
        sp1.setViewportView(cart1Table);
        cart1Panel.add(sp1);


        JScrollPane sp2 = new JScrollPane();
        sp2.setBounds(15,15,675,295);
        modelCart2 = new DefaultTableModel(cart2.toArray(new String[0][0]), columnNames);
        cart2Table = new JTable(modelCart2){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        cart2Table.getTableHeader().setFont(bold_font);
        cart2Table.getTableHeader().setPreferredSize(new Dimension(0,40));
        cart2Table.getTableHeader().setBackground(new Color(243, 243, 243));
        ((DefaultTableCellRenderer)cart2Table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);
        cart2Table.setFillsViewportHeight(true);
        cart2Table.setBackground(new Color(250, 250, 250));
        cart2Table.setShowVerticalLines(false);
        cart2Table.setRowHeight(30);
        //cart2Table.setFont(my_font);
        cart2Table.setCellSelectionEnabled(false);
        cart2Table.setFocusable(false);
        sp2.setViewportView(cart2Table);
        cart2Panel.add(sp2);


        JScrollPane sp3 = new JScrollPane();
        sp3.setBounds(15,15,675,295);
        modelCart3 = new DefaultTableModel(cart3.toArray(new String[0][0]), columnNames);
        cart3Table = new JTable(modelCart3){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        cart3Table.getTableHeader().setFont(bold_font);
        cart3Table.getTableHeader().setPreferredSize(new Dimension(0,40));
        cart3Table.getTableHeader().setBackground(new Color(243, 243, 243));
        ((DefaultTableCellRenderer)cart3Table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);
        cart3Table.setFillsViewportHeight(true);
        cart3Table.setBackground(new Color(250, 250, 250));
        cart3Table.setShowVerticalLines(false);
        cart3Table.setRowHeight(30);
        //cart3Table.setFont(my_font);
        cart3Table.setCellSelectionEnabled(false);
        cart3Table.setFocusable(false);
        sp3.setViewportView(cart3Table);
        cart3Panel.add(sp3);

        JScrollPane sp4 = new JScrollPane();
        sp4.setBounds(15,15,675,295);
        modelCart4 = new DefaultTableModel(cart4.toArray(new String[0][0]), columnNames);
        cart4Table = new JTable(modelCart4){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        cart4Table.getTableHeader().setFont(bold_font);
        cart4Table.getTableHeader().setPreferredSize(new Dimension(0,40));
        cart4Table.getTableHeader().setBackground(new Color(243, 243, 243));
        ((DefaultTableCellRenderer)cart4Table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);
        cart4Table.setFillsViewportHeight(true);
        cart4Table.setBackground(new Color(250, 250, 250));
        cart4Table.setShowVerticalLines(false);
        cart4Table.setRowHeight(30);
        //cart4Table.setFont(my_font);
        cart4Table.setCellSelectionEnabled(false);
        cart4Table.setFocusable(false);
        sp4.setViewportView(cart4Table);
        cart4Panel.add(sp4);


        JLabel lblCart1Count = new JLabel("Count: ");
        lblCart1Count.setBounds(20,320,40,30);
        //UI lblCart1Count.setBounds(35,320,50,30);
        cart1Panel.add(lblCart1Count);

        txtCart1Count = new JPlaceholderTextField("");
        txtCart1Count.setBounds(75,320,140,30);
        //UI txtCart1Count.setBounds(100,320,140,30);
        txtCart1Count.setFont(my_font);
        txtCart1Count.setEditable(false);
        txtCart1Count.setFocusable(false);
        cart1Panel.add(txtCart1Count);


        JLabel lblCart1Total = new JLabel("Total: ");
        lblCart1Total.setBounds(235,320,40,30);
        //UI lblCart1Total.setBounds(260,320,50,30);
        cart1Panel.add(lblCart1Total);

        txtCart1Total = new JPlaceholderTextField("");
        txtCart1Total.setBounds(290,320,140,30);
        //UI txtCart1Total.setBounds(330,320,140,30);
        txtCart1Total.setFont(my_font);
        txtCart1Total.setEditable(false);
        txtCart1Total.setFocusable(false);
        cart1Panel.add(txtCart1Total);

        btnCart1Bill = new RoundButton("Print");
        btnCart1Bill.setBounds(460,320,100,30);
        //UI btnCart1Bill.setBounds(490,320,100,30);
        btnCart1Bill.setFont(my_font);
        cart1Panel.add(btnCart1Bill);
        btnCart1Bill.addActionListener(this);

        btnCart1Clear = new RoundButton("Clear");
        btnCart1Clear.setBounds(580,320,100,30);
        //UI btnCart1Clear.setBounds(610,320,100,30);
        btnCart1Clear.setFont(my_font);
        cart1Panel.add(btnCart1Clear);
        btnCart1Clear.addActionListener(this);


        JLabel lblCart2Count = new JLabel("Count: ");
        lblCart2Count.setBounds(20,320,40,30);
        //UI lblCart2Count.setBounds(35,320,50,30);
        cart2Panel.add(lblCart2Count);

        txtCart2Count = new JPlaceholderTextField("");
        txtCart2Count.setBounds(75,320,140,30);
        //UI txtCart2Count.setBounds(100,320,140,30);
        txtCart2Count.setFont(my_font);
        txtCart2Count.setEditable(false);
        txtCart2Count.setFocusable(false);
        cart2Panel.add(txtCart2Count);

        JLabel lblCart2Total = new JLabel("Total: ");
        lblCart2Total.setBounds(235,320,40,30);
        //UI lblCart2Total.setBounds(260,320,50,30);
        cart2Panel.add(lblCart2Total);

        txtCart2Total = new JPlaceholderTextField("");
        txtCart2Total.setBounds(290,320,140,30);
        //UI txtCart2Total.setBounds(330,320,140,30);
        txtCart2Total.setFont(my_font);
        txtCart2Total.setEditable(false);
        txtCart2Total.setFocusable(false);
        cart2Panel.add(txtCart2Total);

        btnCart2Bill = new RoundButton("Print");
        btnCart2Bill.setBounds(460,320,100,30);
        //UI btnCart2Bill.setBounds(490,320,100,30);
        btnCart2Bill.setFont(my_font);
        cart2Panel.add(btnCart2Bill);
        btnCart2Bill.addActionListener(this);

        btnCart2Clear = new RoundButton("Clear");
        //UI btnCart2Clear.setBounds(610,320,100,30);
        btnCart2Clear.setBounds(580,320,100,30);
        btnCart2Clear.setFont(my_font);
        cart2Panel.add(btnCart2Clear);
        btnCart2Clear.addActionListener(this);



        JLabel lblCart3Count = new JLabel("Count: ");
        lblCart3Count.setBounds(20,320,40,30);
        //UI lblCart3Count.setBounds(35,320,50,30);
        cart3Panel.add(lblCart3Count);

        txtCart3Count = new JPlaceholderTextField("");
        txtCart3Count.setBounds(75,320,140,30);
        //UI txtCart3Count.setBounds(100,320,140,30);
        txtCart3Count.setFont(my_font);
        txtCart3Count.setEditable(false);
        txtCart3Count.setFocusable(false);
        cart3Panel.add(txtCart3Count);

        JLabel lblCart3Total = new JLabel("Total: ");
        lblCart3Total.setBounds(235,320,40,30);
        //UI lblCart3Total.setBounds(260,320,50,30);
        cart3Panel.add(lblCart3Total);

        txtCart3Total = new JPlaceholderTextField("");
        txtCart3Total.setBounds(290,320,140,30);
        //UI txtCart3Total.setBounds(330,320,140,30);
        txtCart3Total.setFont(my_font);
        txtCart3Total.setEditable(false);
        txtCart3Total.setFocusable(false);
        cart3Panel.add(txtCart3Total);

        btnCart3Bill = new RoundButton("Print");
        btnCart3Bill.setBounds(460,320,100,30);
        //UI btnCart3Bill.setBounds(490,320,100,30);
        btnCart3Bill.setFont(my_font);
        cart3Panel.add(btnCart3Bill);
        btnCart3Bill.addActionListener(this);

        btnCart3Clear = new RoundButton("Clear");
        btnCart3Clear.setBounds(580,320,100,30);
        //UI btnCart3Clear.setBounds(610,320,100,30);
        btnCart3Clear.setFont(my_font);
        cart3Panel.add(btnCart3Clear);
        btnCart3Clear.addActionListener(this);


        JLabel lblCart4Count = new JLabel("Count: ");
        lblCart4Count.setBounds(20,320,40,30);
        //UI lblCart4Count.setBounds(35,320,50,30);
        cart4Panel.add(lblCart4Count);

        txtCart4Count = new JPlaceholderTextField("");
        txtCart4Count.setBounds(75,320,140,30);
        //UI txtCart4Count.setBounds(100,320,140,30);
        txtCart4Count.setFont(my_font);
        txtCart4Count.setEditable(false);
        txtCart4Count.setFocusable(false);
        cart4Panel.add(txtCart4Count);

        JLabel lblCart4Total = new JLabel("Total: ");
        lblCart4Total.setBounds(235,320,40,30);
        //UI lblCart4Total.setBounds(260,320,50,30);
        cart4Panel.add(lblCart4Total);

        txtCart4Total = new JPlaceholderTextField("");
        txtCart4Total.setBounds(290,320,140,30);
        //UI txtCart4Total.setBounds(330,320,140,30);
        txtCart4Total.setFont(my_font);
        txtCart4Total.setEditable(false);
        txtCart4Total.setFocusable(false);
        cart4Panel.add(txtCart4Total);

        btnCart4Bill = new RoundButton("Print");
        btnCart4Bill.setBounds(460,320,100,30);
        //UI btnCart4Bill.setBounds(490,320,100,30);
        btnCart4Bill.setFont(my_font);
        cart4Panel.add(btnCart4Bill);
        btnCart4Bill.addActionListener(this);

        btnCart4Clear = new RoundButton("Clear");
        btnCart4Clear.setBounds(580,320,100,30);
        //UI btnCart4Clear.setBounds(610,320,100,30);
        btnCart4Clear.setFont(my_font);
        cart4Panel.add(btnCart4Clear);
        btnCart4Clear.addActionListener(this);


        setFocusable(true);
        requestFocus();

        //Initializing Total text fields
        calculateCartTotal(1);
        calculateCartTotal(2);
        calculateCartTotal(3);
        calculateCartTotal(4);

        calculateCartCount(1);
        calculateCartCount(2);
        calculateCartCount(3);
        calculateCartCount(4);


        addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == 10 && temp_barcode.length() > 0) {
                    if(temp_barcode.toString().equals("A_0000000000000"))
                        generatePDFFromTable(cart1Table, txtCart1Total.getText(), txtCart1Count.getText(),1);
                    else if(temp_barcode.toString().equals("B_0000000000000"))
                        generatePDFFromTable(cart2Table, txtCart2Total.getText(),txtCart2Count.getText(), 2);
                    else if(temp_barcode.toString().equals("C_0000000000000"))
                        generatePDFFromTable(cart3Table, txtCart3Total.getText(), txtCart3Count.getText(), 3);
                    else if(temp_barcode.toString().equals("D_0000000000000"))
                        generatePDFFromTable(cart4Table, txtCart4Total.getText(), txtCart4Count.getText(), 4);
                    else
                        assignCart(temp_barcode.toString());
                    temp_barcode = new StringBuilder();
                }
                else if(e.getKeyCode() != 10 && e.getKeyCode() != 16){
                    // some character has been read, append it to your "barcode cache"
                    temp_barcode.append(e.getKeyChar());
                }
            }

        });


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

       /* try {
            Thread.sleep(5000);
            addtocart(new String[]{"1","4","7","8","5"});
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }



    @Override
    public void actionPerformed(ActionEvent ae) {
        this.requestFocus();
        if(ae.getSource() == btnCart1Bill){
            generatePDFFromTable(cart1Table, txtCart1Total.getText(), txtCart1Count.getText(),1);
        }
        else if(ae.getSource() == btnCart2Bill){
            generatePDFFromTable(cart2Table, txtCart2Total.getText(),txtCart2Count.getText(), 2);
        }
        else if(ae.getSource() == btnCart3Bill){
            generatePDFFromTable(cart3Table, txtCart3Total.getText(), txtCart3Count.getText(), 3);
        }
        else if(ae.getSource() == btnCart4Bill){
            generatePDFFromTable(cart4Table, txtCart4Total.getText(), txtCart4Count.getText(), 4);
        }
        else if(ae.getSource() == btnCart1Clear){
            clearCart(1);
        }
        else if(ae.getSource() == btnCart2Clear){
            clearCart(2);
        }
        else if(ae.getSource() == btnCart3Clear){
            clearCart(3);
        }
        else if(ae.getSource() == btnCart4Clear){
            clearCart(4);
        }
        else if(ae.getSource() == btnAddProductWithId) {
            AddProductWithIdDialog modal = new AddProductWithIdDialog(this);
            modal.setModal(true);
            modal.setVisible(true);
            String productId = modal.productId;
            int cartNo = modal.cartNo;
            int count = modal.count;

            for(int i = 0; i < count; i++){
                if(cartNo == 1)
                    assignCart("A_"+productId);
                else if(cartNo == 2)
                    assignCart("B_"+productId);
                else if(cartNo == 3)
                    assignCart("C_"+productId);
                else if(cartNo == 4)
                    assignCart("D_"+productId);
            }
        }
        else if(ae.getSource() == btnBack){
            new Dashboard();
            dispose();
        }
        this.requestFocus();
    }

    private void assignCart(String barcode){
        if(barcode.length() < 3)
            return;
        String pid = barcode.substring(2);
        String[] product = Product.getProductWithId(pid);

        if(product != null){
            if(barcode.startsWith("A_")) {
                int flag = 0;
                for(int i = 0; i < cart1.size(); i++){
                    if(cart1.get(i)[1].equals(pid)){
                        cart1.get(i)[6] = String.valueOf(Integer.parseInt(cart1.get(i)[6]) + 1);
                        modelCart1.setValueAt(cart1.get(i)[6],i,6);

                        cart1.get(i)[7] = String.valueOf(Double.parseDouble(cart1.get(i)[3])*Integer.parseInt(cart1.get(i)[6]));
                        modelCart1.setValueAt(cart1.get(i)[7],i,7);

                        flag = 1;
                    }
                }
                if(flag == 0){
                    String[] productForCart = new String[8];
                    productForCart[0] = String.valueOf(cart1.size()+1);
                    for(int i = 1; i < 6; i++){
                        if(i == 2)
                            productForCart[i] = SCUtils.capitalize(product[i-1]);
                        else
                            productForCart[i] = product[i-1];
                    }
                    productForCart[6] = "1";
                    productForCart[7] = product[2];
                    cart1.add(productForCart);
                    modelCart1.addRow(productForCart);
                }
                calculateCartTotal(1);
                calculateCartCount(1);

            }
            else if(barcode.startsWith("B_")){
                int flag = 0;
                for(int i = 0; i < cart2.size(); i++){
                    if(cart2.get(i)[1].equals(pid)){
                        cart2.get(i)[6] = String.valueOf(Integer.parseInt(cart2.get(i)[6]) + 1);
                        modelCart2.setValueAt(cart2.get(i)[6],i,6);

                        cart2.get(i)[7] = String.valueOf(Double.parseDouble(cart2.get(i)[3])*Integer.parseInt(cart2.get(i)[6]));
                        modelCart2.setValueAt(cart2.get(i)[7],i,7);

                        flag = 1;
                    }
                }
                if(flag == 0){
                    String[] productForCart = new String[8];
                    productForCart[0] = String.valueOf(cart2.size()+1);
                    for(int i = 1; i < 6; i++){
                        if(i == 2)
                            productForCart[i] = SCUtils.capitalize(product[i-1]);
                        else
                            productForCart[i] = product[i-1];
                    }
                    productForCart[6] = "1";
                    productForCart[7] = product[2];
                    cart2.add(productForCart);
                    modelCart2.addRow(productForCart);
                }
                calculateCartTotal(2);
                calculateCartCount(2);
            }
            else if(barcode.startsWith("C_")){
                int flag = 0;
                for(int i = 0; i < cart3.size(); i++){
                    if(cart3.get(i)[1].equals(pid)){
                        cart3.get(i)[6] = String.valueOf(Integer.parseInt(cart3.get(i)[6]) + 1);
                        modelCart3.setValueAt(cart3.get(i)[6],i,6);

                        cart3.get(i)[7] = String.valueOf(Double.parseDouble(cart3.get(i)[3])*Integer.parseInt(cart3.get(i)[6]));
                        modelCart3.setValueAt(cart3.get(i)[7],i,7);
                        flag = 1;
                    }
                }
                if(flag == 0){
                    String[] productForCart = new String[8];
                    productForCart[0] = String.valueOf(cart3.size()+1);
                    for(int i = 1; i < 6; i++){
                        if(i == 2)
                            productForCart[i] = SCUtils.capitalize(product[i-1]);
                        else
                            productForCart[i] = product[i-1];
                    }
                    productForCart[6] = "1";
                    productForCart[7] = product[2];
                    cart3.add(productForCart);
                    modelCart3.addRow(productForCart);
                }
                calculateCartTotal(3);
                calculateCartCount(3);
            }
            else if(barcode.startsWith("D_")){
                int flag = 0;
                for(int i = 0; i < cart4.size(); i++){
                    if(cart4.get(i)[1].equals(pid)){
                        cart4.get(i)[6] = String.valueOf(Integer.parseInt(cart4.get(i)[6]) + 1);
                        modelCart4.setValueAt(cart4.get(i)[6],i,6);

                        cart4.get(i)[7] = String.valueOf(Double.parseDouble(cart4.get(i)[3])*Integer.parseInt(cart4.get(i)[6]));
                        modelCart4.setValueAt(cart4.get(i)[7],i,7);
                        flag = 1;
                    }
                }
                if(flag == 0){
                    String[] productForCart = new String[8];
                    productForCart[0] = String.valueOf(cart4.size()+1);
                    for(int i = 1; i < 6; i++){
                        if(i == 2)
                            productForCart[i] = SCUtils.capitalize(product[i-1]);
                        else
                            productForCart[i] = product[i-1];
                    }
                    productForCart[6] = "1";
                    productForCart[7] = product[2];
                    cart4.add(productForCart);
                    modelCart4.addRow(productForCart);
                }
                calculateCartTotal(4);
                calculateCartCount(4);
            }
        }
    }


    private void calculateCartTotal(int cartNo){
        double amt = 0;
        if(cartNo == 1) {
            for (int i = 0; i < cart1.size(); i++)
                amt += Double.parseDouble(cart1.get(i)[3])*Integer.parseInt(cart1.get(i)[6]);
            txtCart1Total.setText(String.valueOf(amt));
        }
        else if(cartNo == 2) {
            for (int i = 0; i < cart2.size(); i++)
                amt += Double.parseDouble(cart2.get(i)[3])*Integer.parseInt(cart2.get(i)[6]);
            txtCart2Total.setText(String.valueOf(amt));

        }
        else if(cartNo == 3) {
            for (int i = 0; i < cart3.size(); i++)
                amt += Double.parseDouble(cart3.get(i)[3])*Integer.parseInt(cart3.get(i)[6]);
            txtCart3Total.setText(String.valueOf(amt));

        }
        else if(cartNo == 4) {
            for (int i = 0; i < cart4.size(); i++)
                amt += Double.parseDouble(cart4.get(i)[3])*Integer.parseInt(cart4.get(i)[6]);
            txtCart4Total.setText(String.valueOf(amt));

        }

    }

    private void calculateCartCount(int cartNo){
        int count = 0;
        if(cartNo == 1) {
            for (int i = 0; i < cart1.size(); i++)
                count += Integer.parseInt(cart1.get(i)[6]);
            txtCart1Count.setText(String.valueOf(count));
        }
        else if(cartNo == 2) {
            for (int i = 0; i < cart2.size(); i++)
                count += Integer.parseInt(cart2.get(i)[6]);
            txtCart2Count.setText(String.valueOf(count));
        }
        else if(cartNo == 3) {
            for (int i = 0; i < cart3.size(); i++)
                count += Integer.parseInt(cart3.get(i)[6]);
            txtCart3Count.setText(String.valueOf(count));

        }
        else if(cartNo == 4) {
            for (int i = 0; i < cart4.size(); i++)
                count += Integer.parseInt(cart4.get(i)[6]);
            txtCart4Count.setText(String.valueOf(count));
        }
    }

    private void generatePDFFromTable(JTable table, String totalAmount, String totalCount, int cartNo){
        try {
            Document doc = new Document(new com.itextpdf.text.Rectangle(80,210));
            doc.setMargins(-8,-8,0,3);

            Document backend_doc = new Document(new com.itextpdf.text.Rectangle(PageSize.A7));
            backend_doc.setMargins(-8,-8,3,3);

            String billName="D:\\Barcode\\bills\\bill_"+new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(new Date())+".pdf";
            PdfWriter.getInstance(doc, new FileOutputStream(billName));
            PdfWriter.getInstance(backend_doc, new FileOutputStream("D:\\Barcode\\bills\\backend_bill_"+new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(new Date())+".pdf"));

            doc.open();
            backend_doc.open();

            com.itextpdf.text.Font font =  FontFactory.getFont(FontFactory.TIMES_ROMAN,3);

            PdfPTable pdfTable = new PdfPTable(table.getColumnCount()-2);

            float[] columnWidths = new float[]{4f, 9f, 7f, 6f, 5f, 7f};
            pdfTable.setWidths(columnWidths);
            //adding table headers
            for (int i = 0; i < table.getColumnCount(); i++) {

                if(i == 4) pdfTable.addCell(new Phrase("Weight",font));
                else if(i == 1 || i == 5) continue;
                else pdfTable.addCell(new Phrase(table.getColumnName(i), font));
            }

            PdfPTable backend_pdfTable = new PdfPTable(pdfTable);
            //extracting data from the JTable and inserting it to PdfPTable
            double totalCGST = 0, totalSGST = 0;
            for (int row = 0; row < table.getRowCount(); row++) {
                String pid = table.getModel().getValueAt(row, 1).toString();
                double amount = Double.parseDouble(table.getModel().getValueAt(row, 7).toString());

                double cgst = Double.parseDouble(Product.getGST(pid, 0));
                double sgst = Double.parseDouble(Product.getGST(pid, 1));
                double totalGST = cgst + sgst;
                double amountBeforeGST = 100*amount/(100+totalGST);

                totalCGST += cgst*amountBeforeGST/100;
                totalSGST += sgst*amountBeforeGST/100;

                for (int col = 0; col < table.getColumnCount(); col++) {
                    if(col == 1 || col == 5)
                        continue;
                    else if(col == 4){
                        Phrase weightPhrase = new Phrase(table.getModel().getValueAt(row, col).toString()+" "+table.getModel().getValueAt(row, col+1).toString(),font);
                        pdfTable.addCell(weightPhrase);
                        backend_pdfTable.addCell(weightPhrase);
                    }
                    else{
                        Phrase ph = new Phrase(table.getModel().getValueAt(row, col).toString(), font);
                        pdfTable.addCell(ph);
                        if(col == 2)
                            backend_pdfTable.addCell(new Phrase(table.getModel().getValueAt(row, col).toString()+"("+totalGST+")", font));
                        else
                            backend_pdfTable.addCell(ph);
                    }
                }
            }


            doc = addHeadersInPDF(doc);
            doc.add(pdfTable);
            doc.add(new Paragraph("            Items Count : " + totalCount, font));
            doc.add(new Paragraph("         Total Amount : Rs. " + String.format("%.2f", Double.parseDouble(totalAmount)), FontFactory.getFont(FontFactory.TIMES_ROMAN,4, Font.BOLD)));

//            PrinterJob job = PrinterJob.getPrinterJob();
//            PageFormat pf = job.defaultPage();
//            Paper p = pf.getPaper();
//            int resolution = 72; // dpi
//            p.setSize(UnitConv.mm2px(62, resolution), UnitConv.mm2px(40, resolution));
//            p.setImageableArea(0, 0, UnitConv.mm2px(62, resolution), UnitConv.mm2px(40, resolution));
//            pf.setPaper(doc);
//            pf.setOrientation(PageFormat.LANDSCAPE);
//
//            job.setPrintable(this, pf);
//
//            if (job.printDialog()) {
//                try {
//                    job.print();
//                } catch (Exception PrintException) {
//                    PrintException.printStackTrace();
//                }
//            }

            doc.close();

            //Print
            System.out.println(new File(billName));
            PDDocument document = new PDDocument();
            document = PDDocument.load(new File(billName));
            PrintService myPrintService = findPrintService("Darshit Printer");
            if(myPrintService != null) {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPageable(new PDFPageable(document));
                job.setPrintService(myPrintService);
                try {
                    job.print();
                } catch (Exception e) {
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    String exceptionAsString = sw.toString();
                    SCUtils.logErrors("PRINT BILL:\n"+exceptionAsString);
                    JOptionPane.showMessageDialog(null, "Error printing bill: "+e);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Cannot find printer");
                SCUtils.logErrors("FINDING PRINTER\n");
            }
            document.close();


            backend_doc = addHeadersInPDF(backend_doc);
            backend_doc.add(backend_pdfTable);
            backend_doc.add(new Paragraph("            Items Count : " + totalCount, font));
            double totalWithoutGST = Double.parseDouble(totalAmount)-totalCGST-totalSGST;
            backend_doc.add(new Paragraph("            Total Amount without GST : Rs. " + String.format("%.2f", totalWithoutGST), FontFactory.getFont(FontFactory.TIMES_ROMAN,4, Font.BOLD)));
            backend_doc.add(new Paragraph("            Total CGST : Rs. " + String.format("%.2f", totalCGST), FontFactory.getFont(FontFactory.TIMES_ROMAN,7, Font.BOLD)));
            backend_doc.add(new Paragraph("            Total SGST : Rs. " + String.format("%.2f", totalSGST), FontFactory.getFont(FontFactory.TIMES_ROMAN,7, Font.BOLD)));
            backend_doc.add(new Paragraph("         Total Amount : Rs. " + String.format("%.2f", Double.parseDouble(totalAmount)), FontFactory.getFont(FontFactory.TIMES_ROMAN,4, Font.BOLD)));
            backend_doc.close();


            JOptionPane.showMessageDialog(null, "PDF generated successfully");
            clearCart(cartNo);



        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            SCUtils.logErrors("GENERATE PDF:\n"+exceptionAsString);
            JOptionPane.showMessageDialog(null, "Error generating pdf: "+e);
        }
    }

    private Document addHeadersInPDF(Document doc){
        try {
            Paragraph p;

            //Font font = new Font("",Font.PLAIN, 10);

            com.itextpdf.text.Font font =  FontFactory.getFont(FontFactory.TIMES_ROMAN,3);

            p = new Paragraph("\n  PRAGJI HANSRAJ & CO.",FontFactory.getFont(FontFactory.TIMES_ROMAN,4, Font.BOLD));
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);

            p = new Paragraph("Provision Stores", font);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);

            p = new Paragraph("30, Navi Chawl, Bazar Peth, Bhiwandi-421302", font);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);

            p = new Paragraph("Dist. Thane. Tel.: 9970470027", font);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);

            p = new Paragraph("   ___________________________________________", font);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingBefore(-4);
            doc.add(p);

            doc.add(new Paragraph("           GSTIN:27AAIPT422A1Z1 FSSAI LIC NO:11516018000720", FontFactory.getFont(FontFactory.TIMES_ROMAN,3)));
            doc.add(new Paragraph("           Date & Time : " + new SimpleDateFormat("yyyy/MM/dd   HH:mm").format(new Date()) + "\n\n", font));
        }
        catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            SCUtils.logErrors("ADD PDF HEADERS:\n"+exceptionAsString);
            JOptionPane.showMessageDialog(null, "Error generating headers:"+e);
        }
        return doc;
    }

    public void clearCart(int cartNo){
        if(cartNo == 1){
            cart1 = new ArrayList<>();
            modelCart1.setRowCount(0);
            txtCart1Total.setText("0.0");
            txtCart1Count.setText("0");
        }
        else if(cartNo == 2){
            cart2 = new ArrayList<>();
            modelCart2.setRowCount(0);
            txtCart2Total.setText("0.0");
            txtCart2Count.setText("0");
        }
        else if(cartNo == 3){
            cart3 = new ArrayList<>();
            modelCart3.setRowCount(0);
            txtCart3Total.setText("0.0");
            txtCart3Count.setText("0");
        }
        else if(cartNo == 4){
            cart4 = new ArrayList<>();
            modelCart4.setRowCount(0);
            txtCart4Total.setText("0.0");
            txtCart4Count.setText("0");
        }
    }

    private static PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            if (printService.getName().trim().equals(printerName)) {
                return printService;
            }
        }
        return null;
    }

//    private boolean addToCart(String[] product, int cartNo, int quantity){
//        return true;
//    }

    public static void main(String[] args) {
        new CartUI();
        System.out.println(PageSize.A7);

    }

}
