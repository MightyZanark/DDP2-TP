package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Nota {
	private int idNota;
	private String paket;
	private Member member;
	private int berat;
	private String tanggalMasuk;
	private int sisaHariPengerjaan;
	private boolean isReady = false;
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    public Nota(int idNota, Member member, String paket, int berat, String tanggalMasuk) {
		this.idNota = idNota;
    	this.member = member;
		this.paket = paket;
		this.berat = berat;
		this.tanggalMasuk = tanggalMasuk;

		switch (paket.toLowerCase()) {
			case "reguler" -> this.sisaHariPengerjaan = 3;
			case "fast" -> this.sisaHariPengerjaan = 2;
			case "express" -> this.sisaHariPengerjaan = 1;
		}
		// TODO: buat constructor untuk class ini
    }

	public void decSisaHariPengerjaan() {
		if (this.sisaHariPengerjaan != 0) {
			this.sisaHariPengerjaan--;
			return;
		}

		this.isReady = true;
	}

	public boolean isReady() {
		return isReady;
	}

	public int getIdNota() {
		return this.idNota;
	}
    // TODO: tambahkan methods yang diperlukan untuk class ini
	
}
