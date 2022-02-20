package com.company.Product;

import com.company.*;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Locale;

public class ProductsUI extends JFrame implements ActionListener {
    JTable productsTable;
    JPlaceholderTextField txtId, txtName, txtPrice, txtQuantity, txtSearch, txtCGST, txtSGST;
    JComboBox txtUnits;
    RoundButton btnAdd, btnUpdate, btnDelete, btnReset, btnGenerateBarcode, btnBack;

    public ProductsUI(){


        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Products");

        Font helvetica_neue = new Font("", Font.PLAIN, 14);

        JPanel addProductPanel = new JPanel();
        addProductPanel.setLayout(null);
        addProductPanel.setBounds(20,20,600,760);
        addProductPanel.setBackground(Color.WHITE);
        add(addProductPanel);

        /*JLabel lblTxtName = new JLabel("Name: ");
        lblTxtName.setFont(new Font("Serif", Font.PLAIN, 20));
        lblTxtName.setBounds(40,40,80,30);
        addProductPanel.add(lblTxtName);*/

        txtName = new JPlaceholderTextField("Name");
        txtName.setBounds(82,60,436,40);
        txtName.setFont(helvetica_neue);
        addProductPanel.add(txtName);

        /*JLabel lblTxtPrice = new JLabel("Price: ");
        lblTxtPrice.setFont(new Font("Serif", Font.PLAIN, 20));
        lblTxtPrice.setBounds(40,100,80,30);
        addProductPanel.add(lblTxtPrice);*/

        txtPrice = new JPlaceholderTextField("Price");
        txtPrice.setBounds(82,130,436,40);
        txtPrice.setFont(helvetica_neue);
        addProductPanel.add(txtPrice);

        /*JLabel lblTxtQuantity = new JLabel("Quantity: ");
        lblTxtQuantity.setFont(new Font("Serif", Font.PLAIN, 20));
        lblTxtQuantity.setBounds(40,160,80,30);
        addProductPanel.add(lblTxtQuantity);*/

        txtQuantity = new JPlaceholderTextField("Quantity");
        txtQuantity.setFont(helvetica_neue);
        txtQuantity.setBounds(82,200,273,40);
        addProductPanel.add(txtQuantity);

        /*JLabel lblTxtUnits = new JLabel("Units: ");
        lblTxtUnits.setFont(new Font("Serif", Font.PLAIN, 20));
        lblTxtUnits.setBounds(40,220,82,30);
        addProductPanel.add(lblTxtUnits);*/

        txtUnits = new JComboBox(new String[]{"kg","g","l","ml"});
        txtUnits.setFont(helvetica_neue);
        txtUnits.setBackground(Color.WHITE);
        txtUnits.setBounds(385,200,133,40);
        addProductPanel.add(txtUnits);

        /*JLabel lblTxtId = new JLabel("Id: ");
        lblTxtId.setFont(new Font("Serif", Font.PLAIN, 20));
        lblTxtId.setBounds(40,280,80,30);
        addProductPanel.add(lblTxtId);*/

        txtCGST = new JPlaceholderTextField("CGST");
        txtCGST.setBounds(82,270,203,40);
        txtCGST.setFont(helvetica_neue);
        addProductPanel.add(txtCGST);

        txtSGST = new JPlaceholderTextField("SGST");
        txtSGST.setBounds(315,270,203,40);
        txtSGST.setFont(helvetica_neue);
        addProductPanel.add(txtSGST);

        txtId = new JPlaceholderTextField("Id");
        txtId.setBounds(82,340,436,40);
        txtId.setFont(helvetica_neue);
        addProductPanel.add(txtId);



        btnAdd = new RoundButton("Add");
        btnAdd.setBounds(82,410,203,70);
        btnAdd.setFont(helvetica_neue);
        addProductPanel.add(btnAdd);
        btnAdd.addActionListener(this);

        btnUpdate = new RoundButton("Update");
        btnUpdate.setBounds(315,410,203,70);
        btnUpdate.setFont(helvetica_neue);
        addProductPanel.add(btnUpdate);
        btnUpdate.addActionListener(this);

        btnDelete = new RoundButton("Delete");
        btnDelete.setBounds(82,510,203,70);
        btnDelete.setFont(helvetica_neue);
        addProductPanel.add(btnDelete);
        btnDelete.addActionListener(this);

        btnReset = new RoundButton("Reset");
        btnReset.setBounds(315,510,203,70);
        btnReset.setFont(helvetica_neue);
        addProductPanel.add(btnReset);
        btnReset.addActionListener(this);

        btnGenerateBarcode = new RoundButton("Generate");
        btnGenerateBarcode.setBounds(82,610,203,70);
        btnGenerateBarcode.setFont(helvetica_neue);
        addProductPanel.add(btnGenerateBarcode);
        btnGenerateBarcode.addActionListener(this);

        btnBack = new RoundButton("Back");
        btnBack.setBounds(315,610,203,70);
        btnBack.setFont(helvetica_neue);
        addProductPanel.add(btnBack);
        btnBack.addActionListener(this);



        /*JPanel columnNames = new JPanel();
        columnNames.setLayout(null);
        columnNames.setBounds(20,90,1500,50);
        columnNames.setBackground(Color.WHITE);
        add(columnNames);

        JLabel lblId = new JLabel("Id");
        lblId.setBounds(100,0,1500/5,50);
        columnNames.add(lblId);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(440,0,1500/5,50);
        columnNames.add(lblName);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(720,0,1500/5,50);
        columnNames.add(lblPrice);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setBounds(1000,0,1500/5,50);
        columnNames.add(lblQuantity);

        JLabel lblUnits = new JLabel("Units");
        lblUnits.setBounds(1300,0,1500/5,50);
        columnNames.add(lblUnits);*/

        /*JLabel lblSearch = new JLabel("Search: ");
        lblSearch.setFont(new Font("Serif", Font.PLAIN, 20));
        lblSearch.setBounds(680,22,80,30);
        add(lblSearch);*/

        txtSearch = new JPlaceholderTextField("Search");
        txtSearch.setBounds(641,23,850,40);
        txtSearch.setFont(helvetica_neue);
        add(txtSearch);
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                DefaultTableModel model = (DefaultTableModel) productsTable.getModel();
                String searchText = txtSearch.getText().toLowerCase();
                TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
                productsTable.setRowSorter(tr);
                tr.setRowFilter(RowFilter.regexFilter(searchText));
            }
        });

        Font bold_font = new Font("",Font.BOLD,11);

        UIManager.getDefaults().put("TableHeader.cellBorder" , BorderFactory.createEmptyBorder(0,3,0,0));

        productsTable = new JTable();
        productsTable.getTableHeader().setFont(bold_font);
        productsTable.getTableHeader().setPreferredSize(new Dimension(0,40));
        productsTable.getTableHeader().setBackground(new Color(240, 240, 240));
        ((DefaultTableCellRenderer)productsTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);
        productsTable.setFillsViewportHeight(true);
        productsTable.setBackground(new Color(250, 250, 250));
        productsTable.setShowVerticalLines(false);
        productsTable.setRowHeight(30);
        JScrollPane sp = new JScrollPane();
        sp.setBounds(640,75,850,705);
        sp.setViewportView(productsTable);
        add(sp);
        //todo scroll

        loadData();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        productsTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int i = productsTable.getSelectedRow();
                    txtId.setText((String) productsTable.getValueAt(i,0));
                    txtName.setText((String) productsTable.getValueAt(i,1));
                    txtPrice.setText(String.valueOf(productsTable.getValueAt(i,2)));
                    txtQuantity.setText(String.valueOf(productsTable.getValueAt(i,3)));
                    txtUnits.setSelectedItem(productsTable.getValueAt(i,4));
                    txtCGST.setText(Product.getGST((String) productsTable.getValueAt(i,0),0));
                    txtSGST.setText(Product.getGST((String) productsTable.getValueAt(i,0),1));
                }
           }
        );
    }

    private void loadData(){
        insertPID();
        productsTable.setModel(DbUtils.resultSetToTableModel(Product.getProducts()));
    }

    private void insertPID(){
        String pid = SCUtils.getRandomNumber();
        while(Product.getProductIds().contains(pid)){
            pid = SCUtils.getRandomNumber();
        }
        txtId.setText(pid);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnAdd || ae.getSource() == btnUpdate){

            String pid = txtId.getText();
            if(pid.equals(null) || pid.equals("")){
                JOptionPane.showMessageDialog(null, "Enter valid id!");
                return;
            }

            String name = txtName.getText();
            if(name == null || name.equals("")){
                JOptionPane.showMessageDialog(null, "Enter valid name");
                return;
            }

            String units = (String) txtUnits.getSelectedItem();

            double price = 0;
            double quantity = 0;
            try {
                price = Double.parseDouble(txtPrice.getText());
                quantity = Double.parseDouble(txtQuantity.getText());
                if(price < 0 || quantity < 0)
                    throw new Exception();
            }
            catch(Exception e){
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                SCUtils.logErrors("RANDOM:\n"+exceptionAsString);
                JOptionPane.showMessageDialog(null, "Enter valid price or quantity!");
                return;
            }

            double cgst = 0;
            double sgst = 0;
            try {
                cgst = Double.parseDouble(txtCGST.getText());
                sgst = Double.parseDouble(txtSGST.getText());
                if(price < 0 || quantity < 0)
                    throw new Exception();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Enter valid CGST or SGST!");
                return;
            }

            if(ae.getSource() == btnAdd){
                if(Product.getProductIds().contains(pid)){
                    JOptionPane.showMessageDialog(null, "Delete product("+Product.getProductWithId(pid)[1] +")");
                    return;
                }
                else{
                    addProduct(pid, name.toLowerCase(), price, quantity, units.toLowerCase(), cgst, sgst);
                }
            }
            else if(ae.getSource() == btnUpdate){
                if(!Product.getProductIds().contains(pid)){
                    JOptionPane.showMessageDialog(null, "Product with this id does not exist");
                    return;
                }
                updateProduct(pid, name.toLowerCase(), price, quantity, units.toLowerCase(), cgst, sgst);
            }
             reset();

        }
        else if(ae.getSource() == btnDelete){
            String pid = txtId.getText();
            if(pid.equals(null) || pid.equals("")){
                JOptionPane.showMessageDialog(null, "Enter valid id!");
                return;
            }
            if(Product.getProductIds().contains(pid)){
                deleteProduct(pid);
            }
            else{
                JOptionPane.showMessageDialog(null, "Product with this id does not exist");
                return;
            }
            reset();
        }
        else if(ae.getSource() == btnReset){
            reset();
        }
        else if(ae.getSource() == btnGenerateBarcode){
            String pid = txtId.getText();
            if(pid.equals(null) || pid.equals("")){
                JOptionPane.showMessageDialog(null, "Enter valid id!");
                return;
            }
            String name = txtName.getText();
            if(name == null || name.equals("")){
                JOptionPane.showMessageDialog(null, "Enter valid name");
                return;
            }
            SCUtils.generateBarcode(pid, name);
            reset();
        }
        else if(ae.getSource() == btnBack){
            new Dashboard();
            dispose();
        }

    }



    //todo insert query with column names

    private void addProduct(String pid, String name, double price, double quantity, String units, double cgst, double sgst){
        String query = "Insert into product (pid,name,price,quantity,units) values ('"  + pid + "','" + name + "'," + price + "," + quantity + ",'" + units + "');";
        try {
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();

            int rowsAffected = s.executeUpdate(query);
            if(rowsAffected == 1){
                SCUtils.logQuery(query+"\n");
                query = "Insert into gst (pid,cgst,sgst) values ('"  + pid + "'," + cgst + "," + sgst + ");";
                rowsAffected = s.executeUpdate(query);
                if(rowsAffected == 1) {
                    SCUtils.logQuery(query+"\n");
                    JOptionPane.showMessageDialog(null, "Added product successfully");
                }
            }
            reset();
        }
        catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            SCUtils.logErrors("ADD PROD:\n"+exceptionAsString);
            JOptionPane.showMessageDialog(null,"Error adding products: "+e);
            reset();
        }
    }

    private void updateProduct(String pid, String name, double price, double quantity, String units, double cgst, double sgst){
        String query = "update product set name='" + name + "',price=" + price + ",quantity=" + quantity + ",units='" + units + "' where pid='" + pid + "';";
        try {
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();

            int rowsAffected = s.executeUpdate(query);
            if(rowsAffected == 1){
                SCUtils.logQuery(query+"\n");
                query = "update gst set cgst=" + cgst + ",sgst=" + sgst + " where pid='" + pid + "';";
                rowsAffected = s.executeUpdate(query);
                if(rowsAffected == 1){
                    SCUtils.logQuery(query+"\n");
                    JOptionPane.showMessageDialog(null,"Updated product successfully");
                }
            }
            reset();
        }
        catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            SCUtils.logErrors("UPDATE PROD:\n"+exceptionAsString);
            JOptionPane.showMessageDialog(null,e);
            reset();
        }
    }


    private void deleteProduct(String pid){
        String query = "Delete from product where pid='"  + pid + "';";
        try {
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();

            int rowsAffected = s.executeUpdate(query);
            if(rowsAffected == 1){
                SCUtils.logQuery(query+"\n");
                query = "Delete from gst where pid='"  + pid + "';";
                rowsAffected = s.executeUpdate(query);
                if(rowsAffected == 1) {
                    SCUtils.logQuery(query+"\n");
                    JOptionPane.showMessageDialog(null, "Deleted product successfully");
                }
            }
            reset();
        }
        catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            SCUtils.logErrors("DELETE PROD:\n"+exceptionAsString);
            JOptionPane.showMessageDialog(null,e);
            reset();
        }
    }

    private void reset(){
        txtName.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
        txtCGST.setText("0");
        txtSGST.setText("0");
        loadData();
    }

}
