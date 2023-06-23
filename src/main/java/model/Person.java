package model;

import java.util.Locale;

public abstract class Person {
    protected final Hand hand;

    Person() {
        // every Person has a hand
        hand = new Hand();
    }

    /**
     * Adds a card to the person's hand
     *
     * @param card: Card
     */
    public void addCardToHand(Card card) {
        this.hand.add(card);
    }

    /**
     * @return String: a String to display all cards
     */
    public String displayCards() {
        StringBuilder res = new StringBuilder();
        for (Card card : hand) {
            appendToString(res, card);
        }
        return res.toString();
    }

    /**
     * @param showAllCards: boolean, if true, the string contains all cards, if false, the string only contains the
     *                      first card and pads the other ones
     * @return String: a String to display all cards.
     */
    public String displayCards(boolean showAllCards) {
        if (showAllCards) {
            // simply call displayCards method
            return displayCards();
        }
        // hand.sort();
        StringBuilder res = new StringBuilder();
        // show first card
        Card card = hand.showFirstCard();
        appendToString(res, card);

        // hide the rest of the cards
        for (int i = 1; i < hand.getCards().size(); i++) {
            // longest card string takes 13 chars (diamonds_seven)
            res.append("#".repeat(13));
            res.append("  ");
        }
        return res.toString();
    }

    /**
     * Appends the param card as a string to the StringBuilder res
     *
     * @param stringBuilder: StringBuilder
     * @param card:          Card
     */
    private void appendToString(StringBuilder stringBuilder, Card card) {
        stringBuilder.append(card.getSuit().toString().toLowerCase(Locale.ROOT));
        stringBuilder.append("_");
        stringBuilder.append(card.getRank().toString().toLowerCase(Locale.ROOT));
        // Padding to length 13
        stringBuilder.append(" ".repeat(13 - (card.getRank().toString().length() + card.getSuit().toString().length())));
        stringBuilder.append("  ");
    }

    /**
     * @return integer: the value of the person's hand
     */
    public int getHandValue() {
        return this.hand.getValue();
    }
}
