package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * This class represents a deck of cards.
 * It contains a list of cards and methods for dealing hands and getting the deck.
 *
 * @version 1.0
 * @since 3.3.2024
 */
public class DeckOfCards {
    // The deck of cards
    private final ArrayList<PlayingCard> deck= new ArrayList<>(52);
    // The suits of the cards
    private final String[] suits = {"H", "D", "C", "S"};
    // Ace is represented by the number 1
    // The faces of the cards
    private final String[] faces = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
    // The values of the cards
    private final int[] values = {1,2,3,4,5,6,7,8,9,10,11,12,13};
    // The list of cards that are the only ones that can make a flash in a deck of 5 cards (all the same suit)
    private final List<PlayingCard> ONLY_FLASH_LIST = new ArrayList<>(5);

    /**
     * Constructor for the deck of cards.
     * It creates a deck of 52 cards, one of each suit and face.
     *
     * @since 1.0
     */
    public DeckOfCards()
    {
        for (String suit : suits)
        {
            for (int intValue : values)
            {
                deck.add( new PlayingCard(suit,  faces[intValue-1], intValue));
            }
        }
        setFlashList();
    }

    /**
     * Sets the list of cards that are the only ones that can make a flash in a deck of 5 cards (all the same suit)
     *
     * @since 1.0
     */
    private void setFlashList()
    {
        PlayingCard card0 = new PlayingCard("H", "A", 1);
        PlayingCard card1 = new PlayingCard("H", "K", 13);
        PlayingCard card2 = new PlayingCard("H", "Q", 12);
        PlayingCard card3 = new PlayingCard("H", "J", 11);
        PlayingCard card4 = new PlayingCard("H", "T", 10);

        ONLY_FLASH_LIST.add(card0);
        ONLY_FLASH_LIST.add(card1);
        ONLY_FLASH_LIST.add(card2);
        ONLY_FLASH_LIST.add(card3);
        ONLY_FLASH_LIST.add(card4);
    }
    /**
     * Returns the list of cards that are the only ones that can make a flash in a deck of 5 cards (all the same suit)
     *
     * @return List of flash cards
     */
    public List<PlayingCard> getOnlyFlash()
    {
        return ONLY_FLASH_LIST;
    }
    /**
     * Deals a hand of cards from the deck.
     * It removes the cards from the deck and returns them as a stream.
     *
     * @param antalKort The number of cards to deal
     * @return A stream of cards
     * @throws IllegalArgumentException if antalKort is less than 0 or more than 52
     * @throws IllegalStateException if there are not enough cards in the deck
     * @since 1.0
     */
    public Stream<PlayingCard> dealHand(int antalKort)
    {
        if (antalKort<0 || antalKort>52)
        {
            throw new IllegalArgumentException("antalKort must be between 0 and 52");
        }
        if (deck.size()<antalKort)
        {
            throw new IllegalStateException("Not enough cards in the deck");
        }

        Random rand = new Random();
        ArrayList<PlayingCard> hand = new ArrayList<>();

        while (hand.size()<antalKort)
        {
            int index = rand.nextInt(deck.size());

            hand.add(deck.get(index));
            deck.remove(deck.get(index));
        }
        return hand.stream();
    }
    /**
     * Returns the deck of cards as a stream.
     *
     * @return The deck of cards as a stream
     * @since 1.0
     */
    public Stream<PlayingCard> getDeck() {
        return deck.stream();
    }
}
