package assignments.assignment3.user;

public class Employee extends Member {
    public static int employeeCount;
    public Employee(String nama, String password) {
        super(nama, generateId(nama), password);
    }

    /**
     * Membuat ID employee dari nama employee dengan format
     * NAMA_DEPAN-[jumlah employee, mulai dari 0]
     * Contoh: Dek Depe adalah employee pertama yang dibuat, sehingga IDnya adalah DEK-0
     *
     * @param nama -> Nama lengkap dari employee
     */
    private static String generateId(String nama) {
        // Split nama based on whitespaces and get the first element
        String firstName = nama.split("\\s+")[0];
        StringBuilder id = new StringBuilder(firstName.toUpperCase());
        id.append(String.format("-%d", employeeCount++));

        return id.toString();
    }
}
