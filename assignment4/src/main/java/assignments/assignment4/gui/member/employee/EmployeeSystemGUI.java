package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
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
        // TODO
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
        // TODO
        StringBuilder sb = new StringBuilder();
        for(Nota nota : NotaManager.notaList) {
            sb.append(nota.getNotaStatus() + "\n");
        }
        
        JOptionPane.showMessageDialog(
            null, 
            sb.toString(), 
            "Nota List", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {
        // TODO
        StringBuilder sb = new StringBuilder();
        for(Nota nota : NotaManager.notaList) {
            sb.append(nota.kerjakan() + "\n");
        }
    
        JOptionPane.showMessageDialog(
            null, 
            String.format(
                "Stand back! %s beginning to nyuci!", 
                loggedInMember.getNama()
            ), 
            "Nyuci Time", 
            JOptionPane.INFORMATION_MESSAGE
        );
        
        JOptionPane.showMessageDialog(
            null, 
            sb.toString(), 
            "Nyuci Results", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
