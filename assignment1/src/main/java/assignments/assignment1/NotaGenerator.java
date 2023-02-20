package assignments.assignment1;

import java.util.Scanner;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        String opt;

        do {
            printMenu();
            System.out.print("Pilihan : ");
            opt = input.nextLine();
            // input.nextLine();
            switch (opt) {
                case "0":
                    System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                    break;
                case "1":
                    System.out.println("================================");
                    System.out.println("Masukkan nama Anda:");
                    String nama = input.nextLine();
                    // input.nextLine();
                    System.out.println("Masukkan nomor handphone Anda:");
                    String nomorHP;
                    do {
                        nomorHP = input.next();
                        input.nextLine();
                    } while (!isNumeric(nomorHP));

                    String id = generateId(nama, nomorHP);
                    System.out.printf("ID Anda : %s\n\n", id);
                    break;
                case "2":
                    break;
                default:
                    System.out.println("Perintah tidak diketahui, silakan periksa kembali.\n");
                    break;
            }
        } while (!opt.equals("0"));
    }

    /**
     * Method untuk menampilkan menu di NotaGenerator.
     */
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    /**
     * Method untuk menampilkan paket.
     */
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
     *
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
    public static String generateId(String nama, String nomorHP){
        StringBuilder id = new StringBuilder();
        int firstWhitespace = nama.indexOf(' ');
        String firstName = nama.substring(0, firstWhitespace).toUpperCase();
        id.append(firstName + '-' + nomorHP);
        int checksum = generateChecksum(id.toString());
        id.append(String.format("-%02d", checksum));

        return id.toString();
    }

    /**
     *
     * Method untuk membuat Nota.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
     *
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */

    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        // TODO: Implement generate nota sesuai soal.
        return null;
    }

    public static boolean isNumeric(String str) {
        for (char character : str.toCharArray()) {
            // System.out.println(character);
            if (!Character.isDigit(character)) return false;
        }
        return true;
    }

    public static int generateChecksum(String str) {
        int checksum = 0;
        for (char character : str.toCharArray()) {
            if (Character.isDigit(character)) {
                checksum += Character.getNumericValue(character);
            } else if (character >= 65 && character <= 90) {
                checksum += character - 64;
            } else {
                checksum += 7;
            }
        }
        return checksum;
    }
}
