/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.plaf.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
import net.steelswing.plaf.ParadoxLaf;

/**
 *
 * @author MrJavaCoder
 */
public class ParadoxScrollBarUI extends BasicScrollBarUI {

    public static ComponentUI createUI(JComponent c) {
        return new ParadoxScrollBarUI();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        c.setBackground(ParadoxLaf.BASE_BG_COLOR);
        super.paint(g, c);
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        c.setBackground(ParadoxLaf.BASE_BG_COLOR);
        g.setColor(ParadoxLaf.BASE_BG_COLOR);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);

        if (trackHighlight == DECREASE_HIGHLIGHT) {
            paintDecreaseHighlight(g);
        } else if (trackHighlight == INCREASE_HIGHLIGHT) {
            paintIncreaseHighlight(g);
        }
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
     
        if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
            return;
        }

        int w = thumbBounds.width;
        int h = thumbBounds.height;

        Color bg = ParadoxLaf.BASE_BG_COLOR.brighter().brighter();

        bg = new Color(bg.getRed(), bg.getGreen(), bg.getBlue(), 160);

        g.translate(thumbBounds.x, thumbBounds.y);
        g.setColor(bg);
        g.fillRect(1, 1, w - 2, h - 1);
        g.setColor(bg.brighter());
        g.drawRect(1, 1, w - 2, h - 2);

        g.translate(-thumbBounds.x, -thumbBounds.y);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new ParadoxArrowButton(orientation,
                UIManager.getColor("ScrollBar.thumb"),
                UIManager.getColor("ScrollBar.thumbShadow"),
                UIManager.getColor("ScrollBar.thumbDarkShadow"),
                UIManager.getColor("ScrollBar.thumbHighlight"));
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new ParadoxArrowButton(orientation,
                UIManager.getColor("ScrollBar.thumb"),
                UIManager.getColor("ScrollBar.thumbShadow"),
                UIManager.getColor("ScrollBar.thumbDarkShadow"),
                UIManager.getColor("ScrollBar.thumbHighlight"));
    }

    public static class ParadoxArrowButton extends BasicArrowButton {

        private static final long serialVersionUID = 1L;

        public ParadoxArrowButton(int direction, Color background, Color shadow, Color darkShadow, Color highlight) {
            super(direction, background, shadow, darkShadow, highlight);
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            setBackground(ParadoxLaf.BASE_BG_COLOR);
            boolean isPressed, isEnabled;
            int w, h, size;

            w = getSize().width;
            h = getSize().height;
            isPressed = getModel().isPressed();
            isEnabled = isEnabled();

//            g.setColor(ParadoxLaf.BASE_FILL_COLOR);
//            g.fillRect(1, 1, w - 2, h - 2);
            Color bg = ParadoxLaf.BASE_BG_COLOR.brighter().brighter();

            bg = new Color(bg.getRed(), bg.getGreen(), bg.getBlue(), 160);

            g.setColor(bg);
            g.fillRect(1, 1, w - 2, h - 1);
            g.setColor(bg.brighter());
            g.drawRect(1, 1, w - 2, h - 2);

            if (isPressed) {

            }

            // Draw the arrow
            size = Math.min((h - 4) / 3, (w - 4) / 3);
            size = Math.max(size, 2);

            g.setColor(ParadoxLaf.BASE_FILL_COLOR.brighter().brighter().brighter().brighter());
            paintTriangle(g, (w - size) / 2 + 1, (h - size) / 2 + 1, size, direction, true);

            // Reset the Graphics back to it's original settings
            if (isPressed) {

            }
        }

        @Override
        public void paintTriangle(Graphics g, int x, int y, int size, int direction, boolean isEnabled) {
            int mid, i, j;

            j = 0;
            size = Math.max(size, 2);
            mid = (size / 2) - 1;

            Color a = ParadoxLaf.BASE_FILL_COLOR.brighter().brighter();

            g.translate(x, y);
            if (isEnabled) {
                g.setColor(a);
            } else {
                g.setColor(a);
            }

            switch (direction) {
                case NORTH:
                    for (i = 0; i < size; i++) {
                        g.drawLine(mid - i, i, mid + i, i);
                    }
                    if (!isEnabled) {
                        g.setColor(a);
                        g.drawLine(mid - i + 2, i, mid + i, i);
                    }
                    break;
                case SOUTH:
                    if (!isEnabled) {
                        g.translate(1, 1);
                        g.setColor(a);
                        for (i = size - 1; i >= 0; i--) {
                            g.drawLine(mid - i, j, mid + i, j);
                            j++;
                        }
                        g.translate(-1, -1);
                        g.setColor(a);
                    }

                    j = 0;
                    for (i = size - 1; i >= 0; i--) {
                        g.drawLine(mid - i, j, mid + i, j);
                        j++;
                    }
                    break;
                case WEST:
                    for (i = 0; i < size; i++) {
                        g.drawLine(i, mid - i, i, mid + i);
                    }
                    if (!isEnabled) {
                        g.setColor(a);
                        g.drawLine(i, mid - i + 2, i, mid + i);
                    }
                    break;
                case EAST:
                    if (!isEnabled) {
                        g.translate(1, 1);
                        g.setColor(a);
                        for (i = size - 1; i >= 0; i--) {
                            g.drawLine(j, mid - i, j, mid + i);
                            j++;
                        }
                        g.translate(-1, -1);
                        g.setColor(a);
                    }

                    j = 0;
                    for (i = size - 1; i >= 0; i--) {
                        g.drawLine(j, mid - i, j, mid + i);
                        j++;
                    }
                    break;
            }
            g.translate(-x, -y);
        }

    }


}
