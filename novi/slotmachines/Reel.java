package novi.slotmachines;

import java.util.Random;

public class Reel {

    private Symbol symbol;

    protected void roll() {
        Random random = new Random();

        Symbol[] sA = Symbol.values();
        symbol = sA[random.nextInt(sA.length)];
    }

    protected String render() {
        StringBuilder sb = new StringBuilder();
        sb.append(symbol.icon);
        sb.append('|');
        return sb.toString();
    }

    protected Symbol getSymbol() {
        return symbol;
    }
}
