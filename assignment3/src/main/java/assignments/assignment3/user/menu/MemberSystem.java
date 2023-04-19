package assignments.assignment3.user.menu;
import assignments.assignment3.user.Member;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.service.*;
import assignments.assignment1.NotaGenerator;

import assignments.assignment3.nota.NotaManager;

public class MemberSystem extends SystemCLI {
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        switch(choice) {
            case 1 -> addLaundry();
            case 2 -> displayNotaSaya();
            case 3 -> logout = true;
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        memberList.add(member);
    }

    /**
     * Adds a laundry for the current loginMember
     */
    private void addLaundry() {
        System.out.println("Masukan paket laundry:");
        NotaGenerator.showPaket();
        String paket = NotaGenerator.getData("", "packageType", in);
        int berat = Integer.parseInt(
            NotaGenerator.getData(
                "Masukan berat cucian anda [Kg]:", 
                "weight", 
                in
            )
        );
        String startDate = NotaManager.fmt.format(NotaManager.cal.getTime());
        Nota nota = new Nota(loginMember, berat, paket, startDate);
        NotaManager.addNota(nota); // Adds nota to the big listNota
        loginMember.addNota(nota); // Adds nota to loginMember's personal nota list

        String ans;

        // Asks if loginMember wants SetrikaService
        System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?");
        System.out.println("Hanya tambah 1000 / kg :0");
        System.out.print("[Ketik x untuk tidak mau]: ");
        ans = in.nextLine();
        if (!ans.equalsIgnoreCase("x"))
            nota.addService(new SetrikaService());
        
        // Asks if loginMember wants AntarService
        System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!");
        System.out.println("Cuma 2000 / 4kg, kemudian 500 / kg");
        System.out.print("[Ketik x untuk tidak mau]: ");
        ans = in.nextLine();
        if (!ans.equalsIgnoreCase("x"))
            nota.addService(new AntarService());
        
        System.out.println("Nota berhasil dibuat!");
    }

    /**
     * Displays the detail of all nota owned by loginMember
     */
    private void displayNotaSaya() {
        for (Nota nota : loginMember.getNotaList()) {
            System.out.println(nota);
        }
    }
}