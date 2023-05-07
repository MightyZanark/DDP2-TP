package assignments.assignment3.nota;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;
import assignments.assignment1.NotaGenerator;

import java.util.ArrayList;

public class Nota {
    private Member member;
    private String paket;
    private ArrayList<LaundryService> services = new ArrayList<>();
    private long baseHarga;
    private int sisaHariPengerjaan;
    private int berat;
    private int id;
    private String tanggalMasuk;
    private boolean isDone;
    public static int totalNota;
    public static final String formatStatus = "Nota %d : %s"; // Format string for nota status

    public Nota(Member member, int berat, String paket, String tanggal) {
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggal;
        this.id = totalNota++;
        addService(new CuciService()); // Every nota always has CuciService

        switch (paket.toLowerCase()) {
			case "reguler" -> {
                this.sisaHariPengerjaan = 3;
                this.baseHarga = berat * 7000L;
            }
            
			case "fast" -> {
                this.sisaHariPengerjaan = 2;
                this.baseHarga = berat * 10000L;
            }

			case "express" -> {
                this.sisaHariPengerjaan = 1;
                this.baseHarga = berat * 12000L;
            }
		}
    }

    public void addService(LaundryService service){
        services.add(service);
    }

    public String kerjakan(){
        // Iterate over all services and do it if its not done yet
        for (int i = 0; i < services.size(); i++) {
            if (!services.get(i).isDone()) {
                // If current service is the last, nota is done
                if (i == services.size() - 1) isDone = true;

                return String.format(formatStatus, id, services.get(i).doWork());
            }
        }

        return String.format(formatStatus, id, "Sudah selesai.");
    }
    public void toNextDay() {
        // If the nota is not done,
        // reduce sisaHariPengerjaan by 1
        if (!isDone) {
            --sisaHariPengerjaan;
        }
    }

    public long calculateHarga(){
        // Set current sum as baseHarga
        long sum = baseHarga;

        // Add every service cost to the current sum
        for (LaundryService service : services) {
            sum += service.getHarga(berat);
        }

        // If nota sisaHariPengerjaan is < 0,
        // then apply compensation fee, which is 2000/day
        if (sisaHariPengerjaan < 0) {
            sum += sisaHariPengerjaan * 2000;
        }

        // Sum cannot be negative
        // If sum < 0, return 0 instead. Return the sum otherwise
        return sum < 0 ? 0 : sum;
    }

    public String getNotaStatus(){
        // If Nota is not done return not ready
        if (!isDone) {
            return String.format(formatStatus, id, "Belum selesai.");
        }

        return String.format(formatStatus, id, "Sudah selesai.");
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("[ID Nota = %d]\n", id));
        res.append(NotaGenerator.generateNota(member.getId(), paket, berat, tanggalMasuk));
        res.append("--- SERVICE LIST ---\n");
        for (LaundryService service : services) {
            res.append(
                String.format(
                    "-%s @ Rp.%d\n", 
                    service.getServiceName(), 
                    service.getHarga(berat))
            );
        }
        res.append(String.format("Harga Akhir: %d", calculateHarga()));

        // If nota sisaHariPengerjaan < 0, then final price
        // includes compensation fee, so add the info to the detail
        if (sisaHariPengerjaan < 0) {
            res.append(
                String.format(
                    " Ada kompensasi keterlambatan %d * 2000 hari", 
                    Math.abs(sisaHariPengerjaan)
                )
            );
        }
        return res.toString() + "\n";
    }

    // Dibawah ini adalah getter

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }
    public boolean isDone() {
        return isDone;
    }

    public LaundryService[] getServices(){
        LaundryService[] tempArr = new LaundryService[0];
        return services.toArray(tempArr);
    }

    public int getId() {
        return id;
    }
}
