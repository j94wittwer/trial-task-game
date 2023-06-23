package model;


import exception.CardSourceEmptyException;

public class Dealer extends Person {
    private final Deck deck;  // dealer holds a deck

    public Dealer() {
        super();  // dealer holds a hand
        deck = new Deck();  // standard deck with 52 cards
    }

    /**
     * When called, the Dealer fills his hand up with other cards, until the value of its hand is at least 17.
     */
    public void fillUpCards() {
        if (this.hand.getValue() < 17) {
            try {
                this.hand.add(this.deck.draw());
            } catch (CardSourceEmptyException e) {
                // This should never be the case, but for an almost finished turn to finish, we add another 52 cards to the deck.
                this.deck.add(new Deck().getCards());
                this.deck.shuffle();
            } finally {
                fillUpCards();
            }
        }
    }
}
