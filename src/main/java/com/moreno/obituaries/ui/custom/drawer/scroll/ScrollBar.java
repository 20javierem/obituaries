package com.moreno.obituaries.ui.custom.drawer.scroll;

import javax.swing.*;
import java.awt.*;

public class ScrollBar extends JScrollBar {

    public ScrollBar(int al) {
        super(al);
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(5, 5));
        setOpaque(false);
        setUnitIncrement(20);
    }
}
