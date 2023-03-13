package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Nota {
	private int idNota;
	private String paket;
	private Member member;
	private int berat;
	private String tanggalMasuk;
	private int sisaHariPengerjaan;
	private boolean isReady;

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
    }

	/**
	 * Method to decrease the amount of days left until the laundry
	 * is finished.
	 * <p>When sisaHariPengerjaan reaches 0, sets isReady to true
	 */
	public void decSisaHariPengerjaan() {
		if (this.sisaHariPengerjaan != 0) {
			this.sisaHariPengerjaan--;
			return;
		}

		this.isReady = true;
	}

	/**
	 * Method to generate the nota for printing purposes
	 * @return result from NotaGenerator.generateNota added
	 *         with extra information needed from the doc
	 */
	public String getNotaString() {
		StringBuilder out = new StringBuilder();
		out.append("Berhasil menambahkan nota!");
		out.append(String.format("[ID Nota = %d]", this.idNota));
		out.append(
			NotaGenerator.generateNota(
				this.member.getId(), 
				this.paket, 
				this.berat, 
				this.tanggalMasuk, 
				this.member.getBonusCounter() == 3
			)
		);

		return out.toString();
	}

	/**
	 * Checks if the laundry is ready to be picked up
	 * @return ready state
	 */
	public boolean isReady() {
		return isReady;
	}

	public int getIdNota() {
		return this.idNota;
	}
}
