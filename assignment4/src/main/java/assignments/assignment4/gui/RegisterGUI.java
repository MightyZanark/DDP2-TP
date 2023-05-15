package assignments.assignment4.gui;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        // TODO
        GridBagConstraints constraints = new GridBagConstraints();
        
        nameLabel = new JLabel("Masukkan nama Anda:");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        mainPanel.add(nameLabel, constraints);

        nameTextField = new JTextField();
        constraints.gridy = 1;
        mainPanel.add(nameTextField, constraints);

        phoneLabel = new JLabel("Masukkan nomor handphone Anda:");
        constraints.gridy = 2;
        mainPanel.add(phoneLabel, constraints);

        phoneTextField = new JTextField();
        constraints.gridy = 3;
        mainPanel.add(phoneTextField, constraints);

        passwordLabel = new JLabel("Masukkan password Anda:");
        constraints.gridy = 4;
        mainPanel.add(passwordLabel, constraints);

        passwordField = new JPasswordField();
        constraints.gridy = 5;
        mainPanel.add(passwordField, constraints);

        registerButton = new JButton("Register");
        registerButton.addActionListener((ActionEvent e) -> handleRegister());
        constraints.gridy = 6;
        mainPanel.add(registerButton, constraints);

        backButton = new JButton("Back");
        backButton.addActionListener((ActionEvent e) -> handleBack());
        constraints.gridy = 7;
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
        // TODO
        String name = nameTextField.getText().trim();
        String phoneNum = phoneTextField.getText().trim();
        String pass = new String(passwordField.getPassword());

        if (name.isEmpty() || phoneNum.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(
                null, 
                "Please fill in all of the fields", 
                "Empty Field", 
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        if (!NotaGenerator.isStringNumeric(phoneNum)) {
            JOptionPane.showMessageDialog(
                null, 
                "Phone number has to be numeric!", 
                "Invalid Phone Number", 
                JOptionPane.ERROR_MESSAGE
            );
            phoneTextField.setText("");
            return;
        }

        Member registeredMember = loginManager.register(name, phoneNum, pass);
        if (registeredMember == null) {
            JOptionPane.showMessageDialog(
                null, 
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
            null, 
            String.format(
                "Successfully created member with ID %s!",
                registeredMember.getId()
            ), 
            "Registration Successfull", 
            JOptionPane.INFORMATION_MESSAGE
        );
        
        handleBack();
        return;
    }

    private void clearField() {
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
    }
}
