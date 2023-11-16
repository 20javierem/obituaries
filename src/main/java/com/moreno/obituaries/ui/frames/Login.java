package com.moreno.obituaries.ui.frames;

import com.formdev.flatlaf.extras.components.FlatButton;
import com.formdev.flatlaf.extras.components.FlatTextField;
import com.moreno.obituaries.ui.custom.CustomPasswordField;
import com.moreno.obituaries.utilities.Utilities;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JPanel contentPane;
    private FlatTextField fieldUser;
    private FlatButton btnInitSession;
    private CustomPasswordField fieldPassword;
    private JLabel lblLogo;
    private JCheckBox ckRememberUser;
    private JLabel lblRegister;
    private JLabel lblTerminos;

    public Login() {
        super("Inicio de sesión");
        Image image = Utilities.getLogo();
        if (image != null) {
            lblLogo.setIcon(Utilities.resizeIcon(image, 130.0, 130.0));
        }setContentPane(contentPane);
        getRootPane().setDefaultButton(btnInitSession);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        btnInitSession.addActionListener(e -> {
            Dashboard dashboard = new Dashboard();
            dispose();
            dashboard.setVisible(true);
        });
    }

}
