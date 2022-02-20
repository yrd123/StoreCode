package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class RoundButton extends JButton {
    public RoundButton(String text) {
        super(text);

        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);

        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(new Color(250, 250, 250));
            setForeground(Color.BLACK);
        } else {
            //g.setColor(new Color(232, 232, 232));
            g.setColor(new Color(243, 243, 243));
            //g.setColor(new Color(89, 66, 255));
            setForeground(Color.BLACK);//Цвет надписи
        }
        g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 5, 5);

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(172, 172, 172));
        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 5,5);
    }
}