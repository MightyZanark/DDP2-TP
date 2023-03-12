package assignments.assignment1;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        int opt = -1;
        String nama;
        String nomorHP;
        String startDate;
        String packageType;
        int weight;
        String id;

        do {
            printMenu();
            System.out.print("Pilihan : ");
            
            try {
                opt = input.nextInt();
                input.nextLine();
                System.out.println("================================");
            } catch (InputMismatchException err) {
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.\n");
                input.nextLine();
                continue;
            }
            
            switch (opt) {
                case 0:
                    System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                    break;

                case 1:
                    nama = getData("Masukkan nama Anda:", "name");
                    nomorHP = getData("Masukkan nomor handphone Anda:", "nomorHP");
                    id = generateId(nama, nomorHP);
                    System.out.printf("ID Anda : %s\n\n", id);
                    break;

                case 2:
                    nama = getData("Masukkan nama Anda:", "name");
                    nomorHP = getData("Masukkan nomor handphone Anda:", "nomorHP");
                    id = generateId(nama, nomorHP);
                    startDate = getData("Masukkan tanggal terima:", "startDate");
                    packageType = getData("Masukkan paket laundry:", "packageType");
                    weight = Integer.parseInt(getData("Masukkan berat cucian Anda [Kg]:", "weight"));
                    String nota = generateNota(id, packageType, weight, startDate, false);
                    System.out.printf("Nota Laundry\n%s\n\n", nota);
                    break;

                default:
                    System.out.println("Perintah tidak diketahui, silakan periksa kembali.\n");
                    break;
            }
        } while (opt != 0);
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
        int firstWhitespace = nama.trim().indexOf(' ');
        String firstName;
        try {
            firstName = nama.substring(0, firstWhitespace).toUpperCase();
        } catch (StringIndexOutOfBoundsException err) {
            firstName = nama.toUpperCase();
        }
        id.append(firstName + '-' + nomorHP.trim());
        String checksum = generateChecksum(id.toString());
        if (checksum.length() < 2) checksum = '0' + checksum;
        else if (checksum.length() > 2) checksum = checksum.substring(checksum.length() - 2);
        id.append('-' + checksum);

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
    public static String generateNota(String id, String paket, int berat, String tanggalTerima, boolean disc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate endDate = LocalDate.parse(tanggalTerima, formatter);
        long packagePrice = 0;
        StringBuilder nota = new StringBuilder();
        nota.append(String.format("%-5s : %s\n", "ID", id));
        nota.append(String.format("%-5s : %s\n", "Paket", paket));
        nota.append(String.format("%-5s :\n", "Harga"));
        switch (paket.toLowerCase()) {
            case "express":
                packagePrice = 12L;
                endDate = endDate.plusDays(1);
                break;
            case "fast":
                packagePrice = 10L;
                endDate = endDate.plusDays(2);
                break;
            case "reguler":
                packagePrice = 7L;
                endDate = endDate.plusDays(3);
                break;
        }
        
        berat = berat < 2 ? 2 : berat;
        long totalPrice = berat * packagePrice;
        if (!disc) {
            nota.append(String.format("%d kg x %d000 = %d000\n", berat, packagePrice, totalPrice));
        } else {
            nota.append(
                String.format(
                    "%d kg x %d000 = %d000 = %d000 (Discount member 50%%!!!)\n", 
                    berat, packagePrice, totalPrice, (totalPrice / 2)
                )
            );
        }
        nota.append(String.format("Tanggal Terima  : %s\n", tanggalTerima));
        nota.append(String.format("Tanggal Selesai : %s", endDate.format(formatter)));
        nota.append(String.format("%-15s : Belum bisa diambil :(", "Status"));

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
            if (!Character.isDigit(character)) return false;
        }
        return true;
    }

    /**
     * Generates the checksum needed for ID
     * @param str
     * @return 2 digit checksum as a string
     */
    public static String generateChecksum(String str) {
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
        return Integer.toString(checksum);
    }

    /**
     * Asks the user for a type of data
     * @param msg
     * @param type name, nomorHP, startDate, packageType, or weight
     * @return data of type
     */    
    public static String getData(String msg, String type) {
        System.out.println(msg);
        String data = input.nextLine().trim();
        // input.nextLine();

        switch (type) {
            case "nomorHP":
                while (!isStringNumeric(data)) {
                    System.out.println("Field nomor hp hanya menerima digit");
                    data = input.nextLine().trim();
                }
                break;

            case "packageType":
                while (!isPackage(data)) {
                    if (data.equals("?")) {
                        showPaket();
                        System.out.println(msg);
                    } else {
                        System.out.printf("Paket %s tidak diketahui\n", data);
                        System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                        System.out.println(msg);
                    }
                    data = input.nextLine().trim();
                }
                break;

            case "weight":
                while (!isStringNumeric(data) || Integer.parseInt(data) < 1) {
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    data = input.nextLine().trim();
                }

                if (Integer.parseInt(data) < 2) {
                    System.out.println("Cucian kurang dari 2 kg, maka cucian dianggap sebagai 2 kg");
                }
                break;

            case "name":
            case "startDate":
            default:
                break;
        }
        return data;
    }

    /**
     * Checks if laundry package exist
     * @param packageType
     * @return true if package exist
     */
    public static boolean isPackage(String packageType) {
        return packageType.equalsIgnoreCase("express")
            || packageType.equalsIgnoreCase("fast") 
            || packageType.equalsIgnoreCase("reguler");
    }
}
