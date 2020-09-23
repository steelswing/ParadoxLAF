/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.plaf.ui.painter;

import com.bulenkov.darcula.ui.DarculaButtonUI;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.border.Border;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.UIResource;

/**
 *
 * @author MrJavaCoder
 */
public class ParadoxButtonPainter implements Border, UIResource {

    private static final int myOffset = 4;
//

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        Insets ins = getBorderInsets(c);
        int yOff = (ins.top + ins.bottom) / 4;
        boolean square = DarculaButtonUI.isSquare(c);
        int offset = square ? 1 : getOffset();
        if (c.hasFocus()) {
//            DarculaUIUtil.paintFocusRing(g2d, offset, yOff, width - 2 * offset, height - 2 * yOff);
        } else {
//            GraphicsConfig config = new GraphicsConfig(g);
//            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
//            g2d.setPaint(new GradientPaint((width / 2), (y + yOff + 1), Gray._80.withAlpha(90), (width / 2), (height - 2 * yOff), Gray._90.withAlpha(90)));
//
//            ((Graphics2D) g).setPaint(Gray._100.withAlpha(180));
//            g.drawRoundRect(x + offset, y + yOff, width - 2 * offset, height - 2 * yOff, square ? 3 : 5, square ? 3 : 5);
//
//            config.restore();
        }
    }


    @Override
    public Insets getBorderInsets(Component c) {
        return new InsetsUIResource(8, 16, 8, 16);
    }


    protected int getOffset() {
        return 4;
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
