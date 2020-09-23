/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.plaf.ui;

import java.awt.Graphics;
import javax.swing.tree.DefaultTreeCellRenderer;
import static net.steelswing.plaf.ParadoxLaf.BASE_BG_COLOR;

/**
 *
 * @author MrJavaCoder
 */
public class ParadoxDefaultCellRenderer extends DefaultTreeCellRenderer {
    
    private static final long serialVersionUID = 1L;

    public ParadoxDefaultCellRenderer() {
        borderSelectionColor = null;
        backgroundSelectionColor = null;
        backgroundNonSelectionColor = null;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(BASE_BG_COLOR);
    }
    
}
