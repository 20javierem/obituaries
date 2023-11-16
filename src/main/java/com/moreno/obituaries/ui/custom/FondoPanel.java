package com.moreno.obituaries.ui.custom;

import com.moreno.obituaries.App;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FondoPanel extends JPanel {
    private String fondo;
    private Dimension dimension;

    public FondoPanel(String fondo,Dimension dimension){
        this.fondo=fondo;
        this.dimension=dimension;
    }

    private Image imagen;
    @Override
    public void paint(Graphics g){
        imagen = new ImageIcon(Objects.requireNonNull(App.class.getResource(fondo))).getImage();
        Image img2=new ImageIcon(imagen.getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH)).getImage();
        g.drawImage(img2,0,0,getWidth(),getHeight(),this);
        setOpaque(false);
        super.paint(g);
    }
}
