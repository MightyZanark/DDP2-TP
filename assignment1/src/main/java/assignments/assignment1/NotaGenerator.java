package assignments.assignment1;

import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        String opt;
        String nama;
        String nomorHP;
        String startDate;
        String packageType;
        String weight;
        String id;

        do {
            printMenu();
            System.out.print("Pilihan : ");
            opt = input.nextLine();
            System.out.println("================================");
            // input.nextLine();
            switch (opt) {
                case "0":
                    System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                    break;

                case "1":
                    nama = getData("Masukkan nama Anda:");
                    do {
                        nomorHP = getData("Masukkan nomor handphone Anda:");
                    } while (!isStringNumeric(nomorHP));

                    id = generateId(nama, nomorHP);
                    System.out.printf("ID Anda : %s\n\n", id);
                    break;

                case "2":
                    nama = getData("Masukkan nama Anda:");
                    do {
                        nomorHP = getData("Masukkan nomor handphone Anda:");
                    } while (!isStringNumeric(nomorHP));

                    id = generateId(nama, nomorHP);
                    startDate = getData("Masukkan tanggal terima:");
                    
                    do {
                        packageType = getData("Masukkan paket laundry:");
                        if (packageType.equals("?")) showPaket();
                        else if (!isPackage(packageType)) {
                            System.out.printf("Paket %s tidak diketahui\n", packageType);
                            System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                        }
                    } while (!isPackage(packageType));

                    do {
                        weight = getData("Masukkan berat cucian Anda [Kg]:");
                        if (!isStringNumeric(weight)) {
                            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        }
                    } while (!isStringNumeric(weight));

                    String nota = generateNota(id, packageType, Integer.parseInt(weight), startDate);
                    System.out.printf("Nota Laundry\n%s", nota);
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
    public static String generateId(String nama, String nomorHP) {
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

    public static String generateNota(String id, String paket, int berat, String tanggalTerima) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate endDate = LocalDate.parse(tanggalTerima, formatter);
        int packagePrice = 0;
        StringBuilder nota = new StringBuilder();
        nota.append(String.format("%-5s : %s\n", "ID", id));
        nota.append(String.format("%-5s : %s\n", "Paket", paket));
        nota.append(String.format("%-5s :\n", "Harga"));
        switch (paket.toLowerCase()) {
            case "express":
                packagePrice = 12000;
                endDate = endDate.plusDays(1);
                break;
            case "fast":
                packagePrice = 10000;
                endDate = endDate.plusDays(2);
                break;
            case "reguler":
                packagePrice = 7000;
                endDate = endDate.plusDays(3);
                break;
        }

        nota.append(String.format("%d kg x %d = %d\n", (berat < 2 ? 2 : berat), packagePrice, (berat * packagePrice)));
        nota.append(String.format("Tanggal Terima  : %s\n", tanggalTerima));
        nota.append(String.format("Tanggal Selesai : %s\n\n", endDate.format(formatter)));

        return nota.toString();
    }

    /**
     * Method to check if the whole string is numeric
     * @param str
     * @return true if the whole string is numeric, false otherwise
     * 
     */
    public static boolean isStringNumeric(String str) {
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
                // 65 = A, 90 = Z in Unicode
                checksum += character - 64;
            } else {
                // If char is anything other than capital letters or digits
                checksum += 7;
            }
        }
        return checksum;
    }

    /**
     * Asks the user for a type of data
     * @param msg
     * @return data
     */    
    public static String getData(String msg) {
        System.out.println(msg);
        String data = input.nextLine();
        return data;
    }

    public static boolean isPackage(String packageType) {
        return packageType.equalsIgnoreCase("express")
            || packageType.equalsIgnoreCase("fast") 
            || packageType.equalsIgnoreCase("reguler");
    }
}
