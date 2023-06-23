package model;

import exception.CardSourceEmptyException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class DealerTest {
    Dealer aDealer;
    Dealer emptyDeckDealer;
    Deck emptyDeck;
    Field playerField;

    @BeforeEach
    void setUp() {
        aDealer = new Dealer();

        emptyDeckDealer = new Dealer();
        emptyDeck = new Deck();
        try {
            playerField = emptyDeckDealer.getClass().getDeclaredField("deck");
            playerField.setAccessible(true);
            emptyDeck.drawCards(emptyDeck.size());
            playerField.set(emptyDeckDealer, emptyDeck);
        } catch (NoSuchFieldException | CardSourceEmptyException | IllegalAccessException e) {
            e.printStackTrace();
            fail();
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void fillUpCards() {
        aDealer.fillUpCards();
        assertTrue(aDealer.hand.getValue() >= 17);
        emptyDeckDealer.fillUpCards();
        assertTrue(emptyDeckDealer.hand.getValue() >= 17);
    }
}