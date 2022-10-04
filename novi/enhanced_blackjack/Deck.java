package novi.enhanced_blackjack;
import java.util.*;

public abstract class Deck {

    protected List<Card> cards = new ArrayList<>();

    protected CardSuit hearts = new CardSuit("Hearts", 'H', "red");
    protected CardSuit diamonds = new CardSuit("Diamonds", 'D', "red");
    protected CardSuit clubs = new CardSuit("Clubs", 'C', "black");
    protected CardSuit spades = new CardSuit("Spades", 'S', "black");

    protected CardValue two = new CardValue("Two",2);
    protected CardValue three = new CardValue("Three",3);
    protected CardValue four = new CardValue("Four",4);
    protected CardValue five = new CardValue("Five",5);
    protected CardValue six = new CardValue("Six",6);
    protected CardValue seven = new CardValue("Seven",7);
    protected CardValue eight = new CardValue("Eight",8);
    protected CardValue nine = new CardValue("Nine",9);
    protected CardValue ten = new CardValue("Ten",10);
    protected CardValue jack = new CardValue("Jack",10);
    protected CardValue queen = new CardValue("Queen",10);
    protected CardValue king = new CardValue("King",10);


    // GETSET //

    public List<Card> getCards() {
        return cards;
    }

    void shuffle() {
        Collections.shuffle(cards);
     }

//    abstract boolean isEmpty();

    Card getNextCard() {
        return cards.remove(0);
    }

}
