package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    private String nama;
	private String noHp;
	private String id;
	private int bonusCounter;
	// TODO: tambahkan attributes yang diperlukan untuk class ini
    public Member(String nama, String noHp) {
        this.nama = nama;
        this.noHp = noHp;
        // TODO: buat constructor untuk class ini
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    public String getId() {
        return this.id;
    }

    public int getBonusCounter() {
        return bonusCounter;
    }

    
}
