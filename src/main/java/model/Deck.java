package model;

import java.util.List;

/**
 * This is a Deck of cards. It does what a deck must do. Default Deck creation creates a classic Deck with 52 cards.
 * Other decks might be created having another constructor with arguments.
 * The Deck has the cards in a List. Because a List is a Collection, we can call the methods of Collection for the deck.
 * (But the card must be comparable).
 */
public class Deck extends CardSource {

    /**
     * This creates a classical Deck with 52 cards
     */
    public Deck() {
        super();
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(rank, suit);
                aCards.add(card);
            }
        }
    }

    /**
     * This creates a Deck with given cards
     *
     * @param pCards: List of cards
     */
    public Deck(List<Card> pCards) {
        super();
        if (pCards != null) {
            aCards.addAll(pCards);
        }
    }

    /**
     * Implementation from superclass CardSource - Two decks are the same if their cards are the same (in same order)
     *
     * @param other: other Deck
     * @return true if deck are equal to each other
     */
    public boolean equals(Deck other) {
        assert other != null;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (!this.aCards.get(i).equals(other.aCards.get(i))) {
                return false;
            }
        }
        return true;
    }
}
