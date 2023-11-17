package com.moreno.obituaries.ui.frames;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.components.FlatButton;
import com.formdev.flatlaf.extras.components.FlatToggleButton;
import com.formdev.flatlaf.extras.components.FlatToolBar;
import com.moreno.obituaries.App;
import com.moreno.obituaries.ui.custom.TabbedPane;
import com.moreno.obituaries.ui.custom.ToggleButtonCustom;
import com.moreno.obituaries.ui.custom.drawer.Drawer;
import com.moreno.obituaries.ui.custom.drawer.DrawerController;
import com.moreno.obituaries.ui.dialogs.Settings;
import com.moreno.obituaries.ui.panes.PaneDetalles;
import com.moreno.obituaries.ui.panes.PaneNotas;
import com.moreno.obituaries.ui.panes.PaneNotificaciones;
import com.moreno.obituaries.ui.panes.PaneTareas;
import com.moreno.obituaries.utilities.Utilities;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class Dashboard extends JFrame{
    private JPanel contentPane;
    private JMenuBar menuBar;
    private JMenu btnMenuBarProducts;
    private JMenu btnMenuBarSales;
    private JMenu btnMenuBarStart;
    private JMenu btnMenuBarWorkers;
    private FlatToolBar flatToolBar1;
    private FlatButton toolbarButton1;
    private FlatButton toolbarButton2;
    private FlatButton toolbarButton3;
    private FlatButton toolbarButton4;
    private FlatToolBar trailingComponent;
    private FlatButton toolbarTabbedPaneButton;
    private FlatButton btnDrawer;
    private ToggleButtonCustom btn1;
    private ToggleButtonCustom btn2;
    private ToggleButtonCustom btn3;
    private JPanel headerDrawer;
    private JLabel imgLogo;
    private JPanel contentDrawer;
    private PaneDetalles paneDetalles;
    private PaneNotas paneNotas;
    private PaneNotificaciones paneNotificaciones;
    private PaneTareas paneTareas;
    private FlatToggleButton dashboardFlatToggleButton;
    private FlatToggleButton historialFlatToggleButton;
    private FlatToggleButton nuevaFlatToggleButton;
    private FlatToggleButton todosFlatToggleButton;
    private FlatToggleButton nuevoFlatToggleButton;
    private TabbedPane tabbedPane;
    private JSplitPane splitPane;
    private JMenu menuSettings;
    private DrawerController drawer;


    public Dashboard() {
        super("Obituaries");
        init();
        btnDrawer.addActionListener(e -> {
            drawer.show();
        });
        btn1.addActionListener(e -> verifySelectionMenu(btn1));
        btn2.addActionListener(e -> verifySelectionMenu(btn2));
        btn3.addActionListener(e -> verifySelectionMenu(btn3));
        dashboardFlatToggleButton.addActionListener(e -> {
//            if (catalogue == null || tabbedPane.indexOfComponent(catalogue.getTabPane()) == -1) {
//                catalogue = new Catalogue();
//                catalogue.getTabPane().setOption(dashboardFlatToggleButton);
//                tabbedPane.addTab(catalogue.getTabPane().getTitle(), catalogue.getTabPane());
//                catalogue.loadPets();
//            }
//            tabbedPane.setSelectedComponent(catalogue.getTabPane());
            SwingUtilities.invokeLater(() -> drawer.hide());
        });
    }

    private void init() {
        setJMenuBar(menuBar);
        setContentPane(contentPane);
        pack();
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        loadIcons();
        loadMenus();
        createDrawer();
        Utilities.jFrame=this;
        Utilities.tabbedPane=tabbedPane;
        tabbedPane.setPreferredSize(tabbedPane.getParent().getPreferredSize());
        loadJmenus();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createDrawer() {
        drawer = Drawer.newDrawer(this)
                .header(headerDrawer)
                .addChild(contentDrawer)
                .build();
    }

    private void verifySelectionMenu(ToggleButtonCustom toggleButtonCustom) {
        boolean isSelected = toggleButtonCustom.isSelected();
        btn1.setSelected(false);
        btn2.setSelected(false);
        btn3.setSelected(false);
        splitPane.setRightComponent(null);
        if (isSelected) {
            toggleButtonCustom.setSelected(true);
            splitPane.setRightComponent(toggleButtonCustom.getContentPane());
        }
    }

    private void loadMenus() {
        paneDetalles = new PaneDetalles();
        btn1.setContentPane(paneDetalles.getContentPane());
        paneNotas = new PaneNotas();
        btn2.setContentPane(paneNotas.getContentPane());
        paneNotificaciones = new PaneNotificaciones();
        btn3.setContentPane(paneNotificaciones.getContentPane());
        paneTareas = new PaneTareas();
    }

    private void loadJmenus(){
        JMenuItem jMenuSettings = new JMenuItem("Configuraciones");
        jMenuSettings.addActionListener(e -> {
            drawer.hide();
            loadSettings();
        });
        jMenuSettings.setMnemonic(KeyEvent.VK_C);
        menuSettings.add(jMenuSettings);
    }

    private void loadSettings() {
        Settings settings = new Settings();
        settings.setVisible(true);
    }

    private void loadIcons() {
        btnDrawer.setIcon(new FlatSVGIcon(Objects.requireNonNull(App.class.getResource("icons/svg/hambur.svg"))));
        loadIcon(toolbarButton1);
        loadIcon(toolbarButton2);
        loadIcon(toolbarButton3);
        loadIcon(toolbarButton4);
        loadIcon(btn1,15.0,15.0);
        loadIcon(btn2,15.0,15.0);
        loadIcon(btn3,15.0,15.0);

        loadIcon(dashboardFlatToggleButton, 20.0, 20.0);
        loadIcon(historialFlatToggleButton, 20.0, 20.0);
        loadIcon(nuevaFlatToggleButton, 20.0, 20.0);
        loadIcon(todosFlatToggleButton, 20.0, 20.0);
        loadIcon(nuevoFlatToggleButton, 20.0, 20.0);

        loadIcon(toolbarTabbedPaneButton, 16.0, 16.0);
        loadIcon(imgLogo, 130.0, 130.0);
    }

    private void loadIcon(Object component) {
        loadIcon(component, 30.0, 30.0);
    }

    public static void loadIcon(Object component, double width, double height) {
        if (component instanceof JToggleButton) {
            ((JToggleButton) component).setIcon(Utilities.resizeIcon(Utilities.getLogo(), width, height));
        } else if (component instanceof FlatButton) {
            ((FlatButton) component).setIcon(Utilities.resizeIcon(Utilities.getLogo(), width, height));
        } else {
            ((JLabel) component).setIcon(Utilities.resizeIcon(Utilities.getLogo(), width, height));
        }
    }

}
