package src;

import java.util.List;

/**
 * This class is responsible for checking the hand of cards.
 *
 * @version 1.0
 * @since 3.3.2024
 */
public class HandCheck {
    static public int checkSumOfFace(List<PlayingCard> hand) {

        int sum = 0;

        for(PlayingCard card : hand) {
            sum += card.getIntValue();
        }
        return sum;
    }

    /**
     * Returns the cards of a specific suit in the hand.
     *
     * @param hand The hand of cards.
     * @param suit The suit of the cards.
     * @return The cards of the specific suit in the hand.
     * @throws IllegalArgumentException If the suit is not a string of length 1.
     * @since 1.0
     */
    static public String getSuits(List<PlayingCard> hand, String suit) throws IllegalArgumentException {
        List<PlayingCard> cards = hand.stream().filter(card -> card.getSuit().equals(suit)).toList();
        String result = "";

        for(PlayingCard card : cards) {

            result+= card.getAsString() + " ";
        }
        System.out.println(result);
        return result;
    }

    /**
     * Returns true if the hand has a specific card.
     * @param hand The hand of cards.
     * @param cardType The card to check for.
     * @return True if the hand has the card, false otherwise.
     * @throws IllegalArgumentException If the cardType is not a string of length 2.
     */
    static public boolean hasCard (List<PlayingCard> hand, String cardType) throws IllegalArgumentException{
        if (cardType.length() != 2)
        {
            throw new IllegalArgumentException("cardType must be a string of length 2");
        }

        return hand.stream().anyMatch(card -> card.getAsString().equals(cardType));
    }

    /**
     * Returns true if the hand has a flush.
     * @param hand The hand of cards.
     * @return True if the hand has a flush, false otherwise.
     * @throws IllegalArgumentException If the hand is null or empty.
     */
    static public boolean hasFlush(List<PlayingCard> hand) throws IllegalArgumentException{
        if (hand == null)
        {
            throw new IllegalArgumentException("hand must not be null");
        }
        if (hand.size() == 0)
        {
            throw new IllegalArgumentException("hand must not be empty");
        }

        List<String> hurts= hand.stream().filter(card -> card.getSuit().equals("H")).map(card -> card.getSuit()).toList();
        List<String> diamonds=hand.stream().filter(card -> card.getSuit().equals("D")).map(card -> card.getSuit()).toList();
        List<String> clubs= hand.stream().filter(card -> card.getSuit().equals("C")).map(card -> card.getSuit()).toList();
        List<String> spades= hand.stream().filter(card -> card.getSuit().equals("S")).map(card -> card.getSuit()).toList();

        return hurts.size() == 5 || diamonds.size() == 5 || clubs.size() == 5 || spades.size() == 5;

    }
}
