package com.gym.management;

import com.gym.management.ui.LoginScreen;
import com.gym.management.ui.SplashScreen;
import com.gym.management.util.DatabaseConnection;

import javax.swing.*;
import java.awt.*;

public class GymApp {
    public static final String APP_NAME = "Gym Management System";
    public static final String APP_VERSION = "1.0.0";
    
    public static void main(String[] args) {
        // Display console message
        System.out.println(APP_NAME + " v" + APP_VERSION);
        System.out.println("Starting application...");
        
        // Initialize app with splash screen
        SwingUtilities.invokeLater(() -> {
            try {
                // Show splash screen and initialize
                SplashScreen splash = new SplashScreen();
                splash.setVisible(true);
                
                // Test database connection with progress updates
                splash.updateProgress(20, "Connecting to database...");
                DatabaseConnection.getConnection();
                System.out.println("Database connection successful");
                
                // Initialize UI
                splash.updateProgress(50, "Initializing interface...");
                Thread.sleep(500); // Simulate initialization
                initializeUI();
                
                // Show login screen
                splash.updateProgress(90, "Loading login screen...");
                Thread.sleep(500); // Simulate initialization
                showLoginScreen();
                
                // Cleanup
                splash.updateProgress(100, "Ready!");
                Thread.sleep(500); // Show completion
                splash.dispose();
                
            } catch (Exception e) {
                System.err.println("Startup error: " + e.getMessage());
                JOptionPane.showMessageDialog(null,
                    "Error starting application: " + e.getMessage(),
                    "Startup Error",
                    JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });

        // Start the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                initializeUI();
                showLoginScreen();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Error starting application: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }
    
    private static void initializeUI() throws Exception {
        // Set the look and feel to the system default
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
        // Set global font settings
        Font defaultFont = new Font("Arial", Font.PLAIN, 12);
        Font boldFont = new Font("Arial", Font.BOLD, 12);
        
        UIManager.put("Button.font", defaultFont);
        UIManager.put("Label.font", defaultFont);
        UIManager.put("TextField.font", defaultFont);
        UIManager.put("TextArea.font", defaultFont);
        UIManager.put("ComboBox.font", defaultFont);
        UIManager.put("MenuBar.font", defaultFont);
        UIManager.put("MenuItem.font", defaultFont);
        UIManager.put("PasswordField.font", defaultFont);
        UIManager.put("Table.font", defaultFont);
        UIManager.put("TableHeader.font", boldFont);
        
        // Set color scheme
        Color primaryColor = new Color(51, 153, 255);   // Blue
        Color accentColor = new Color(0, 102, 204);     // Darker blue
        Color backgroundColor = new Color(240, 240, 240);// Light gray
        
        UIManager.put("Panel.background", backgroundColor);
        UIManager.put("Button.background", primaryColor);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.select", accentColor);
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));
        
        // Custom button UI
        UIManager.put("Button.border", BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(accentColor),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        
        System.out.println("UI initialization completed");
    }
    
    private static void showLoginScreen() {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.setLocationRelativeTo(null); // Center on screen
        loginScreen.setVisible(true);
        System.out.println("Login screen displayed");
    }
}