/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.plaf.ui;

import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalMenuBarUI;
import static net.steelswing.plaf.ParadoxLaf.BASE_BG_COLOR;

/**
 *
 * @author MrJavaCoder
 */
public class ParadoxMenuBarUI extends MetalMenuBarUI {

    public static ComponentUI createUI(JComponent c) {
        return new ParadoxMenuBarUI();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        c.setBackground(BASE_BG_COLOR);
        g.setColor(BASE_BG_COLOR);
        g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }
}
