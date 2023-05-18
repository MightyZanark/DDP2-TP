package assignments.assignment4.gui;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
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
        
        // Set nameLabel
        nameLabel = new JLabel("Masukkan nama Anda:");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.weightx = 1;
        constraints.insets = new Insets(10, 20, 10, 20);
        mainPanel.add(nameLabel, constraints);

        // Set nameTextField
        nameTextField = new JTextField();
        constraints.gridy = 1;
        mainPanel.add(nameTextField, constraints);

        // Set phoneLabel
        phoneLabel = new JLabel("Masukkan nomor handphone Anda:");
        constraints.gridy = 2;
        mainPanel.add(phoneLabel, constraints);

        // Set phoneTextField
        phoneTextField = new JTextField();
        constraints.gridy = 3;
        mainPanel.add(phoneTextField, constraints);

        // Set passwordLabel
        passwordLabel = new JLabel("Masukkan password Anda:");
        constraints.gridy = 4;
        mainPanel.add(passwordLabel, constraints);

        // Set passwordField
        passwordField = new JPasswordField();
        constraints.gridy = 5;
        mainPanel.add(passwordField, constraints);

        // Set registerButton
        registerButton = new JButton("Register");
        registerButton.addActionListener((ActionEvent e) -> handleRegister());
        constraints.gridy = 6;
        constraints.insets = new Insets(20, 20, 5, 20);
        constraints.fill = GridBagConstraints.NONE;
        mainPanel.add(registerButton, constraints);
        
        // Set backButton
        backButton = new JButton("Back");
        backButton.addActionListener((ActionEvent e) -> handleBack());
        constraints.gridy = 7;
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
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        String name = nameTextField.getText().trim();
        String phoneNum = phoneTextField.getText().trim();
        String pass = new String(passwordField.getPassword());

        // Check if all fields are filled
        if (name.isEmpty() || phoneNum.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, 
                "Please fill in all of the fields", 
                "Empty Field", 
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        // Check if phone number is only numbers
        if (!NotaGenerator.isStringNumeric(phoneNum)) {
            JOptionPane.showMessageDialog(
                this, 
                "Phone number has to be numeric!", 
                "Invalid Phone Number", 
                JOptionPane.ERROR_MESSAGE
            );
            phoneTextField.setText("");
            return;
        }

        Member registeredMember = loginManager.register(name, phoneNum, pass);
        
        // Check if registeredMember already exist
        if (registeredMember == null) {
            JOptionPane.showMessageDialog(
                this, 
                String.format(
                    "Member %s with phone number %s already exist!",
                    name,
                    phoneNum
                ), 
                "Registration Failed", 
                JOptionPane.ERROR_MESSAGE
            );
            handleBack();
            return;
        }

        JOptionPane.showMessageDialog(
            this, 
            String.format(
                "Successfully created member with ID %s!\n",
                registeredMember.getId()
            ), 
            "Registration Successful", 
            JOptionPane.INFORMATION_MESSAGE,
            new ImageIcon(MainFrame.getImageDir() + "pompom-welcome.gif")
        );
        
        handleBack();
        return;
    }

    /**
     * Clears nameTextField, phoneTextField, and passwordField
     */
    private void clearField() {
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
    }
}
