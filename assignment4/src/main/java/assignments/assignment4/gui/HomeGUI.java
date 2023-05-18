package assignments.assignment4.gui;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource.CompoundBorderUIResource;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.ActionEvent;

import static assignments.assignment3.nota.NotaManager.toNextDay;

public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout, Feel free to make any changes

        // Set up main panel, Feel free to make any changes
        ImageIcon icon = new ImageIcon(MainFrame.getImageDir() + "panorama.gif");
        mainPanel = new Background(icon.getImage());
        mainPanel.setLayout(new GridBagLayout());
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
        
        titleLabel = new JLabel("Welcome to CuciCuci System!");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        constraints.weightx = 1;
        constraints.weighty = 0.4;
        constraints.insets = new Insets(30, 0, 10, 0);
        constraints.anchor = GridBagConstraints.PAGE_START;
        mainPanel.add(titleLabel, constraints);
        
        // Create color and border for buttons
        Color grayishWhite = new Color(0xDADADA);
        Color blue = new Color(0x33A7FF);
        Border btnBorder = new CompoundBorderUIResource(
            BorderFactory.createLineBorder(Color.BLACK, 1), 
            BorderFactory.createEmptyBorder(5, 20, 5, 20)
        );

        // Set loginButton
        loginButton = new JButton("⠀Login⠀");
        loginButton.addActionListener((ActionEvent e) -> handleToLogin());
        loginButton.setForeground(blue);
        loginButton.setBackground(grayishWhite);
        loginButton.setBorder(btnBorder);
        constraints.gridy = 1;
        constraints.weightx = 0.4;
        constraints.weighty = 0.05;
        constraints.ipadx = 100;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(loginButton, constraints);

        // Set registerButton
        registerButton = new JButton("Register");
        registerButton.addActionListener((ActionEvent e) -> handleToRegister());
        registerButton.setForeground(blue);
        registerButton.setBackground(grayishWhite);
        registerButton.setBorder(btnBorder);
        constraints.gridy = 2;
        mainPanel.add(registerButton, constraints);

        // Set toNextDayButton
        toNextDayButton = new JButton("Next Day");
        toNextDayButton.addActionListener((ActionEvent e) -> handleNextDay());
        toNextDayButton.setForeground(blue);
        toNextDayButton.setBackground(grayishWhite);
        toNextDayButton.setBorder(btnBorder);
        constraints.gridy = 3;
        mainPanel.add(toNextDayButton, constraints);

        // Set dateLabel
        dateLabel = new JLabel(
            "Today: " + NotaManager.fmt.format(NotaManager.cal.getTime())
        );
        dateLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        dateLabel.setForeground(Color.WHITE);
        constraints.gridy = 4;
        constraints.weightx = 1;
        constraints.weighty = 0.6;
        constraints.ipadx = 0;
        constraints.anchor = GridBagConstraints.PAGE_END;
        mainPanel.add(dateLabel, constraints);
    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame.getInstance().navigateTo(RegisterGUI.KEY);
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame.getInstance().navigateTo(LoginGUI.KEY);
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        toNextDay();
        dateLabel.setText(
            "Today: " + NotaManager.fmt.format(NotaManager.cal.getTime())
        );
        JOptionPane.showMessageDialog(
            this, 
            "You're done for the day... zzz...",
            "This is Prince Paul's Bubble Party Ability!",
            JOptionPane.INFORMATION_MESSAGE,
            new ImageIcon(MainFrame.getImageDir() + "laffey-sleep.gif")
        );
    }
}
