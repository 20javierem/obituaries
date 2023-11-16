package com.moreno.obituaries.ui.custom;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.components.FlatTabbedPane;
import com.formdev.flatlaf.icons.FlatTabbedPaneCloseIcon;
import com.moreno.obituaries.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class TabbedPane extends FlatTabbedPane {
    private JPopupMenu pop_up;

    public TabbedPane() {
        super();
        setShowTabSeparators(true);
        setTabsClosable(true);
        setTabCloseCallback(this::removeOneTab);
        UIManager.put("TabbedPane.closeHoverForeground", Color.red);
        UIManager.put("TabbedPane.closePressedForeground", Color.red);
        UIManager.put("TabbedPane.closeArc", 999);
        UIManager.put("TabbedPane.closeIcon", new FlatTabbedPaneCloseIcon());
        updateUI();
        insertarButtons();
        getModel().addChangeListener(e -> verifySelected());
    }

    @Override
    public Component[] getComponents() {
        if (super.getComponents().length >= 1) {
            Component[] components = new Component[super.getComponents().length + 1];
            int i = 0;
            for (Component component : super.getComponents()) {
                components[i] = component;
                i++;
            }
            components[i] = pop_up;
            return components;
        }
        return super.getComponents();
    }

    @Override
    public void updateUI() {
        super.updateUI();
        verifySelected();
    }

    @Override
    public void addTab(String title, Component component) {
        super.addTab(title, component);
        setSelectedComponent(component);
        setTabCloseToolTipText(indexOfComponent(component), "Cerrar Pestaña " + title);
        verifySelected();
    }

    @Override
    public void setSelectedComponent(Component c) {
        super.setSelectedComponent(c);
        c.requestFocus();
        verifySelected();
    }

    private void removeOneTab(JTabbedPane jTabbedPane,int index){
        unpaintSelected();
        removeTabAt(index);
        verifySelected();
    }

    private void removeAllTabs() {
        unpaintSelected();
        removeAll();
        verifySelected();
    }

    private void removeOthersTabs(String title, Component component) {
        removeAll();
        addTab(title,component);
        verifySelected();
    }

    public void updateTab(TYPECHANGE TYPECHANGE) {
        if (getSelectedIndex() != -1) {
            if (getComponentAt(getSelectedIndex()) instanceof TabPane) {
                TabPane tabPane = (TabPane) getComponentAt(getSelectedIndex());
                if (tabPane.getOption() != null) {
                    tabPane.getOption().setSelected(true);
                }
                tabPane.getUiUpdate().updateUi(TYPECHANGE);
            }
        }
    }

    public void verifySelected() {
        unpaintSelected();
        paintSelected();
    }

    private void unpaintSelected() {
        for (Component component : getComponents()) {
            if (indexOfComponent(component) != -1) {
//                setEnabledAt(indexOfComponent(component), true);
//                setBackgroundAt(indexOfComponent(component), getBackground());
                if (component instanceof TabPane) {
                    TabPane tabPane = (TabPane) component;
                    if (tabPane.getOption() != null) {
                        tabPane.getOption().setSelected(false);
                    }
                }
            }
        }
    }

    public void paintSelected() {
        if(getSelectedIndex()!=-1){
//            setEnabledAt(getSelectedIndex(), false);
//            setBackgroundAt(getSelectedIndex(), lighten(getBackground(), 0.05f));
            if (getComponentAt(getSelectedIndex()) instanceof TabPane) {
                TabPane tabPane = (TabPane) getComponentAt(getSelectedIndex());
                if (tabPane.getOption() != null) {
                    tabPane.getOption().setSelected(true);
                    updateTab(TYPECHANGE.DEFAULT);
                }
            }
        }
        setVisibleTrailingComponent();
    }
    private void setVisibleTrailingComponent(){
        if(getTrailingComponent()!=null){
            getTrailingComponent().setVisible(getSelectedIndex()!=-1);
        }
    }
    private void insertarButtons() {
        //creacion de pop_up
        pop_up = new JPopupMenu();
        Point point = new Point();
        JMenuItem moveToLeft = new JMenuItem("Mover a la izquierda", new FlatSVGIcon(App.class.getResource("icons/svg/arrowCollapse.svg")));
        JMenuItem moveToRight = new JMenuItem("Mover a la derecha", new FlatSVGIcon(App.class.getResource("icons/svg/arrowExpand.svg")));
        JMenuItem closeTab = new JMenuItem("Cerrar Pestaña");
        JMenuItem closeOthers = new JMenuItem("Cerrar Otras Pestañas");
        JMenuItem closeAll = new JMenuItem("Cerrar Todas Las Pestañas");

        moveToLeft.addActionListener(e -> {
            if (getUI().tabForCoordinate(TabbedPane.this, point.x, point.y) > 0) {
                Component componentSelected = getSelectedComponent();
                Component component = getComponentAt(getUI().tabForCoordinate(TabbedPane.this, point.x, point.y));
                int newIndex = getUI().tabForCoordinate(TabbedPane.this, point.x, point.y) - 1;
                String tittle = getTitleAt(getUI().tabForCoordinate(TabbedPane.this, point.x, point.y));
                String tooltip = getToolTipTextAt(getUI().tabForCoordinate(TabbedPane.this, point.x, point.y));
                removeTabAt(getUI().tabForCoordinate(TabbedPane.this, point.x, point.y));
                insertTab(tittle, null, component, tooltip, newIndex);
                setSelectedComponent(componentSelected);
            }
        });
        moveToRight.addActionListener(e -> {
            if (getUI().tabForCoordinate(TabbedPane.this, point.x, point.y) < getTabCount() - 1) {
                Component componentSelected = getSelectedComponent();
                Component component = getComponentAt(getUI().tabForCoordinate(TabbedPane.this, point.x, point.y));
                int newIndex = getUI().tabForCoordinate(TabbedPane.this, point.x, point.y) + 1;
                String tittle = getTitleAt(getUI().tabForCoordinate(TabbedPane.this, point.x, point.y));
                String tooltip = getToolTipTextAt(getUI().tabForCoordinate(TabbedPane.this, point.x, point.y));
                removeTabAt(getUI().tabForCoordinate(TabbedPane.this, point.x, point.y));
                insertTab(tittle, null, component, tooltip, newIndex);
                setSelectedComponent(componentSelected);
            }
        });
        closeTab.addActionListener(e -> removeTabAt(getUI().tabForCoordinate(TabbedPane.this, point.x, point.y)));
        closeOthers.addActionListener(e -> {
            if (getSelectedIndex() != -1) {
                if (getComponentAt(getSelectedIndex()) instanceof TabPane) {
                    TabPane tabPane = (TabPane) getComponentAt(getUI().tabForCoordinate(TabbedPane.this, point.x, point.y));
                    removeOthersTabs(tabPane.getTitle(), tabPane);
                } else {
                    String tittle = getTitleAt(getSelectedIndex());
                    Component component = getComponentAt(getUI().tabForCoordinate(TabbedPane.this, point.x, point.y));
                    removeOthersTabs(tittle,component);
                }

            }
        });
        closeAll.addActionListener(e -> removeAll());
        pop_up.add(moveToLeft);
        pop_up.add(moveToRight);
        pop_up.add(closeTab);
        pop_up.addSeparator();
        pop_up.add(closeOthers);
        pop_up.add(closeAll);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == 3) {
                    if (getUI().tabForCoordinate(TabbedPane.this, e.getX(), e.getY()) != -1) {
                        point.setLocation(e.getPoint());
                        pop_up.show(getComponentAt(getMousePosition()), e.getX(), e.getY());
                    }
                }
            }
        });
    }

    public enum TYPECHANGE {
        DEFAULT,

        INSERTUSER,
        INSERTWORKER,
        INSERTTYPEWORKER,
        INSERTTYPEPLACE,
        INSERTPRODUCT,
        INSERTPLACE,
        INSERTPLACESALE,
        INSERTTRANSFER,
        INSERTADVANCE,
        INSERTMOTION,

        UPDATETUSER,
        UPDATEWORKER,
        UPDATETYPEWORKER,
        UPDATETYPEPLACE,
        UPDATEPRODUCT,
        UPDATEPLACE,
        UPDATEPLACESALE,
        UPDATETRANSFER,
        UPDATEADVANCE,
        UPDATEMOTION,
        UPDATEPRODUCTDAYSALE,
    }
}
