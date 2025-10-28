package com.pxbtdev.tradingcalculatorweb.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainView extends JFrame {
    private JTextField accountSizeField;
    private JTextField riskDollarsField;
    private JTextField entryPriceField;
    private JTextField stopLossField;
    private JTextField targetPriceField;
    private JTextArea resultArea;
    private JToggleButton themeToggle;
    private JToggleButton alwaysOnTopToggle;
    private JButton calculateButton;
    private JButton clearButton;

    private ThemeManager themeManager;

    public MainView() {
        this.themeManager = new ThemeManager();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Trading Position Size Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use BoxLayout for proper stacking
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Increased padding

        // Add components in order with spacing
        mainPanel.add(createHeaderPanel());
        mainPanel.add(Box.createVerticalStrut(20)); // Increased spacing
        mainPanel.add(createInputPanel());
        mainPanel.add(Box.createVerticalStrut(20)); // Increased spacing
        mainPanel.add(createResultsPanel());
        mainPanel.add(Box.createVerticalStrut(20)); // Increased spacing
        mainPanel.add(createButtonPanel());

        add(mainPanel);

        // Apply initial theme
        themeManager.applyTheme(this);

        // Set larger initial window size
        setPreferredSize(new Dimension(700, 900)); // Width, Height - much larger!
        pack();

        // Center on screen but make it larger
        setLocationRelativeTo(null);
        setResizable(true); // Allow user to resize further
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("💰 Trading Position Size Calculator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Panel for the toggle buttons
        JPanel togglePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        togglePanel.setOpaque(false);

        // Always on top toggle
        alwaysOnTopToggle = new JToggleButton("📌");
        alwaysOnTopToggle.setPreferredSize(new Dimension(35, 25));
        alwaysOnTopToggle.setToolTipText("Always on Top");
        alwaysOnTopToggle.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
        alwaysOnTopToggle.setMargin(new Insets(0, 0, 0, 0));

        // Theme toggle
        boolean isDarkMode = themeManager.isDarkMode();
        themeToggle = new JToggleButton(isDarkMode ? "☀️" : "🌙");
        themeToggle.setPreferredSize(new Dimension(35, 25));
        themeToggle.setToolTipText(isDarkMode ? "Switch to Light Mode" : "Switch to Dark Mode");
        themeToggle.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
        themeToggle.setMargin(new Insets(0, 0, 0, 0));

        togglePanel.add(alwaysOnTopToggle);
        togglePanel.add(themeToggle);

        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(togglePanel, BorderLayout.EAST);

        // Add always on top functionality directly
        alwaysOnTopToggle.addActionListener(e -> {
            boolean selected = alwaysOnTopToggle.isSelected();
            MainView.this.setAlwaysOnTop(selected);
            alwaysOnTopToggle.setToolTipText(selected ? "Disable Always on Top" : "Always on Top");
        });

        return headerPanel;
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 8)); // Increased vertical spacing
        panel.setBorder(BorderFactory.createTitledBorder("Trade Parameters"));

        // Create a larger font for better readability
        Font largerFont = new Font("Arial", Font.PLAIN, 14); // Increased from default 12

        accountSizeField = new JTextField("10000");
        riskDollarsField = new JTextField("100");
        entryPriceField = new JTextField();
        stopLossField = new JTextField();
        targetPriceField = new JTextField();

        // Apply larger font to all input fields
        accountSizeField.setFont(largerFont);
        riskDollarsField.setFont(largerFont);
        entryPriceField.setFont(largerFont);
        stopLossField.setFont(largerFont);
        targetPriceField.setFont(largerFont);

        // Create labels with larger font too
        JLabel accountLabel = new JLabel("Account Size ($):");
        JLabel riskLabel = new JLabel("Amount to Risk ($):");
        JLabel entryLabel = new JLabel("Entry Price:");
        JLabel stopLossLabel = new JLabel("Stop Loss Price:");
        JLabel targetLabel = new JLabel("Target Price (optional):");

        accountLabel.setFont(largerFont);
        riskLabel.setFont(largerFont);
        entryLabel.setFont(largerFont);
        stopLossLabel.setFont(largerFont);
        targetLabel.setFont(largerFont);

        panel.add(accountLabel);
        panel.add(accountSizeField);
        panel.add(riskLabel);
        panel.add(riskDollarsField);
        panel.add(entryLabel);
        panel.add(entryPriceField);
        panel.add(stopLossLabel);
        panel.add(stopLossField);
        panel.add(targetLabel);
        panel.add(targetPriceField);

        return panel;
    }

    private JPanel createResultsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Results"));

        resultArea = new JTextArea(15, 50); // Increased rows and columns
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Increased from 12 to 14
        resultArea.setText("Enter your trade parameters and click 'Calculate'");

        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        calculateButton = new JButton("Calculate Position Size");
        clearButton = new JButton("Clear");

        // Larger font for buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        calculateButton.setFont(buttonFont);
        clearButton.setFont(buttonFont);

        // Larger button size
        calculateButton.setPreferredSize(new Dimension(200, 35));
        clearButton.setPreferredSize(new Dimension(100, 35));

        panel.add(calculateButton);
        panel.add(clearButton);

        return panel;
    }

    // Getters
    public JTextField getAccountSizeField() { return accountSizeField; }
    public JTextField getRiskDollarsField() { return riskDollarsField; }
    public JTextField getEntryPriceField() { return entryPriceField; }
    public JTextField getStopLossField() { return stopLossField; }
    public JTextField getTargetPriceField() { return targetPriceField; }
    public JTextArea getResultArea() { return resultArea; }
    public JToggleButton getThemeToggle() { return themeToggle; }
    public JToggleButton getAlwaysOnTopToggle() { return alwaysOnTopToggle; }
    public JButton getCalculateButton() { return calculateButton; }
    public JButton getClearButton() { return clearButton; }
    public ThemeManager getThemeManager() { return themeManager; }
}