package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.HashMap;
import java.util.InputMismatchException;

import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static HashMap<Integer, Nota> notaList = new HashMap<>();
    private static HashMap<String, Member> memberList = new HashMap<>();
    private static int idNota = 0;

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    /**
     * Handles the Generate User option (1)
     */
    private static void handleGenerateUser() {
        String name = getData("Masukan nama Anda:", "name");
        String noHp = getData("Masukan nomor handphone Anda:", "nomorHP");
        Member member = new Member(name, noHp);

        // Checks if member already exists by their unique ID
        if (memberList.containsKey(member.getId())) {
            System.out.printf(
                "Member dengan nama %1$s dan nomor hp %2$s sudah ada!\n",
                name, noHp
            );
            return;
        }

        // If they aren't a member yet, add them as a member
        memberList.put(member.getId(), member);
        System.out.printf("Berhasil membuat member dengan ID %s!\n", member.getId());
    }

    /**
     * Handles the Generate Nota option (2)
     */
    private static void handleGenerateNota() {
        System.out.println("Masukan ID member:");
        String memberId = input.nextLine();

        // Checks if the inputted member id is valid
        if (!memberList.containsKey(memberId)) {
            System.out.printf("Member dengan ID %s tidak ditemukan!", memberId);
            return;
        }

        String paket = getData("Masukan paket laundry", "packageType");
        int berat = Integer.parseInt(getData("Masukan berat cucian Anda [Kg]:", "weight"));
        String startDate = fmt.format(cal.getTime());
    
        // Gets the member that corresponds to the inputted member id
        // and increases their bonus counter
        Member member = memberList.get(memberId);
        member.incBonusCounter();
        
        Nota nota = new Nota(idNota, member, paket, berat, startDate);
        System.out.println(nota.getNotaString());

        // Checks if this is the member's 3rd order, if it is then reset the bonus counter
        if (member.getBonusCounter() == 3) member.resetBonusCounter();

        // Adds the nota to the nota list to keep track of the order
        notaList.put(idNota, nota);
        idNota++;
    }

    /**
     * Handles the List Nota option (3)
     */
    private static void handleListNota() {
        // Checks to see if there is any nota on the list
        if (notaList.size() == 0) {
            System.out.println("Terdaftar 0 nota dalam sistem.");
            return;
        }

        // If there are notas on the list, iterate over them to see their status
        System.out.printf("Terdapat %d nota dalam sistem.\n", notaList.size());
        for (Nota nota : notaList.values()) {
            System.out.printf(
                "- [%1$d] Status\t\t : %2$s\n",
                nota.getIdNota(),
                nota.isReady() ? "Sudah dapat diambil!" : "Belum bisa diambil :("
            );
        }
    }

    /**
     * Handles the List User option (4)
     */
    private static void handleListUser() {
        // Checks to see if there is any member on the list
        if (memberList.size() == 0) {
            System.out.println("Terdaftar 0 member dalam sistem.");
            return;
        }

        // If there are members on the list, iterate over them to see their id and full names
        System.out.printf("Terdapat %d member dalam sistem.\n", memberList.size());
        for (Member member : memberList.values()) {
            System.out.printf("- %1$s : %2$s\n", member.getId(), member.getName());
        }
    }

    /**
     * Handles the Ambil Cucian option (5)
     */
    private static void handleAmbilCucian() {
        System.out.println("Masukan ID nota yang akan diambil:");
        int id = -1;
        boolean idValid = false;
        while (!idValid) {
            // Checks if id is valid (>= 0)
            try {
                id = input.nextInt();
                
                // Negative id is the same as invalid id so jump to catch block
                if (id < 0) throw new InputMismatchException();

                idValid = true;
            } catch (InputMismatchException err) {
                // Tells the user how the ID should really be
                System.out.println("ID nota berbentuk angka!");
            } finally {
                input.nextLine();
            }
        }

        // Checks if nota list has the inputted id
        if (!notaList.containsKey(id)) {
            System.out.printf("Nota dengan ID %d tidak ditemukan!\n", id);
            return;
        }

        // Checks if the inputted id is ready to be picked up
        if (!notaList.get(id).isReady()) {
            System.out.printf("Nota dengan ID %d gagal diambil!\n", id);
        }

        // On a successful pickup, remove the nota from the list
        System.out.printf("Nota dengan ID %d berhasil diambil!\n", id);
        notaList.remove(id);
    }

    /**
     * Handles the Next Day option (6)
     */
    private static void handleNextDay() {
        System.out.println("Dek Depe tidur hari ini... z...");

        // Checks if there is any nota on the list
        if (notaList.size() != 0) {

            // If there are notas on the list, iterate over them
            for (Nota nota : notaList.values()) {
                
                // Decreases time remaining for the laundry to be finished
                nota.decSisaHariPengerjaan();

                // Checks if the laundry is ready to be picked up
                if (nota.isReady()) {
                    System.out.printf(
                        "Laundry dengan nota ID %d sudah dapat diambil!\n",
                        nota.getIdNota()
                    );
                }
            }
        }

        System.out.println("Selamat pagi dunia!");
        System.out.println("Dek Depe: It's CuciCuci Time.");

        // Moves forward by 1 day
        cal.add(Calendar.DATE, 1);
    }

    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

}
