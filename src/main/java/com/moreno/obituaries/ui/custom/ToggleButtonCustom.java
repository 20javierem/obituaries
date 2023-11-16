package com.moreno.obituaries.ui.custom;

import com.moreno.obituaries.App;
import com.moreno.obituaries.utilities.Utilities;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ToggleButtonCustom extends JToggleButton {
    private JPanel contentPane;
    private final double constant = 0.244827586206895;

    public ToggleButtonCustom() {
        setBorder(BorderFactory.createEmptyBorder());
        setIcon(Utilities.resizeIcon(new ImageIcon(Objects.requireNonNull(App.class.getResource("images/logo-sin-fondo.png"))).getImage(), 16.0, 16.0));
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        double x;
        double y;
        x = 8 + ((double) getWidth() * constant);
        y = 8 + ((double) getWidth() * constant);
        g2d.rotate(Math.PI / 2, x, y);
        super.paintComponent(g);
    }
    public void setContentPane(JPanel contentPane) {
        this.contentPane = contentPane;
    }

    public Component getContentPane() {
        Utilities.updateComponents(contentPane);
        return contentPane;
    }
}