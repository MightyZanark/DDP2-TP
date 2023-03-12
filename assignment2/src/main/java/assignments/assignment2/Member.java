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
        this.bonusCounter = 0;
        this.id = NotaGenerator.generateId(nama, noHp);

        // TODO: buat constructor untuk class ini
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    public String getName() {
        return nama;
    }

    public String getNoHP() {
        return noHp;
    }

    public String getId() {
        return id;
    }

    public int getBonusCounter() {
        return bonusCounter;
    }

    public void incBonusCounter() {
        this.bonusCounter++;
    }

    public void resetBonusCounter() {
        this.bonusCounter = 0;
    }
}
