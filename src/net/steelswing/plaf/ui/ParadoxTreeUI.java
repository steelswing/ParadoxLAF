/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.plaf.ui;

import com.bulenkov.darcula.DarculaUIUtil;
import com.bulenkov.iconloader.util.UIUtil;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JViewport;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import static net.steelswing.plaf.ParadoxLaf.BASE_BG_COLOR;

/**
 *
 * @author MrJavaCoder
 */
public class ParadoxTreeUI extends BasicTreeUI {

    public static ComponentUI createUI(JComponent c) {
        return new ParadoxTreeUI();
    }


    @Override
    public void paint(Graphics g, JComponent c) {
        try {
            super.paint(g, c);
            c.setBackground(BASE_BG_COLOR);
            setHashColor(BASE_BG_COLOR.brighter().brighter());
        } catch (Exception e) {
        }
    }

    @Override
    protected void paintRow(Graphics g, Rectangle clipBounds, Insets insets, Rectangle bounds, TreePath path, int row, boolean isExpanded, boolean hasBeenExpanded, boolean isLeaf) {

        int containerWidth = (this.tree.getParent() instanceof JViewport) ? this.tree.getParent().getWidth() : this.tree.getWidth();
        int xOffset = (this.tree.getParent() instanceof JViewport) ? (((JViewport) this.tree.getParent()).getViewPosition()).x : 0;

        if (path != null) {
            boolean selected = this.tree.isPathSelected(path);
            Graphics2D rowGraphics = (Graphics2D) g.create();
            rowGraphics.setClip(clipBounds);

            Object sourceList = this.tree.getClientProperty("mac.ui.source.list");
            Color background = this.tree.getBackground();

            if (row % 2 == 0 && Boolean.TRUE.equals(this.tree.getClientProperty("mac.ui.striped"))) {
                background = UIUtil.getDecoratedRowColor();
            }

            if (sourceList != null && ((Boolean) sourceList).booleanValue()) {
                if (selected) {
                } else {
                    rowGraphics.setColor(background);
                    rowGraphics.fillRect(xOffset, bounds.y, containerWidth, bounds.height);
                }

            } else if (selected) {
                Color bg = BASE_BG_COLOR.brighter().brighter();//UIUtil.getTreeSelectionBackground((this.tree.hasFocus() || Boolean.TRUE.equals(this.tree.getClientProperty("TreeTableTree"))));

                bg = new Color(bg.getRed(), bg.getGreen(), bg.getBlue(), 160);

                rowGraphics.setColor(bg);
                rowGraphics.fillRect(xOffset, bounds.y, containerWidth, bounds.height);
                rowGraphics.setColor(bg.brighter());
                rowGraphics.drawRect(xOffset, bounds.y, containerWidth - 1, bounds.height - 1);
            }

            if (shouldPaintExpandControl(path, row, isExpanded, hasBeenExpanded, isLeaf)) {
                paintExpandControl(rowGraphics, bounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);
            }
            super.paintRow(rowGraphics, clipBounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);

            rowGraphics.dispose();
        } else {
            super.paintRow(g, clipBounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);
        }
    }


    @Override
    protected void paintExpandControl(Graphics g, Rectangle clipBounds, Insets insets, Rectangle bounds, TreePath path, int row, boolean isExpanded, boolean hasBeenExpanded, boolean isLeaf) {
//        clipBounds.setLocation(20, clipBounds.getLocation().y);

//        bounds.setLocation(bounds.x + 2, bounds.y);
        boolean isPathSelected = this.tree.getSelectionModel().isPathSelected(path);
        if (!isLeaf(row)) {
            setExpandedIcon(DarculaUIUtil.getTreeNodeIcon(true, isPathSelected, this.tree.hasFocus()));
            setCollapsedIcon(DarculaUIUtil.getTreeNodeIcon(false, isPathSelected, this.tree.hasFocus()));
        }

        super.paintExpandControl(g, clipBounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);
    }

    @Override
    protected TreeCellRenderer createDefaultCellRenderer() {
        return new ParadoxDefaultCellRenderer();
    }

}
