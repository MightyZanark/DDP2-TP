package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Nota {
	private static int idNota = 0;
	private String paket;
	private Member member;
	private int berat;
	private String tanggalMasuk;
	private int sisaHariPengerjaan;
	private boolean isReady;
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
    	this.member = member;
		this.paket = paket;
		this.berat = berat;
		this.tanggalMasuk = tanggalMasuk;
		// TODO: buat constructor untuk class ini
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
}
