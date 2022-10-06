package novi.slotmachines;

public class HighRoller extends SlotMachine {

    public HighRoller() {
        super("High Roller",3);
    }

    @Override
    protected int getRollPrice() {
        return 10;
    }


    @Override
    public String renderWinningConditions() {
        return null;
    }
}
