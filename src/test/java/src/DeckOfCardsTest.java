package src;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeckOfCardsTest {
    DeckOfCards deck;
    @Before
    public void setUp() throws Exception {
        deck= new DeckOfCards();
    }

    @After
    public void tearDown() throws Exception {
        deck=null;
    }

    @Test
    public void dealHand() {
        List<PlayingCard> hand = deck.dealHand(5).toList();
        assertEquals(5, hand.size());

    }

     @Test
    public void getDeck() {
        List<PlayingCard> deckOfCards = deck.getDeck().toList();
        assertEquals(52, deckOfCards.size());
    }


}