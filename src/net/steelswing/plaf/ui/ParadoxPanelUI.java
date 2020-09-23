/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.plaf.ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPanelUI;
import static net.steelswing.plaf.ParadoxLaf.BASE_FILL_COLOR;
import static net.steelswing.plaf.ParadoxLaf.BASE_BG_COLOR;

/**
 *
 * @author MrJavaCoder
 */
public class ParadoxPanelUI extends BasicPanelUI {

    public static ComponentUI createUI(JComponent c) {
        return new ParadoxPanelUI();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        c.setBackground(BASE_BG_COLOR);

        Color base = BASE_FILL_COLOR.darker().darker();
        g.setColor(base);
        g.fillRect(0, 0, c.getWidth(), c.getHeight());

//        g.setColor(BASE_FILL_COLOR);
//        g.fillRect(0, 0, c.getWidth(), c.getHeight());
//        if (c.hasFocus()) {
//            g.setColor(ParadoxButtonUI.brighter(BASE_FILL_COLOR, 0.9f));
//        }
//
//        g.fillRect(offset, offset, c.getWidth() - (offset * 2), c.getHeight() - (offset * 2));
//
//        g.setColor(BASE_FILL_COLOR.brighter());
//        if (c.hasFocus()) {
//            g.setColor(BASE_FILL_COLOR.brighter().brighter());
//        }
//
//        for (int i = 0; i < borderSize; i++) {
//            g.drawRect(offset + i, offset + i, c.getWidth() - ((offset + i) * 2), c.getHeight() - ((offset + i) * 2));
//        }
//        super.paint(g, c);
    }
}
