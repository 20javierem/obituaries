package com.moreno.obituaries.ui.custom;


import com.formdev.flatlaf.extras.components.FlatToggleButton;

import javax.swing.*;

public class TabPane extends JPanel {
    private String title;
    private Icon icon;
    private FlatToggleButton option;
    private UiUpdate uiUpdate = (typechange) -> {};

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public FlatToggleButton getOption() {
        return option;
    }

    public void setOption(FlatToggleButton option) {
        this.option = option;
    }

    public UiUpdate getUiUpdate() {
        return uiUpdate;
    }

    public void setUiUpdate(UiUpdate uiUpdate) {
        this.uiUpdate = uiUpdate;
    }
}
