package com.company;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;


public class RoundedJTextField extends JTextField{
    private Shape shape;
    public RoundedJTextField(int size) {
        super(size);
        setOpaque(false);
    }
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 3, 3);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(175, 175, 175));
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 3, 3);
    }
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 5, 5);
        }
        return shape.contains(x, y);
    }
}
