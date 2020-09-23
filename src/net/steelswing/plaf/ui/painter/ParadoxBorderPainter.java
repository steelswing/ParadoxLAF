/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.plaf.ui.painter;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.EtchedBorder;
import static net.steelswing.plaf.ParadoxLaf.BASE_FILL_COLOR;

/**
 *
 * @author MrJavaCoder
 */
public class ParadoxBorderPainter extends EtchedBorder {

    private static final long serialVersionUID = 1L;

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(BASE_FILL_COLOR);
        g.drawRect(0, 0, width - 1, height - 1);  
    }
    
    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        //         y  l  d  r
        insets.set(2, 2, 4, 2);
//        c.setBounds(c.getX() + 1, c.getY() + 1, c.getWidth(), c.getHeight());
        return insets;
    }


}
