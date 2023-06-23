package model;

import exception.CardSourceEmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    Hand hand;

    @BeforeEach
    void SetUp() {
        hand = new Hand();
    }

    @Test
    void getValue4Aces() {
        hand.add(new Card(Rank.ACE, Suit.HEARTS)); // adds 11
        hand.add(new Card(Rank.ACE, Suit.DIAMOND)); // adds 1
        hand.add(new Card(Rank.ACE, Suit.CLUBS)); // adds 1
        hand.add(new Card(Rank.ACE, Suit.SPADES)); // adds 1
        assertEquals(14, hand.getValue());
    }

    @Test
    void getValue20AndAce() {
        hand.add(new Card(Rank.KING, Suit.HEARTS)); // adds 10
        hand.add(new Card(Rank.KING, Suit.DIAMOND)); // adds 10
        hand.add(new Card(Rank.ACE, Suit.CLUBS)); // adds 1
        assertEquals(21, hand.getValue());
    }

    @Test
    void getValue20AndTwoAces() {
        hand.add(new Card(Rank.KING, Suit.HEARTS)); // adds 10
        hand.add(new Card(Rank.KING, Suit.DIAMOND)); // adds 10
        hand.add(new Card(Rank.ACE, Suit.CLUBS)); // adds 1
        hand.add(new Card(Rank.ACE, Suit.SPADES)); // adds 1
        assertEquals(22, hand.getValue());
    }

    @Test
    void getValue0AndTwoAces() {
        hand.add(new Card(Rank.NINE, Suit.HEARTS)); // adds 9
        hand.add(new Card(Rank.ACE, Suit.DIAMOND)); // adds 11
        hand.add(new Card(Rank.ACE, Suit.CLUBS)); // adds 1
        assertEquals(21, hand.getValue());
    }

    @Test
    void getValue21AndOneAce() {
        hand.add(new Card(Rank.NINE, Suit.HEARTS)); // adds 9
        hand.add(new Card(Rank.KING, Suit.DIAMOND)); // adds 10
        hand.add(new Card(Rank.TWO, Suit.CLUBS)); // adds 2
        hand.add(new Card(Rank.ACE, Suit.SPADES)); // adds 1
        assertEquals(22, hand.getValue());
    }

    @Test
    void getValueOneAce() {
        hand.add(new Card(Rank.ACE, Suit.HEARTS)); // adds 11
        assertEquals(11, hand.getValue());
    }

    @Test
    void getValueKingAndAce() {
        hand.add(new Card(Rank.KING, Suit.CLUBS));
        hand.add(new Card(Rank.ACE, Suit.CLUBS));
        int expected = 21;
        int actual = hand.getValue();
        assertEquals(expected, actual);
    }

    @Test
    void getValueTwoAces() {
        hand.add(new Card(Rank.ACE, Suit.HEARTS)); // adds 11
        hand.add(new Card(Rank.ACE, Suit.SPADES)); // adds 1
        assertEquals(12, hand.getValue());
    }

    @Test
    void getAllValues4Aces() {
        hand.add(new Card(Rank.ACE, Suit.HEARTS)); // adds 11
        hand.add(new Card(Rank.ACE, Suit.DIAMOND)); // adds 1
        hand.add(new Card(Rank.ACE, Suit.CLUBS)); // adds 1
        hand.add(new Card(Rank.ACE, Suit.SPADES)); // adds 1
        int[] expected = {4, 14};
        int[] actual = hand.getAllValues();
        assertArrayEquals(expected, actual, "For four aces, expected " + Arrays.toString(expected) + ", but got " + Arrays.toString(actual));
    }

    @Test
    void getAllValues20AndAce() {
        hand.add(new Card(Rank.KING, Suit.HEARTS)); // adds 10
        hand.add(new Card(Rank.KING, Suit.DIAMOND)); // adds 10
        hand.add(new Card(Rank.ACE, Suit.CLUBS)); // adds 1
        int[] expected = {21};
        int[] actual = hand.getAllValues();
        assertArrayEquals(expected, actual, "For two kings and one ace, expected " + Arrays.toString(expected) + ", but got " + Arrays.toString(actual));

    }

    @Test
    void getAllValues20AndTwoAces() {
        hand.add(new Card(Rank.KING, Suit.HEARTS)); // adds 10
        hand.add(new Card(Rank.KING, Suit.DIAMOND)); // adds 10
        hand.add(new Card(Rank.ACE, Suit.CLUBS)); // adds 1
        hand.add(new Card(Rank.ACE, Suit.SPADES)); // adds 1
        int[] expected = {22};
        int[] actual = hand.getAllValues();
        assertArrayEquals(expected, actual, "For two kings and two aces, expected " + Arrays.toString(expected) + ", but got " + Arrays.toString(actual));
    }

    @Test
    void getAllValues0AndTwoAces() {
        hand.add(new Card(Rank.NINE, Suit.HEARTS)); // adds 9
        hand.add(new Card(Rank.ACE, Suit.DIAMOND)); // adds 11
        hand.add(new Card(Rank.ACE, Suit.CLUBS)); // adds 1
        int[] expected = {11, 21};
        int[] actual = hand.getAllValues();
        assertArrayEquals(expected, actual, "For a nine and two aces, expected " + Arrays.toString(expected) + ", but got " + Arrays.toString(actual));
        assertEquals(21, hand.getValue());
    }

    @Test
    void getAllValues21AndOneAce() {
        hand.add(new Card(Rank.NINE, Suit.HEARTS)); // adds 9
        hand.add(new Card(Rank.KING, Suit.DIAMOND)); // adds 10
        hand.add(new Card(Rank.TWO, Suit.CLUBS)); // adds 2
        hand.add(new Card(Rank.ACE, Suit.SPADES)); // adds 1
        int[] expected = {22};
        int[] actual = hand.getAllValues();
        assertArrayEquals(expected, actual, "For 21 + one ace, expected " + Arrays.toString(expected) + ", but got " + Arrays.toString(actual));
    }

    @Test
    void getAllValuesOneAce() {
        hand.add(new Card(Rank.ACE, Suit.HEARTS)); // adds 11
        int[] expected = {1, 11};
        int[] actual = hand.getAllValues();
        assertArrayEquals(expected, actual, "For one ace, expected " + Arrays.toString(expected) + ", but got " + Arrays.toString(actual));
    }

    @Test
    void getAllValuesKingAndAce() {
        hand.add(new Card(Rank.KING, Suit.CLUBS));
        hand.add(new Card(Rank.ACE, Suit.CLUBS));
        int[] expected = {11, 21};
        int[] actual = hand.getAllValues();
        assertArrayEquals(expected, actual);
    }

    @Test
    void getAllValuesTwoAces() {
        hand.add(new Card(Rank.ACE, Suit.HEARTS)); // adds 11
        hand.add(new Card(Rank.ACE, Suit.SPADES)); // adds 1
        int[] expected = {2, 12};
        int[] actual = hand.getAllValues();
        assertArrayEquals(expected, actual, "For two aces, expected " + Arrays.toString(expected) + ", but got " + Arrays.toString(actual));
    }

    @Test
    void testAddList() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.FIVE, Suit.DIAMOND));
        cards.add(new Card(Rank.KING, Suit.CLUBS));
        hand.add(cards);
        assertEquals(15, hand.getValue());
    }

    @Test
    void testEqual() {
        Deck tmp = new Deck();
        tmp.shuffle();
        Hand other = new Hand(tmp.getCards());
        tmp.shuffle();
        hand.add(tmp.getCards());
        assertTrue(hand.equals(other));
    }

    @Test
    void testNotEqualDifferentSize() {
        Deck tmp = new Deck();
        tmp.shuffle();
        Hand other = new Hand(tmp.getCards());
        tmp.shuffle();
        hand.add(tmp.getCards());
        try {
            hand.draw();
        } catch (CardSourceEmptyException e) {
            fail();
        }
        assertFalse(hand.equals(other));
    }

    @Test
    void testNotEqualSameSize() {
        hand.add(new Card(Rank.KING, Suit.SPADES));
        hand.add(new Card(Rank.ACE, Suit.HEARTS));
        Hand other = new Hand();
        other.add(new Card(Rank.JACK, Suit.HEARTS));
        other.add(new Card(Rank.KING, Suit.SPADES));

        assertFalse(hand.equals(other));
    }
}