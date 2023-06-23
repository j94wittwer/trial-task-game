package model;


import exception.CardSourceEmptyException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class CardSource implements Iterable<Card> {
    protected final ArrayList<Card> aCards;

    CardSource() {
        aCards = new ArrayList<>();
    }

    /**
     * Returns an iterator over elements of type Card.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Card> iterator() {
        return aCards.iterator();
    }

    /**
     * @return int: size of the card source.
     */
    public int size() {
        return aCards.size();
    }

    /**
     * @return List<Card>: a copy of the arraylist of the cards (cards are immutable).
     */
    public ArrayList<Card> getCards() {
        return new ArrayList<>(aCards);
    }

    /**
     * @return Card: the first card of a CardSource (mainly used for hand - could be used for decks).
     */
    public Card showFirstCard() {
        return this.getCards().get(0);
    }

    /**
     * @return Card: the topmost card of the deck
     */
    public Card draw() throws CardSourceEmptyException {
        if (!isEmpty()) {
            return aCards.remove(0);
        } else {
            throw new CardSourceEmptyException();
        }
    }

    /**
     * Adds a card to the CardSource
     *
     * @param pCard: Card
     */
    public void add(Card pCard) {
        this.aCards.add(pCard);
    }

    /**
     * Adds multiple cards to the CardSource
     *
     * @param pCards List of Cards
     */
    public void add(List<Card> pCards) {
        for (Card card : pCards) {
            this.add(card);
        }
    }

    /**
     * sorts the Cards according to the comparable method in Card
     */
    public void sort() {
        Collections.sort(aCards);
    }

    /**
     * Randomly shuffles the cards
     */
    public void shuffle() {
        Collections.shuffle(aCards);
    }

    /**
     * @return boolean: true if the deck is empty, false otherwise
     */
    public boolean isEmpty() {
        return aCards.isEmpty();
    }

    /**
     * @param pNumber: int, how many cards to draw
     * @return List<Card>: a new List containing the first pNumber cards of the deck.
     */
    public List<Card> drawCards(int pNumber) throws CardSourceEmptyException {
        List<Card> result = new ArrayList<>();
        for (int i = 0; i < pNumber; i++) {
            result.add(this.draw());
        }
        return result;
    }

    /**
     * Compares two CardSources - subclasses can implement this method (but must if someone wants to call this on such a subclass)
     *
     * @return boolean: true if CardSources are equal to each other
     */
    public boolean equals() {
        throw new UnsupportedOperationException();
    }

}
