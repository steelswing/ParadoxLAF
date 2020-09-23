/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.plaf;

import com.bulenkov.darcula.DarculaLaf;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.lang.reflect.Method;
import javax.swing.JSplitPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.plaf.metal.MetalLookAndFeel;
import net.steelswing.plaf.ui.ParadoxButtonUI;
import net.steelswing.plaf.ui.ParadoxMenuBarUI;
import net.steelswing.plaf.ui.ParadoxMenuItemUI;
import net.steelswing.plaf.ui.ParadoxMenuUI;
import net.steelswing.plaf.ui.ParadoxPanelUI;
import net.steelswing.plaf.ui.ParadoxScrollBarUI;
import net.steelswing.plaf.ui.ParadoxSeparatorUI;
import net.steelswing.plaf.ui.ParadoxSplitPaneUI;
import net.steelswing.plaf.ui.ParadoxTreeUI;
import net.steelswing.plaf.ui.painter.ParadoxBorderPainter;
import net.steelswing.plaf.ui.painter.ParadoxButtonPainter;

/**
 *
 * @author MrJavaCoder
 */
public class ParadoxLaf extends BasicLookAndFeel {

    private static final long serialVersionUID = 1L;

    private BasicLookAndFeel base;

    public static Color //
            BASE_FILL_COLOR = new Color(64, 69, 85, 255),
            BASE_BG_COLOR = BASE_FILL_COLOR.darker().darker();


    public static void setColor(Color color) {
        BASE_FILL_COLOR = color;
        BASE_BG_COLOR = BASE_FILL_COLOR.darker().darker();
    }

    public ParadoxLaf() {
        try {
            String name = UIManager.getSystemLookAndFeelClassName();
            this.base = (BasicLookAndFeel) Class.forName(name).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public UIDefaults getDefaults() {
        try {
            Method superMethod = BasicLookAndFeel.class.getDeclaredMethod("getDefaults", new Class[0]);
            superMethod.setAccessible(true);

            UIDefaults metalDefaults = (UIDefaults) superMethod.invoke(new MetalLookAndFeel(), new Object[0]);
            UIDefaults defaults = (UIDefaults) superMethod.invoke(new DarculaLaf(), new Object[0]);

            defaults.put("ScrollPane.border", new ParadoxBorderPainter() {

                @Override
                public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                    c.setBackground(BASE_BG_COLOR);
                    super.paintBorder(c, g, x, y, width, height);
                }

            });

            defaults.put("Panel.border", new ParadoxBorderPainter() {
                @Override
                public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                    super.paintBorder(c, g, x, y, width, height);
                }

                @Override
                public Insets getBorderInsets(Component c, Insets insets) {
                    insets.set(4, 4, 4, 4);
                    return insets;
                }
            });

            defaults.put("SplitPane.border", new ParadoxBorderPainter() {
                @Override
                public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

                }

                @Override
                public Insets getBorderInsets(Component c, Insets insets) {
                    if (c instanceof JSplitPane) {
                        insets.set(0, 0, 0, 0);
                    } else {
                        insets.set(2, 2, 4, 2);
                    }
                    return insets;
                }
            });

            defaults.put("TreeUI.editorBorder", new ParadoxBorderPainter() {
                @Override
                public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                    g.setColor(Color.RED);
                    g.fillRect(x, y, width, height);
                }

                @Override
                public Insets getBorderInsets(Component c, Insets insets) {
                    insets.set(0, 0, 0, 0);
                    return insets;
                }
            });

            defaults.put("Button.border", new ParadoxButtonPainter());
            defaults.put("ButtonUI", ParadoxButtonUI.class.getCanonicalName());
            defaults.put("SplitPaneUI", ParadoxSplitPaneUI.class.getCanonicalName());
            defaults.put("PanelUI", ParadoxPanelUI.class.getCanonicalName());
            defaults.put("TreeUI", ParadoxTreeUI.class.getCanonicalName());
            defaults.put("MenuBarUI", ParadoxMenuBarUI.class.getCanonicalName());
            defaults.put("MenuItemUI", ParadoxMenuItemUI.class.getCanonicalName());
            defaults.put("MenuUI", ParadoxMenuUI.class.getCanonicalName());
            defaults.put("ScrollBarUI", ParadoxScrollBarUI.class.getCanonicalName());
            defaults.put("SeparatorUI", ParadoxSeparatorUI.class.getCanonicalName());
            
            
            return defaults;
        } catch (Exception e) {
            e.printStackTrace();
            return super.getDefaults();
        }
    }

    @Override
    public String getName() {
        return "Paradox";
    }

    @Override
    public String getID() {
        return "Paradox";
    }

    @Override
    public String getDescription() {
        return "Paradox";
    }

    @Override
    public boolean isNativeLookAndFeel() {
        return false;
    }

    @Override
    public boolean isSupportedLookAndFeel() {
        return true;
    }

}
