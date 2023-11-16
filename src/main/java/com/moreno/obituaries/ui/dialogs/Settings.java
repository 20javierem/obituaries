package com.moreno.obituaries.ui.dialogs;

import com.formdev.flatlaf.extras.components.FlatButton;
import com.formdev.flatlaf.extras.components.FlatComboBox;
import com.moreno.obituaries.utilities.Properties;
import com.moreno.obituaries.utilities.Utilities;
import org.jdesktop.swingx.border.DropShadowBorder;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JDialog {
    private JPanel contentPane;
    private JButton hechoButton;
    private JButton btnSave;
    private JButton btnApply;
    private FlatComboBox cbbFontSize;
    private FlatButton btnColorBacground2;
    private FlatButton btnColorPrincipal;
    private FlatButton btnColorBackground;
    private FlatButton btnColorForeground;

    public Settings() {
        super(Utilities.jFrame, "Configuraciones", true);
        init();
        btnApply.addActionListener(e -> applyChanges());
        btnColorBackground.addActionListener(e -> {
            showColorChooser(btnColorBackground, "background");
        });
        btnColorBacground2.addActionListener(e -> {
            showColorChooser(btnColorBacground2, "background2");
        });
        btnColorPrincipal.addActionListener(e -> {
            showColorChooser(btnColorPrincipal, "accentColor");
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnColorForeground.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showColorChooser(btnColorForeground, "foreground");
            }
        });
        ((JTextField) cbbFontSize.getEditor().getEditorComponent()).getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                verify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                verify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                verify();
            }
        });
        hechoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void verify() {
        String newSize = ((JTextField) cbbFontSize.getEditor().getEditorComponent()).getText();
        btnApply.setEnabled(!String.valueOf(Properties.getInstance().getFont().getSize()).equals(newSize));
    }

    private void showColorChooser(FlatButton btn, String variable) {
        JColorChooser colorChooser = new JColorChooser(btn.getBackground());
        int resultado = JOptionPane.showConfirmDialog(this, colorChooser, "Selecciona un color", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (resultado == JOptionPane.OK_OPTION) {
            Color color = colorChooser.getColor();
            btn.setBackground(color);
            String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
            switch (variable) {
                case "background" -> Properties.getInstance().setBackground(hex);
                case "background2" -> Properties.getInstance().setPanelBackground(hex);
                case "accentColor" -> Properties.getInstance().setAccentColor(hex);
                case "foreground" -> Properties.getInstance().setForeground(hex);
            }
            applyChanges();
        } else {
            System.out.println("No se seleccionó ningún color.");
        }
    }

    private void applyChanges() {
        Properties.getInstance().setFontSize(String.valueOf(cbbFontSize.getSelectedItem()));
        Properties.getInstance().save();
        updateTheme();
        Utilities.updateUI(true);
        Utilities.updateComponents(Utilities.jFrame.getRootPane());
        Utilities.updateComponents(getRootPane());
        Utilities.updateUI(false);
        loadSettings();
        pack();
    }

    private void updateTheme() {
        Utilities.loadTheme();
        Utilities.updateUI(true);
        Utilities.updateComponents(Utilities.jFrame.getRootPane());
        Utilities.updateComponents(getRootPane());
        Utilities.updateUI(false);
    }

    private void init() {
        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(getOwner());
        getRootPane().setDefaultButton(btnSave);
        loadSettings();
    }

    private void loadSettings() {
        btnColorBackground.setBackground(UIManager.getColor("background"));
        btnColorBacground2.setBackground(UIManager.getColor("Panel.background"));
        btnColorPrincipal.setBackground(UIManager.getColor("accentColor"));
        btnColorForeground.setBackground(UIManager.getColor("foreground"));
        cbbFontSize.setSelectedItem(Properties.getInstance().getFont().getSize());
    }
}
