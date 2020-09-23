/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.plaf.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuItemUI;
import sun.swing.MenuItemLayoutHelper;
import sun.swing.SwingUtilities2;

/**
 *
 * @author MrJavaCoder
 */
public class ParadoxMenuItemUI extends BasicMenuItemUI {

    public static ComponentUI createUI(JComponent c) {
        return new ParadoxMenuItemUI();
    }


    public void processMouseEvent(JMenuItem item, MouseEvent e, MenuElement[] path, MenuSelectionManager manager) {
        Point p = e.getPoint();
        if (p.x >= 0 && p.x < item.getWidth() && p.y >= 0 && p.y < item.getHeight()) {
            if (e.getID() == 502) {
                manager.clearSelectedPath();
                item.doClick(0);
                item.setArmed(false);
            } else {
                manager.setSelectedPath(path);
            }
        } else if (item.getModel().isArmed()) {
            MenuElement[] newPath = new MenuElement[path.length - 1];

            for (int i = 0, c = path.length - 1; i < c; i++) {
                newPath[i] = path[i];
            }
            manager.setSelectedPath(newPath);
        }
    }


    @Override
    protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
        Font holdf = g.getFont();
        Color holdc = g.getColor();

        JMenuItem mi = (JMenuItem) c;
        g.setFont(mi.getFont());

        Rectangle viewRect = new Rectangle(0, 0, mi.getWidth(), mi.getHeight());
        applyInsets(viewRect, mi.getInsets());

        MenuItemLayoutHelper lh = new MenuItemLayoutHelper(mi, checkIcon, arrowIcon, viewRect, defaultTextIconGap, this.acceleratorDelimiter, mi.getComponentOrientation().isLeftToRight(), mi.getFont(), this.acceleratorFont, MenuItemLayoutHelper.useCheckAndArrow(this.menuItem), getPropertyPrefix());
        MenuItemLayoutHelper.LayoutResult lr = lh.layoutMenuItem();

        paintBackground(g, mi, background);
        paintCheckIcon(g, lh, lr, holdc, foreground);
        paintIcon(g, lh, lr, holdc);
        g.setColor(foreground);
        paintText(g, lh, lr);
        paintAccText(g, lh, lr);
        paintArrowIcon(g, lh, lr, foreground);

        g.setColor(holdc);
        g.setFont(holdf);
    }


    protected void paintIcon(Graphics g, MenuItemLayoutHelper lh, MenuItemLayoutHelper.LayoutResult lr, Color holdc) {
        if (lh.getIcon() != null) {
            Icon icon;
            ButtonModel model = lh.getMenuItem().getModel();
            if (!model.isEnabled()) {
                icon = lh.getMenuItem().getDisabledIcon();
            } else if (model.isPressed() && model.isArmed()) {
                icon = lh.getMenuItem().getPressedIcon();
                if (icon == null) {
                    icon = lh.getMenuItem().getIcon();
                }
            } else {
                icon = lh.getMenuItem().getIcon();
            }

            if (icon != null) {
                icon.paintIcon(lh.getMenuItem(), g, (lr.getIconRect()).x,
                        (lr.getIconRect()).y);
                g.setColor(holdc);
            }
        }
    }

    protected void paintCheckIcon(Graphics g, MenuItemLayoutHelper lh, MenuItemLayoutHelper.LayoutResult lr, Color holdc, Color foreground) {
        if (lh.getCheckIcon() != null) {
            ButtonModel model = lh.getMenuItem().getModel();
            if (model.isArmed() || (lh.getMenuItem() instanceof javax.swing.JMenu && model
                    .isSelected())) {
                g.setColor(foreground);
            } else {
                g.setColor(holdc);
            }
            if (lh.useCheckAndArrow()) {
                lh.getCheckIcon().paintIcon(lh.getMenuItem(), g,
                        (lr.getCheckRect()).x, (lr.getCheckRect()).y);
            }
            g.setColor(holdc);
        }
    }

    protected void paintAccText(Graphics g, MenuItemLayoutHelper lh, MenuItemLayoutHelper.LayoutResult lr) {
        if (!lh.getAccText().equals("")) {
            ButtonModel model = lh.getMenuItem().getModel();
            g.setFont(lh.getAccFontMetrics().getFont());
            if (!model.isEnabled()) {

                if (this.disabledForeground != null) {
                    g.setColor(this.disabledForeground);
                    SwingUtilities2.drawString(lh.getMenuItem(), g, lh
                            .getAccText(), (lr.getAccRect()).x,
                            (lr.getAccRect()).y + lh.getAccFontMetrics().getAscent());
                } else {
                    g.setColor(lh.getMenuItem().getBackground().brighter());
                    SwingUtilities2.drawString(lh.getMenuItem(), g, lh
                            .getAccText(), (lr.getAccRect()).x,
                            (lr.getAccRect()).y + lh.getAccFontMetrics().getAscent());
                    g.setColor(lh.getMenuItem().getBackground().darker());
                    SwingUtilities2.drawString(lh.getMenuItem(), g, lh
                            .getAccText(), (lr.getAccRect()).x - 1,
                            (lr.getAccRect()).y + lh.getFontMetrics().getAscent() - 1);
                }
            } else {

                if (model.isArmed() || (lh
                        .getMenuItem() instanceof javax.swing.JMenu && model
                                .isSelected())) {
                    g.setColor(this.acceleratorSelectionForeground);
                } else {
                    g.setColor(this.acceleratorForeground);
                }
                SwingUtilities2.drawString(lh.getMenuItem(), g, lh.getAccText(),
                        (lr.getAccRect()).x, (lr.getAccRect()).y + lh
                        .getAccFontMetrics().getAscent());
            }
        }
    }

    protected void paintText(Graphics g, MenuItemLayoutHelper lh, MenuItemLayoutHelper.LayoutResult lr) {
        if (!lh.getText().equals("")) {
            if (lh.getHtmlView() != null) {

                lh.getHtmlView().paint(g, lr.getTextRect());
            } else {

                paintText(g, lh.getMenuItem(), lr.getTextRect(), lh.getText());
            }
        }
    }

    protected void paintArrowIcon(Graphics g, MenuItemLayoutHelper lh, MenuItemLayoutHelper.LayoutResult lr, Color foreground) {
        if (lh.getArrowIcon() != null) {
            ButtonModel model = lh.getMenuItem().getModel();
            if (model.isArmed() || (lh.getMenuItem() instanceof javax.swing.JMenu && model
                    .isSelected())) {
                g.setColor(foreground);
            }
            if (lh.useCheckAndArrow()) {
                lh.getArrowIcon().paintIcon(lh.getMenuItem(), g,
                        (lr.getArrowRect()).x, (lr.getArrowRect()).y);
            }
        }
    }

    protected void applyInsets(Rectangle rect, Insets insets) {
        if (insets != null) {
            rect.x += insets.left;
            rect.y += insets.top;
            rect.width -= insets.right + rect.x;
            rect.height -= insets.bottom + rect.y;
        }
    }
}
