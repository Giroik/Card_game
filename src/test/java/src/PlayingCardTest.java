package src;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayingCardTest {

    PlayingCard card;
    @Before
    public void setUp() {
        card = new PlayingCard("H", "A", 1);
    }

    @After
    public void tearDown() {
        card = null;
    }

    @Test
    public void getIntValue() {
        assertEquals(1, card.getIntValue());
    }

    @Test
    public void getAsString() {
        assertEquals("AH", card.getAsString());
    }

    @Test
    public void getSuit() {
        assertEquals("H", card.getSuit());
    }

    @Test
    public void getFace() {
        assertEquals("A", card.getFace());
    }

    @Test
    public void getImgPath() {
        assertEquals("src/main/java/src/cards/AH.png", card.getImgPath());
    }
}