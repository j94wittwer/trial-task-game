package model;

import java.util.HashSet;
import java.util.List;

public class Hand extends CardSource {

    public Hand() {
        super();
    }

    /**
     * This creates a Hand with given cards
     *
     * @param pCards: List of cards
     */
    public Hand(List<Card> pCards) {
        super();
        if (pCards != null) {
            aCards.addAll(pCards);
        }
    }

    /**
     * Returns the value of a CardSource, an ace can be worth 1 or 11, if possible, aces do not result in a bust
     *
     * @return int: Value of card in CardSource (0 if empty, > 1 else)
     */
    public int getValue() {
        int[] allValues = getAllValues();
        return allValues[allValues.length - 1];
    }

    /**
     * Returns the value of the hole CardSource, an ace can be worth 1 or 11, if possible, aces do not result in a bust
     *
     * @return int: Value of hand (0 if empty, > 1 else)
     */
    public int[] getAllValues() {
        int result = 0;
        int aces = 0;
        for (Card card : this) {
            if (card.getRank() == Rank.ACE) {
                aces += 1;
                // for every ace at least add one to the result
                result += 1;
            } else if (card.getRank() == Rank.KING || card.getRank() == Rank.QUEEN || card.getRank() == Rank.JACK) {
                result += 10;
            } else {
                result += card.getRank().ordinal() + 1;
            }
        }
        // max 1 ace can be counted as 11 points
        // might be changed for different set of rules
        if (aces > 0 && result <= 11) {
            return new int[]{result, result + 10};
        }
        return new int[]{result};
    }

    /**
     * Compares two hands, they are equal, if they contain the same cards - uses Sets.
     *
     * @param other other deck
     * @return boolean - true if hands are equal to each other
     */
    public boolean equals(Hand other) {
        return new HashSet<>(this.aCards).equals(new HashSet<>(other.aCards));
    }
}
