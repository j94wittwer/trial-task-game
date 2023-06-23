package model;

import exception.CardSourceEmptyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// since Person is abstract, we test AnonymousPerson which is not abstract
class AnonymousPerson extends Person {
}

class PersonTest {
    AnonymousPerson person = new AnonymousPerson();

    @Test
    void addCardToHand() {
        Card card = new Card(Rank.FIVE, Suit.DIAMOND);
        person.addCardToHand(card);
        try {
            assertTrue(person.hand.draw().equals(card));
        } catch (CardSourceEmptyException e) {
            fail();
        }
    }

    @Test
    void displayCards() {
        person.addCardToHand(new Card(Rank.FIVE, Suit.DIAMOND));
        assertEquals("diamond_five    ", person.displayCards());
        assertEquals("diamond_five    ", person.displayCards(true));
        assertEquals("diamond_five    ", person.displayCards(false));
        person.addCardToHand(new Card(Rank.KING, Suit.HEARTS));
        assertEquals("diamond_five    hearts_king     ", person.displayCards());
        assertEquals("diamond_five    hearts_king     ", person.displayCards(true));
        assertEquals("diamond_five    #############  ", person.displayCards(false));
        person.addCardToHand(new Card(Rank.TWO, Suit.CLUBS));
        assertEquals("diamond_five    hearts_king     clubs_two       ", person.displayCards());
    }

    @Test
    void getHandValue() {
        assertEquals(person.hand.getValue(), person.getHandValue());  // hand.getValue tested in hand
        person.addCardToHand(new Card(Rank.ACE, Suit.HEARTS));
        person.addCardToHand(new Card(Rank.KING, Suit.SPADES));
        assertEquals(person.hand.getValue(), person.getHandValue());
    }
}