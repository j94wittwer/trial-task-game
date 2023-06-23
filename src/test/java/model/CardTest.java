package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    Card card;

    @BeforeEach
    void setUp() {
        card = new Card(Rank.FIVE, Suit.CLUBS);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRank() {
        assertEquals(Rank.FIVE, card.getRank());
    }

    @Test
    void getSuit() {
        assertEquals(Suit.CLUBS, card.getSuit());
    }

    @Test
    void compareToLess() {
        Card otherCard = new Card(Rank.FOUR, Suit.CLUBS);
        int actual = card.compareTo(otherCard);
        assertTrue(actual < 0, "clubs_four's value should be less than clubs_five's, but isn't: " + actual + " should be < 0.");
    }

    @Test
    void compareToEqual() {
        Card otherCard = new Card(Rank.FIVE, Suit.CLUBS);
        int actual = card.compareTo(otherCard);
        assertEquals(0, actual, "The value of clubs_five should be equal to clubs_five, but isn't: " + actual);
    }

    @Test
    void compareToGreater() {
        Card otherCard = new Card(Rank.SIX, Suit.CLUBS);
        int actual = card.compareTo(otherCard);
        assertTrue(actual > 0, "clubs_six's value should be greater that clubs_five's, but isn't: " + actual + " should be > 0.");
    }

    @Test
    void testEquals() {
        Card otherCard = new Card(Rank.FIVE, Suit.CLUBS);
        boolean actual = card.equals(otherCard);
        assertTrue(actual, "clubs_five should equal to clubs_five, but doesn't.");
    }

    @Test
    void testNotEquals() {
        Card otherCard = new Card(Rank.SIX, Suit.CLUBS);
        boolean actual = card.equals(otherCard);
        assertFalse(actual, "clubs_six should not equal to clubs_five, but does.");
    }
}