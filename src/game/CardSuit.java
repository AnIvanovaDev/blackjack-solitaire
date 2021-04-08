package game;

/**
 * An enumeration of card suits.
 *
 * @author Anastasiia Ivanova
 */
public enum CardSuit {
    HEARTS("H"), SPADES("S"), DIAMONDS("D"), CLUBS("C");

    private String shortName;

    CardSuit(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}
