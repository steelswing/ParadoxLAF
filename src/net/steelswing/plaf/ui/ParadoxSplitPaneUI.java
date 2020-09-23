/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.plaf.ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import static net.steelswing.plaf.ParadoxLaf.BASE_FILL_COLOR;
import static net.steelswing.plaf.ParadoxLaf.BASE_BG_COLOR;

/**
 *
 * @author MrJavaCoder
 */
public class ParadoxSplitPaneUI extends BasicSplitPaneUI {

    public static ComponentUI createUI(JComponent c) {
        return new ParadoxSplitPaneUI();
    }

    @Override
    protected void installDefaults() {
        divider = new ParadoxSplitPaneDivider(this);

        super.installDefaults();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
//        g.setColor(Color.red);
//        g.fillRect(0, 0, c.getWidth(), c.getHeight());
        c.setBackground(BASE_BG_COLOR);
    }


    public static class ParadoxSplitPaneDivider extends BasicSplitPaneDivider {

        private static final long serialVersionUID = 1L;

        public ParadoxSplitPaneDivider(BasicSplitPaneUI ui) {
            super(ui);
        }

        private static final int borderSize = 1;
        private int wOffSet = 2, hOffSet = 2, count = 5;

        @Override
        public void paint(Graphics g) {
            setBackground(BASE_BG_COLOR);

            if (orientation == 1) {
                hOffSet = 0;
                wOffSet = 2;
            } else {
                hOffSet = 2;
                wOffSet = 0;
            }

            drawRec(g, wOffSet, hOffSet, (getWidth() - 1) - (wOffSet * 2), (getHeight() - 1) - (hOffSet * 2));

            for (int i = 0; i < count; i++) {
                if (orientation == 1) {
                    drawRec(g, getWidth() / 2 - 2, ((getHeight() / 2 - (3 * count) - 10) + (i * 10)), 3, 3);
                } else {
                    drawRec(g, ((getWidth() / 2 - (3 * count) - 10) + (i * 10)), getHeight() / 2 - 2, 3, 3);
                }
            }

        }

        private void drawRec(Graphics g, int x, int y, int w, int h) {
            g.setColor(BASE_FILL_COLOR);
            g.fillRect(x, y, w, h);

            g.setColor(BASE_FILL_COLOR.brighter());
            g.drawRect(x, y, w, h);
        }

        private void drawRec(Graphics g, Color c, int x, int y, int w, int h) {
            g.setColor(c);
            g.fillRect(x, y, w, h);

            g.setColor(c.brighter());
            g.drawRect(x, y, w, h);
        }
    }
}
