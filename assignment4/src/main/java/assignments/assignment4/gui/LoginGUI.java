package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        GridBagConstraints constraints = new GridBagConstraints();
    
        // Set idLabel
        idLabel = new JLabel("Masukkan ID Anda:");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.weightx = 1;
        constraints.insets = new Insets(10, 20, 10, 20);
        mainPanel.add(idLabel, constraints);
    
        // Set idTextField
        idTextField = new JTextField();
        constraints.gridy = 1;
        mainPanel.add(idTextField, constraints);

        // Set passwordLabel
        passwordLabel = new JLabel("Masukkan password Anda:");
        constraints.gridy = 2;
        mainPanel.add(passwordLabel, constraints);
        
        // Set passwordField
        passwordField = new JPasswordField();
        constraints.gridy = 3;
        mainPanel.add(passwordField, constraints);

        // Set loginButton
        loginButton = new JButton("Login");
        loginButton.addActionListener((ActionEvent e) -> handleLogin());
        constraints.gridy = 4;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(20, 20, 5, 20);
        mainPanel.add(loginButton, constraints);
        
        // Set backButton
        backButton = new JButton("Back");
        backButton.addActionListener((ActionEvent e) -> handleBack());
        constraints.gridy = 5;
        constraints.insets = new Insets(5, 20, 20, 20);
        mainPanel.add(backButton, constraints);

    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        clearField();
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        String id = idTextField.getText().trim();
        String password = new String(passwordField.getPassword());
        MainFrame mainFrame = MainFrame.getInstance();
        
        // Check if id is empty or if either id or pass is incorrect
        if (id.isEmpty() || !mainFrame.login(id, password)) {
            JOptionPane.showMessageDialog(
                null, 
                "ID or Password is incorrect", 
                "Invalid ID or Password", 
                JOptionPane.ERROR_MESSAGE
            );
        }

        clearField();
    }

    /**
     * Clears idTextField and passwordField
     */
    private void clearField() {
        idTextField.setText("");
        passwordField.setText("");
    }
}
