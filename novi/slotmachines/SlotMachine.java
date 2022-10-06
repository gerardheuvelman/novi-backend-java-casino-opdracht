package novi.slotmachines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class SlotMachine {

    private List<Reel> reels;

    protected  String name;
    protected int numberOfReels;

    protected ReelDeck reelDeck;

    public SlotMachine(String name, int numberOfReels) {
        this.name = name;
        this.numberOfReels = numberOfReels;

        reels = new ArrayList<>();
        for (int i = 0; i < numberOfReels; i++) {
            Reel reel = new Reel();
            reels.add(reel);
        }
        reelDeck = new ReelDeck(reels);

    }

    protected void renderIntro() {
        System.out.println("Welcome to the " + name);
    }
    protected int roll() {
        boolean winCondition =false;
        reelDeck.roll();
        System.out.println(reelDeck.render());
        if (reelDeck.allReelsHaveSymbol(Symbol.YINYANG) ||
                reelDeck.allReelsHaveSymbol(Symbol.CLUB) ||
                reelDeck.allReelsHaveSymbol(Symbol.HEART) ||
                reelDeck.allReelsHaveSymbol(Symbol.STAR) ||
                reelDeck.allReelsHaveSymbol(Symbol.SPADE)) {
            winCondition = true;
            System.out.println("You win!");
        }

        if (winCondition) {
            return calculatePayout();
        } else {
            System.out.println("You lose!");
            return 0;
        }
    }

    protected String render() {
        return null;
    }

    protected abstract int getRollPrice();
    public int calculatePayout() {
        int payout = (int)Math.pow(5, numberOfReels) /5 * getRollPrice();
        System.out.println( "You have won " + payout + " coins.");
        return payout;
    }
    public abstract String renderWinningConditions();

}
