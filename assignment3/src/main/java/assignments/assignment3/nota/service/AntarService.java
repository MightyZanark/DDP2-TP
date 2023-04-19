package assignments.assignment3.nota.service;


public class AntarService implements LaundryService{
    private boolean isDone;
    
    @Override
    public String doWork() {
        isDone = true;
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public long getHarga(int berat) {
        // Price has a minimal of 2000 for the first 4 kg
        // and an extra 500 for every 1 kg after
        return berat <= 4 ? 2000 : 500 * berat;
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
