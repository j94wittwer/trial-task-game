package model;

import exception.CardSourceEmptyException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void size() {
        assertEquals(52, deck.size());
    }

    @Test
    void getCards() {
        List<Card> cardList = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(rank, suit);
                cardList.add(card);
            }
        }
        List<Card> deckCards = deck.getCards();
        for (int i = 0; i < deckCards.size(); i++) {
            if (!cardList.get(i).equals(deckCards.get(i))) {
                fail();
            }
        }
        assertTrue(true);
    }

    @Test
    void sort() {
        try {
            deck.drawCards(42);
        } catch (CardSourceEmptyException e) {
            fail();
        }
        deck.shuffle();
        deck.sort();
        List<Card> deckCards = deck.getCards();
        for (int i = 0; i < deckCards.size() - 1; i++) {
            if (deckCards.get(i).getRank().ordinal() < deckCards.get(i + 1).getRank().ordinal()) {
                fail();
            }
        }
        // if we reach this, test is successful
        assertTrue(true);
    }

    @Test
    void shuffle() {
        // There is a slight chance that after shuffling, the deck remains the same (but very low)
        Deck otherDeck = new Deck();
        deck.shuffle();
        assertFalse(deck.equals(otherDeck));
    }

    @Test
    void draw() {
        deck.sort();
        Card card = null;
        try {
            card = deck.draw();
        } catch (CardSourceEmptyException e) {
            fail();
        }
        Card otherCard = new Card(Rank.KING, Suit.HEARTS);
        assertTrue(otherCard.equals(card));
    }

    @Test
    void isEmpty() {
        try {
            deck.drawCards(52);
        } catch (CardSourceEmptyException e) {
            fail();
        }
        assertTrue(deck.isEmpty());
    }

    @Test
    void drawCards() {
        Deck compareDeck = new Deck();
        Deck drawnCards = null;
        try {
            drawnCards = new Deck(deck.drawCards(52));
        } catch (CardSourceEmptyException e) {
            fail();
        }
        drawnCards.sort();
        compareDeck.sort();
        assertTrue(compareDeck.equals(drawnCards));
    }

    @Test
    void testEquals() {
        Deck compareDeck = new Deck();
        assertTrue(compareDeck.equals(deck));
    }

    @Test
    void testNotEquals() {
        Deck compareDeck = new Deck();
        try {
            compareDeck.draw();
        } catch (CardSourceEmptyException e) {
            fail();
        }
        assertFalse(compareDeck.equals(deck));
    }
}