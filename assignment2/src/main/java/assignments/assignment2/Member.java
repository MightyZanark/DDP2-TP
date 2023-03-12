package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    private String nama;
	private String noHp;
	private String id;
	private int bonusCounter;

    public Member(String nama, String noHp, String id) {
        this.nama = nama;
        this.noHp = noHp;
        this.id = id;
        this.bonusCounter = 0;
    }

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
