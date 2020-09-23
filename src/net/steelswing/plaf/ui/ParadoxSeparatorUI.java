/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.plaf.ui;

import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSeparatorUI;
import net.steelswing.plaf.ParadoxLaf;

/**
 *
 * @author MrJavaCoder
 */
public class ParadoxSeparatorUI extends BasicSeparatorUI {

    public static ComponentUI createUI(JComponent c) {
        return new ParadoxSeparatorUI();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        c.setBackground(ParadoxLaf.BASE_FILL_COLOR);
        c.setForeground(c.getBackground());
        super.paint(g, c); 
    }
}
