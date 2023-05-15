package assignments.assignment4.gui.member.member;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        // Set up main panel, Feel free to make any changes
        initGUI();
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // TODO
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        paketLabel = new JLabel("Paket Laundry:");
        constraints.weightx = 0.8;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(paketLabel, constraints);

        String[] paketOption = new String[]{"Express", "Fast", "Reguler"};
        paketComboBox = new JComboBox<>(paketOption);
        constraints.gridx = 1;
        constraints.weightx = 0.3;
        constraints.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(paketComboBox, constraints);

        showPaketButton = new JButton("Show Paket");
        showPaketButton.addActionListener((ActionEvent e) -> showPaket());
        constraints.gridx = 2;
        mainPanel.add(showPaketButton, constraints);

        beratLabel = new JLabel("Berat Cucian (Kg):");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 0.8;
        constraints.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(beratLabel, constraints);

        beratTextField = new JTextField();
        constraints.gridx = 1;
        constraints.weightx = 0.3;
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(beratTextField, constraints);

        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000 / kg)");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(setrikaCheckBox, constraints);
        
        antarCheckBox = new JCheckBox("Tambah Antar Service (2000 / 4kg pertama, kemudian 500 / kg)");
        constraints.gridy = 3;
        mainPanel.add(antarCheckBox, constraints);

        createNotaButton = new JButton("Create Nota");
        createNotaButton.addActionListener((ActionEvent e) -> createNota());
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        mainPanel.add(createNotaButton, constraints);

        backButton = new JButton("Back");
        backButton.addActionListener((ActionEvent e) -> handleBack());
        constraints.gridy = 5;
        mainPanel.add(backButton, constraints);

        add(mainPanel);
    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        // TODO
        String paket = (String) paketComboBox.getSelectedItem();
        String berat = beratTextField.getText().trim();

        if (!NotaGenerator.isStringNumeric(berat) || Integer.parseInt(berat) <= 0) {
            JOptionPane.showMessageDialog(
                this, 
                "Weight must be a positive integer", 
                "Invalid Weight Value", 
                JOptionPane.ERROR_MESSAGE
            );
            clearField();
            return;
        }
        
        int beratInt = Integer.parseInt(berat);
        if (beratInt < 2) {
            beratInt = 2;
            JOptionPane.showMessageDialog(
                this, 
                "Weight is less than 2 kg, but will be treated as 2 kg", 
                "Minimum Weight", 
                JOptionPane.INFORMATION_MESSAGE
            );
        }

        Member member = memberSystemGUI.getLoggedInMember();
        Nota nota = new Nota(
            member,
            beratInt,
            paket,
            fmt.format(cal.getTime())
        );

        if (setrikaCheckBox.isSelected())
            nota.addService(new SetrikaService());
        
        if (antarCheckBox.isSelected())
            nota.addService(new AntarService());
        
        member.addNota(nota);
        NotaManager.addNota(nota);

        JOptionPane.showMessageDialog(
            this, 
            "Successfully created nota!", 
            "Nota Created", 
            JOptionPane.INFORMATION_MESSAGE
        );
        clearField();
        return;
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // TODO
        clearField();
        MainFrame.getInstance().navigateTo(MemberSystemGUI.KEY);
    }
    
    private void clearField() {
        paketComboBox.setSelectedIndex(0);
        beratTextField.setText("");
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
    }
}
