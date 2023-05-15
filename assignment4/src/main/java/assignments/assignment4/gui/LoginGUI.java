package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        // TODO
        GridBagConstraints constraints = new GridBagConstraints();
    
        idLabel = new JLabel("Masukkan ID Anda:");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        mainPanel.add(idLabel, constraints);
    
        idTextField = new JTextField();
        constraints.gridy = 1;
        mainPanel.add(idTextField, constraints);

        passwordLabel = new JLabel("Masukkan password Anda:");
        constraints.gridy = 2;
        mainPanel.add(passwordLabel, constraints);
        
        passwordField = new JPasswordField();
        constraints.gridy = 3;
        mainPanel.add(passwordField, constraints);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridy = 4;
        mainPanel.add(loginButton, constraints);

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
            }
        });
        constraints.gridy = 5;
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
        // TODO
        String id = idTextField.getText().trim();
        String password = new String(passwordField.getPassword());
        MainFrame mainFrame = MainFrame.getInstance();
        
        if (id.isEmpty() || !mainFrame.login(id, password)) {
            JOptionPane.showMessageDialog(
                null, 
                "ID or Password is incorrect", 
                "Invalid ID or Password", 
                JOptionPane.ERROR_MESSAGE
            );
            idTextField.setText("");
            passwordField.setText("");
            return;
        }

        clearField();
    }

    private void clearField() {
        idTextField.setText("");
        passwordField.setText("");
    }
}
