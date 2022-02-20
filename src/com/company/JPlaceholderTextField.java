package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class JPlaceholderTextField extends JTextField {
    private String ph;
    private Shape shape;

    public JPlaceholderTextField(String ph) {
        this.ph = ph;
    }

   /* public JPlaceholderTextField() {
        this.ph = null;
    } */

    /**
     * Gets text, returns placeholder if nothing specified
     */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (super.getText().length() > 0 || ph == null) {
            return;
        }

        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 3, 3);
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(160, 160, 160));
        g2.setFont(new Font("",Font.PLAIN,13));

        g2.drawString(ph, getInsets().left+10, g.getFontMetrics().getMaxAscent() + getInsets().top+8);
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
