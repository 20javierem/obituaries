package com.moreno.obituaries.ui.custom;

import com.formdev.flatlaf.extras.components.FlatPasswordField;
import com.formdev.flatlaf.icons.FlatRevealIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomPasswordField extends FlatPasswordField {
    private JButton btnShowPassword;

    public CustomPasswordField(){
        loadShowPassword();
    }

    private void loadShowPassword() {
        btnShowPassword =new JButton();
        btnShowPassword.setVisible(false);
        btnShowPassword.setContentAreaFilled(false);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                btnShowPassword.setVisible(getPassword().length > 0);
            }
        });
        btnShowPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (getEchoChar() == '•') {
                    setEchoChar((char) 0);
                    btnShowPassword.setIcon(new FlatRevealIcon());
                } else {
                    setEchoChar('•');
                    btnShowPassword.setIcon(new FlatRevealIcon());
                }
            }
        });
        btnShowPassword.setIcon(new FlatRevealIcon());
        btnShowPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        setTrailingComponent(btnShowPassword);
    }

    @Override
    public void setText(String t) {
        super.setText(t);
        if(getPassword().length>0){
            btnShowPassword.setVisible(true);
        }
    }
}
