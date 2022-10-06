package novi.slotmachines;

import java.util.List;

public class ReelDeck {


    protected List<Reel> reels;

    public ReelDeck(List<Reel> reels) {
        this.reels = reels;
    }

    protected void roll() {
        for (Reel reel : reels) {
            reel.roll();
        }
    }

    protected String render() {
        StringBuilder sb = new StringBuilder();
        sb.append('|');
        for (Reel reel : reels) {
            sb.append(reel.render());
        }
        return sb.toString();
    }

    protected boolean allReelsHaveSymbol(Symbol symbol) {
        for (Reel reel : reels) {
            if (reel.getSymbol().icon != symbol.icon) {
                return false;
            }
        }
        return true;
    }






}
