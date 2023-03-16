package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    private String nama;
	private String noHp;
	private String id;
	private int bonusCounter;

    public Member(String nama, String noHp) {
        this.nama = nama;
        this.noHp = noHp;
        this.id = NotaGenerator.generateId(nama, noHp);
        this.bonusCounter = 0;
    }

    public String toString() {
        return String.format("%1$s : %2$s", id, nama);
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

    /**
     * Method to simply increase the bonus counter.
     */
    public void incBonusCounter() {
        this.bonusCounter++;
    }

    /**
     * Method to reset the bonus counter when it reaches 3.
     */
    public void resetBonusCounter() {
        this.bonusCounter = 0;
    }
}
