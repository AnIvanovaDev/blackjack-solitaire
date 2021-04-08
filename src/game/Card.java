package game;

/**
 * A class of single playing card, which contains card rank and card suit.
 *
 * @author Anastasiia Ivanova
 */
public final class Card {

    private final CardRank cardRank;
    private final CardSuit cardSuit;

    private Card(CardRank cardRank, CardSuit cardSuit) {
        this.cardRank = cardRank;
        this.cardSuit = cardSuit;
    }

    /**
     * Returns created card.
     *
     * @param cardRank the rank of card that will be created.
     * @param cardSuit the suit of card that will be created.
     * @return created card.
     */
    public static Card of(CardRank cardRank, CardSuit cardSuit) {
        return new Card(cardRank, cardSuit);
    }

    public CardRank getCardRank() {
        return this.cardRank;
    }

    public CardSuit getCardSuit() {
        return this.cardSuit;
    }

    public int getCardValue() {
        return this.cardRank.getValue();
    }

    @Override
    public String toString() {
        return this.cardRank.getShortName() + this.cardSuit.getShortName();
    }
}
