/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.plaf.ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuUI;
import static net.steelswing.plaf.ParadoxLaf.BASE_BG_COLOR;

/**
 *
 * @author MrJavaCoder
 */
public class ParadoxMenuUI extends BasicMenuUI {

    public static ComponentUI createUI(JComponent c) {
        return new ParadoxMenuUI();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        c.setBackground(BASE_BG_COLOR);
        super.paint(g, c);
    }

    @Override
    protected void paintBackground(Graphics g, JMenuItem i, Color bgColor) {
        ButtonModel model = menuItem.getModel();
        Color oldColor = g.getColor();
        int menuWidth = menuItem.getWidth();
        int menuHeight = menuItem.getHeight();

        if (menuItem.isOpaque()) {
            if (model.isArmed() || (menuItem instanceof JMenu && model.isSelected())) {
//                g.setColor(bgColor);
//                g.fillRect(0, 0, menuWidth, menuHeight);
                drawSelection(g, i);
            } else {
                g.setColor(menuItem.getBackground());
                g.fillRect(0, 0, menuWidth, menuHeight);
            }
            g.setColor(oldColor);
        } else if (model.isArmed() || (menuItem instanceof JMenu && model.isSelected())) {
             drawSelection(g, i);
//            g.setColor(bgColor);
//            g.fillRect(0, 0, menuWidth, menuHeight);
            g.setColor(oldColor);
        }
    }

    private void drawSelection(Graphics g, JMenuItem i) {
        Color bg = BASE_BG_COLOR.brighter().brighter();

        bg = new Color(bg.getRed(), bg.getGreen(), bg.getBlue(), 160);

        g.setColor(bg);
        g.fillRect(0, 0, i.getWidth(), i.getHeight());
        g.setColor(bg.brighter());
        g.drawRect(0, 0, i.getWidth() - 1, i.getHeight() - 1);
    }




}
