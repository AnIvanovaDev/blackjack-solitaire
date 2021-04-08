package game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/**
 * The class represents the deck of cards.
 *
 * @author Anastasiia Ivanova
 */
public final class Deck {

    private static final String EMPTY_DECK_MESSAGE = "The deck is empty.";
    private static Deck instance;
    private Queue<Card> cards;

    private Deck() {
        List<Card> cardList = new ArrayList<>();
        for (CardRank rank : CardRank.values()) {
            for (CardSuit suit : CardSuit.values()) {
                cardList.add(Card.of(rank, suit));
            }
        }
        Collections.shuffle(cardList);
        cards = new ArrayDeque<>(cardList);
    }

    /**
     * Returns instance of the deck.
     *
     * @return the deck.
     */
    public static Deck getInstance() {
        if (instance == null) {
            return new Deck();
        } else {
            return instance;
        }
    }

    /**
     * Returns next card from the deck.
     *
     * @return next card
     * @throws IllegalStateException if the deck is empty.
     */
    public Card nextCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(EMPTY_DECK_MESSAGE);
        }
        return cards.poll();
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
