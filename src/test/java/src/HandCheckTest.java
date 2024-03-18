package src;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HandCheckTest {
    DeckOfCards deck;
    PlayingCard card1;
    @Before
    public void setUp() {
        deck = new DeckOfCards();

    }
    @After
    public void tearDown() {
        deck = null;
        card1 = null;
    }

    @Test
    public void checkSumOfFacePosetive() {
        List<PlayingCard> hand = deck.dealHand(5).toList();
        int sum = 0;
        for(PlayingCard card : hand) {
            sum += card.getIntValue();
        }
        assertEquals(sum, HandCheck.checkSumOfFace(hand), "Sum of face is not correct");
    }
    public void checkSumOfFaceNegative()
    {
        List<PlayingCard> hand = deck.dealHand(5).toList();
        int sum = 0;
        for(PlayingCard card : hand) {
            sum += card.getIntValue();
        }
        assertNotEquals(sum+1, HandCheck.checkSumOfFace(hand), "Sum of face is not correct");

    }

    @Test
    public void getSuits() {
        List<PlayingCard> hand = deck.dealHand(5).toList();
        String suit = "H";
        List<PlayingCard> cards = hand.stream().filter(card -> card.getSuit().equals(suit)).toList();
        String result = "";
        for(PlayingCard card : cards) {
            result+= card.getAsString() + " ";
        }
        assertEquals(result, HandCheck.getSuits(hand, suit), "Suits are not correct");
    }


    @Test
    public void hasFlush() {
        List<PlayingCard> hand = deck.getOnlyFlash();
        boolean hasFlush = true;
        if (hand == null) {
            throw new IllegalArgumentException("hand must not be null");
        }
        List<String> hurts = hand.stream().filter(card -> card.getSuit().equals("H")).map(card -> card.getSuit()).toList();
        List<String> diamonds = hand.stream().filter(card -> card.getSuit().equals("D")).map(card -> card.getSuit()).toList();
        List<String> clubs = hand.stream().filter(card -> card.getSuit().equals("C")).map(card -> card.getSuit()).toList();
        List<String> spades = hand.stream().filter(card -> card.getSuit().equals("S")).map(card -> card.getSuit()).toList();

        assertEquals(hasFlush, HandCheck.hasFlush(hand), "Flush is not correct");
    }
}