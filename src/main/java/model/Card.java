package model;

/**
 * This is a card. It does what a card is supposed to do. It has a suit and a rank. We can compare the value of a card
 * with another card's value
 */
public class Card implements Comparable<Card> {
    private final Rank aRank;
    private final Suit aSuit;

    /**
     * Constructs a new Card, with a rank and a suite
     *
     * @pre pRank != null && pSuit != null
     */
    public Card(Rank pRank, Suit pSuit) {
        assert pRank != null && pSuit != null;
        aRank = pRank;
        aSuit = pSuit;
    }

    /**
     * @return Rank, the enumeration. No problem having a getter here (It is well encapsulated)
     */
    public Rank getRank() {
        return aRank;
    }

    /**
     * @return Suit, the enumeration. No problem having a getter here (It is well encapsulated)
     */
    public Suit getSuit() {
        return aSuit;
    }

    /**
     * @param other: Card (the other card)
     * @return integer: value to sort the cards (negative number if implicit argument comes before explicit, positive if
     * implicit argument comes after explicit, 0 if they should be the same
     */
    @Override
    public int compareTo(Card other) {
        // hearts < diamond < clubs < spades
        return (other.aRank.ordinal() - aRank.ordinal()) * Suit.values().length + other.aSuit.ordinal() - aSuit.ordinal();
    }

    /**
     * Two cards are equal, if their suit and their rank are both equal
     *
     * @param other: Card - card to compare to
     * @return boolean - true if cards are equal, false otherwise
     */
    public boolean equals(Card other) {
        return other.aSuit == this.aSuit && other.aRank == this.aRank;
    }
}

