/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.plaf.ui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;
import sun.swing.SwingUtilities2;
import static net.steelswing.plaf.ParadoxLaf.BASE_FILL_COLOR;
import static net.steelswing.plaf.ParadoxLaf.BASE_BG_COLOR;

/**
 *
 * @author MrJavaCoder
 */
public class ParadoxButtonUI extends BasicButtonUI {
    
    public static ComponentUI createUI(JComponent c) {
        return new ParadoxButtonUI();
    }

    private static final int offset = 2, borderSize = 2;

    @Override
    public void paint(Graphics g, JComponent c) {
        c.setBackground(BASE_BG_COLOR);
        
        Color base = BASE_FILL_COLOR;
        if (!c.isEnabled()) {
            base = new Color(BASE_FILL_COLOR.getRed(), BASE_FILL_COLOR.getGreen(), BASE_FILL_COLOR.getBlue()).darker();
        }

        g.setColor(base);
        if (c.hasFocus()) {
            g.setColor(ParadoxButtonUI.brighter(base, 0.9f));
        }

        g.fillRect(offset, offset, c.getWidth() - (offset * 2), c.getHeight() - (offset * 2));

        g.setColor(base.brighter());
        if (c.hasFocus()) {
            g.setColor(base.brighter().brighter());
        }

        for (int i = 0; i < borderSize; i++) {
            g.drawRect(offset + i, offset + i, c.getWidth() - ((offset + i) * 2), c.getHeight() - ((offset + i) * 2));
        }
        super.paint(g, c);
    }

    @Override
    protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();
        FontMetrics fm = SwingUtilities2.getFontMetrics(c, g);
        int mnemonicIndex = b.getDisplayedMnemonicIndex();

        if (model.isEnabled()) {
            g.setColor(b.getForeground());
        } else {
            g.setColor(b.getForeground().darker());
        }
        SwingUtilities2.drawStringUnderlineCharAt(c, g, text, mnemonicIndex, textRect.x + getTextShiftOffset(), textRect.y + fm.getAscent() + getTextShiftOffset());
    }

    public static Color brighter(Color base, float factor) {
        int r = base.getRed();
        int g = base.getGreen();
        int b = base.getBlue();
        int alpha = base.getAlpha();

        /* From 2D group:
         * 1. black.brighter() should return grey
         * 2. applying brighter to blue will always return blue, brighter
         * 3. non pure color (non zero rgb) will eventually return white
         */
        int i = (int) (1.0 / (1.0 - factor));
        if (r == 0 && g == 0 && b == 0) {
            return new Color(i, i, i, alpha);
        }
        if (r > 0 && r < i) {
            r = i;
        }
        if (g > 0 && g < i) {
            g = i;
        }
        if (b > 0 && b < i) {
            b = i;
        }

        return new Color(Math.min((int) (r / factor), 255),
                Math.min((int) (g / factor), 255),
                Math.min((int) (b / factor), 255),
                alpha);
    }
}
