package model;

import exception.CardSourceEmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// because CardSource is abstract, have another class that is not
class AnonymousCardSource extends CardSource {
}

class CardSourceTest {
    List<Card> cards;
    AnonymousCardSource cardSource;

    @BeforeEach
    void setUp() {
        cards = new ArrayList<>();
        cardSource = new AnonymousCardSource();
        cardSource.add(new Card(Rank.KING, Suit.CLUBS));
        cardSource.add(new Card(Rank.QUEEN, Suit.HEARTS));
    }

    @Test
    void size() {
        assertEquals(2, cardSource.size());
    }

    @Test
    void getCards() {
        cards.add(new Card(Rank.KING, Suit.CLUBS));
        cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
        assertTrue(cards.get(0).equals(cardSource.getCards().get(0)));
        assertTrue(cards.get(1).equals(cardSource.getCards().get(1)));
        assertFalse(cards.get(0).equals(cardSource.getCards().get(1)));
        assertFalse(cards.get(1).equals(cardSource.getCards().get(0)));
    }

    @Test
    void draw() {
        // deck is like a queue with (add = enqueue and draw = dequeue)
        try {
            assertTrue(cardSource.draw().equals(new Card(Rank.KING, Suit.CLUBS)));
        } catch (CardSourceEmptyException e) {
            fail();
        }
        try {
            assertTrue(cardSource.draw().equals(new Card(Rank.QUEEN, Suit.HEARTS)));
        } catch (CardSourceEmptyException e) {
            fail();
        }
        assertThrows(Exception.class, () -> cardSource.draw());
    }

    @Test
    void addMultiple() {
        cards.add(new Card(Rank.KING, Suit.CLUBS));
        cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Rank.FIVE, Suit.DIAMOND));
        cardList.add(new Card(Rank.SIX, Suit.DIAMOND));

        cardSource.add(cardList);
        cards.addAll(cardList);

        assertEquals(cards.size(), cardSource.size());
        for (int i = 0; i < cards.size(); i++) {
            assertTrue(cardSource.getCards().get(i).equals(cards.get(i)));
        }
    }

    @Test
    void add() {
        cards.add(new Card(Rank.KING, Suit.CLUBS));
        cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
        cards.add(new Card(Rank.FIVE, Suit.DIAMOND));
        cardSource.add(new Card(Rank.FIVE, Suit.DIAMOND));
        assertEquals(cards.size(), cardSource.size());
        for (int i = 0; i < cards.size(); i++) {
            assertTrue(cardSource.getCards().get(i).equals(cards.get(i)));
        }
    }

    @Test
    void sort() {
        // does not need to be tested as it just calls a method from Collections
    }

    @Test
    void shuffle() {
        // does not need to be tested as it just calls a method from Collections
    }

    @Test
    void isEmpty() {
        assertFalse(cardSource.isEmpty());
        cardSource = new AnonymousCardSource();
        assertTrue(cardSource.isEmpty());
    }

    @Test
    void drawCards() {
        try {
            cards.add(new Card(Rank.KING, Suit.CLUBS));
            cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
            List<Card> drawnCards = cardSource.drawCards(2);
            assert drawnCards.size() == cards.size();
            for (int i = 0; i < cards.size(); i++) {
                assertTrue(cards.get(i).equals(drawnCards.get(i)));
            }
        } catch (CardSourceEmptyException e) {
            fail();
        }
    }

    @Test
    void iterator() {
        Iterator<Card> fromCardSource = cardSource.iterator();
        Iterator<Card> fromCards = cardSource.aCards.iterator();
        while (fromCardSource.hasNext()) {
            Card card = fromCardSource.next();
            Card card1 = fromCards.next();
            assertEquals(card, card1);
        }
    }

    @Test
    void showFirstCard() {
        try {
            assertTrue(cardSource.showFirstCard().equals(cardSource.draw()));
        } catch (CardSourceEmptyException e) {
            fail();
        }
    }

    @Test
    void testEquals() {
        assertThrows(UnsupportedOperationException.class, () -> cardSource.equals());
    }
}