package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    private boolean isDone;
    
    @Override
    public String doWork() {
        isDone = true;
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public long getHarga(int berat) {
        // Price is 1000/kg with no minimal
        return 1000 * berat;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
