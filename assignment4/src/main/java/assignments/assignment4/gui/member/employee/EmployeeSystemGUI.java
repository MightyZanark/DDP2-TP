package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        JButton butt1 = new JButton("It's Nyuci Time!");
        JButton butt2 = new JButton("Display List Nota");
        return new JButton[]{
            butt1,
            butt2
        };
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void displayNota() {
        StringBuilder sb = new StringBuilder();
        for(Nota nota : NotaManager.notaList) {
            sb.append(nota.getNotaStatus() + "\n");
        }
        
        // Check if there is any nota to display
        String notaStatuses = sb.toString();
        if (notaStatuses.isBlank()) {
            JOptionPane.showMessageDialog(
                this, 
                "Nothing to see here...", 
                "Nota List", 
                JOptionPane.ERROR_MESSAGE,
                new ImageIcon(MainFrame.getImageDir() + "1171228.png")
            );
            return;
        }

        JOptionPane.showMessageDialog(
            this, 
            notaStatuses, 
            "Nota List", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {
        StringBuilder sb = new StringBuilder();
        for(Nota nota : NotaManager.notaList) {
            sb.append(nota.kerjakan() + "\n");
        }
    
        JOptionPane.showMessageDialog(
            this, 
            String.format(
                "Stand back! %s beginning to nyuci!", 
                loggedInMember.getNama()
            ), 
            "Nyuci Time", 
            JOptionPane.INFORMATION_MESSAGE,
            new ImageIcon(MainFrame.getImageDir() + "tsukuyo-watergun.gif")
        );
        
        // Check if there is any nota to display
        String result = sb.toString();
        if (result.isBlank()) {
            JOptionPane.showMessageDialog(
                this, 
                "Nothing to cuci here...", 
                "Nyuci Results", 
                JOptionPane.ERROR_MESSAGE,
                new ImageIcon(MainFrame.getImageDir() + "1171228.png")
            );
            return;
        }

        JOptionPane.showMessageDialog(
            this, 
            result, 
            "Nyuci Results", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
