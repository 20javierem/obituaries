package com.moreno.obituaries.ui.panes;


import com.moreno.obituaries.App;
import com.moreno.obituaries.utilities.Utilities;

import javax.swing.*;
import java.util.Objects;

public class PaneNotificaciones {
    private JLabel lblImage;
    private JButton organizarButton;
    private JPanel contentPane;


    public PaneNotificaciones(){
        lblImage.setIcon(Utilities.resizeIcon(new ImageIcon(Objects.requireNonNull(App.class.getResource("images/logo-sin-fondo.png"))).getImage(), 50.0, 50.0));
    }

    public JPanel getContentPane(){
        return contentPane;
    }
}
