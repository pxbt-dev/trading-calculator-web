package com.pxbtdev.tradingcalculatorweb.view;

import javax.swing.*;
import java.awt.*;

public class ThemeManager {
    private boolean darkMode = false;

    // Light theme colors
    private final Color lightBg = Color.WHITE;
    private final Color lightFg = Color.BLACK;
    private final Color lightBorder = new Color(200, 200, 200);

    // Dark theme colors - GREEN TEXT ON BLACK! 🍇
    private final Color darkBg = Color.BLACK;
    private final Color darkFg = new Color(0, 255, 0); // Bright green
    private final Color darkInputBg = new Color(30, 30, 30); // Dark gray for inputs
    private final Color darkBorder = new Color(0, 100, 0); // Dark green border
    private final Color darkButtonBg = new Color(50, 50, 50); // Dark gray for buttons

    public boolean isDarkMode() {
        return darkMode;
    }

    public void toggleTheme() {
        darkMode = !darkMode;
    }

    public String getThemeButtonText() {
        return darkMode ? "☀️" : "🌙";
    }

    public void applyTheme(Component component) {
        if (component instanceof Container) {
            applyThemeToComponent(component);
        }
    }

    private void applyThemeToComponent(Component component) {
        Color bg = darkMode ? darkBg : lightBg;
        Color fg = darkMode ? darkFg : lightFg;
        Color inputBg = darkMode ? darkInputBg : lightBg;
        Color buttonBg = darkMode ? darkButtonBg : new Color(240, 240, 240);

        // Apply to current component
        component.setBackground(bg);
        if (component instanceof JComponent) {
            ((JComponent) component).setForeground(fg);
        }

        // Special handling for different component types
        if (component instanceof JTextField) {
            component.setBackground(inputBg);
            component.setForeground(fg);
            ((JTextField) component).setCaretColor(fg); // Cursor color matches text
        } else if (component instanceof JTextArea) {
            component.setBackground(inputBg);
            component.setForeground(fg);
            ((JTextArea) component).setCaretColor(fg);
        } else if (component instanceof JButton || component instanceof JToggleButton) {
            component.setBackground(buttonBg);
            component.setForeground(fg);
        } else if (component instanceof JLabel) {
            component.setForeground(fg);
        } else if (component instanceof JScrollPane) {
            component.setBackground(bg);
        } else if (component instanceof JPanel) {
            // Handle titled borders - make titles green in dark mode!
            if (((JPanel) component).getBorder() instanceof javax.swing.border.TitledBorder) {
                javax.swing.border.TitledBorder border = (javax.swing.border.TitledBorder) ((JPanel) component).getBorder();
                border.setTitleColor(fg);
            }
        }

        // Recursively apply to children
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                applyThemeToComponent(child);
            }
        }
    }

    // Utility method to update a specific component
    public void updateComponentTheme(Component component) {
        applyThemeToComponent(component);
    }
}