package novi.slotmachines;

public class FiveReeler extends SlotMachine {

    public FiveReeler() {
        super("Five Reeler", 5);
    }

    @Override
    protected int getRollPrice() {
        return 5;
    }


    @Override
    public String renderWinningConditions() {
        return null;
    }
}
