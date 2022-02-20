package com.company;

import javax.swing.*;
import java.io.*;

import com.company.Product.Product;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SCUtils {
    public static String getRandomNumber(){
        StringBuilder number = new StringBuilder();
        while(number.length() < 13)
            number.append((int)(Math.random()*10));
        return number.toString();
    }

    public static String capitalize(String name){
        return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public static void generateBarcode(String number, String name){
        try {
            Code128Bean writer = new Code128Bean();

            //Configure the barcode generator
            final int dpi = 160;
            writer.setModuleWidth(UnitConv.in2mm(2.8f / dpi));
            writer.doQuietZone(false);

            //Open output file
            String path = "D:\\Barcode\\generatedBarcodes\\"+ name + "_" +number+".jpg";
            File outputFile = new File(path);

            FileOutputStream out = new FileOutputStream(outputFile);

            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

            //Generate the barcode
            writer.generateBarcode(canvas, number);

            //Signal end of generation
            canvas.finish();
            //System.out.println("barcode created");

                /*Code128Writer writer = new Code128Writer();
                BitMatrix matrix = writer.encode(number, BarcodeFormat.CODE_128, 300, 200);
                MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));*/

                /*String path="D:\\Barcode\\bar1.jpg";
                BufferedImage bf= ImageIO.read(new FileInputStream(path));
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
                        new BufferedImageLuminanceSource(bf)
                ));

                Result result = new MultiFormatReader().decode(bitmap);
                System.out.println(result.getText());*/
                JOptionPane.showMessageDialog(null, "Barcode Generatedd Successfully");
        }
        catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            SCUtils.logErrors("GENERATE BARCODE:\n"+exceptionAsString);
            JOptionPane.showMessageDialog(null, "Error in generating Barcode: "+e);
        }
    }

    public static void writeToFile(String filepath, String input){
        try {

            BufferedWriter out = new BufferedWriter(
                    new FileWriter(filepath, true));

            // Writing on output stream
            out.write(input);
            // Closing the connection
            out.close();
        }

        // Catch block to handle the exceptions
        catch (IOException e) {
            // Display message when exception occurs
            JOptionPane.showMessageDialog(null,"Error while adding to file: "+e);
        }
    }

    public static void logErrors(String error){
        String path = "D:\\Barcode\\log.txt";
        String errorWithTimestamp = "Date: " + new SimpleDateFormat("yyyy/MM/dd   HH:mm").format(new Date()) + "\n" + error + "\n\n";
        writeToFile(path,errorWithTimestamp);
    }

    public static void logQuery(String query){
        String path = "D:\\Barcode\\queries.txt";
        writeToFile(path,query);
    }


}
