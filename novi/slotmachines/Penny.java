package novi.slotmachines;

public class Penny extends SlotMachine {

    public Penny() {
        super("Penny Machine", 3);
    }

    @Override
    protected int getRollPrice() {
        return 1;
    }

    @Override
    public String renderWinningConditions() {
        return null;
    }
}
