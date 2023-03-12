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

    private static void handleGenerateUser() {
        String name = getData("Masukan nama Anda:", "name");
        String noHp = getData("Masukan nomor handphone Anda:", "nomorHP");
        String id = generateId(name, noHp);
        if (memberList.containsKey(id)) {
            System.out.printf(
                "Member dengan nama %1$s dan nomor hp %2$s sudah ada!\n",
                name, noHp
            );
            return;
        }
        Member member = new Member(name, noHp, id);
        memberList.put(id, member);
        System.out.printf("Berhasil membuat member dengan ID %s!\n\n", member.getId());
    }

    private static void handleGenerateNota() {
        System.out.println("Masukan ID member:");
        String memberId = input.nextLine();
        if (!memberList.containsKey(memberId)) {
            System.out.printf("Member dengan ID %s tidak ditemukan!", memberId);
            return;
        }
    
        Member member = memberList.get(memberId);
        member.incBonusCounter();
        boolean disc = member.getBonusCounter() == 3;
        if (disc) member.resetBonusCounter();

        String paket = getData("Masukan paket laundry", "packageType");
        int berat = Integer.parseInt(getData("Masukan berat cucian Anda [Kg]:", "weight"));
        String startDate = fmt.format(cal.getTime());
        String notaStr = generateNota(memberId, paket, berat, startDate, disc);
        System.out.println("Berhasil menambahkan nota!");
        System.out.printf("[ID Nota = %d]\n", idNota);
        System.out.println(notaStr);
        Nota nota = new Nota(idNota, member, paket, berat, startDate);
        notaList.put(idNota, nota);
        idNota++;
    }

    private static void handleListNota() {
        if (notaList.size() == 0) {
            System.out.println("Terdaftar 0 nota dalam sistem.");
            return;
        }

        System.out.printf("Terdapat %d nota dalam sistem.\n", notaList.size());
        for (Nota nota : notaList.values()) {
            System.out.printf(
                "- [%1$d] Status\t\t : %2$s\n",
                nota.getIdNota(),
                nota.isReady() ? "Sudah dapat diambil!" : "Belum bisa diambil :("
            );
        }
    }

    private static void handleListUser() {
        if (memberList.size() == 0) {
            System.out.println("Terdaftar 0 member dalam sistem.");
            return;
        }

        System.out.printf("Terdapat %d member dalam sistem.", memberList.size());
        for (Member member : memberList.values()) {
            System.out.printf("- %1$s : %2$s\n", member.getId(), member.getName());
        }
    }

    private static void handleAmbilCucian() {
        System.out.println("Masukan ID nota yang akan diambil:");
        int id = -1;
        boolean idValid = false;
        while (!idValid) {
            try {
                id = input.nextInt();
                if (id < 0) throw new InputMismatchException();
                idValid = true;
            } catch (InputMismatchException err) {
                System.out.println("ID nota berbentuk angka!");
            } finally {
                input.nextLine();
            }
        }

        if (!notaList.containsKey(id)) {
            System.out.printf("Nota dengan ID %d tidak ditemukan!\n", id);
            return;
        }

        if (!notaList.get(id).isReady()) {
            System.out.printf("Nota dengan ID %d gagal diambil!\n", id);
        }

        System.out.printf("Nota dengan ID %d berhasil diambil!\n", id);
        notaList.remove(id);
    }

    private static void handleNextDay() {
        System.out.println("Dek Depe tidur hari ini... z...");
        if (notaList.size() != 0) {
            for (Nota nota : notaList.values()) {
                nota.decSisaHariPengerjaan();
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
