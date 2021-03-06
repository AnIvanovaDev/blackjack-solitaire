package game;

/**
 * An enumeration of card rank.
 *
 * @author Anastasiia Ivanova
 */
public enum CardRank {
    ACE("A", 1), TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5), SIX("6", 6), SEVEN("7", 7), EIGHT("8", 8),
    NINE("9", 9), TEN("10", 10), JACK("J", 10), QUEEN("Q", 10), KING("K", 10);

    private String shortName;
    private int value;

    CardRank(String shortName, int value) {
        this.shortName = shortName;
        this.value = value;
    }

    public String getShortName() {
        return this.shortName;
    }

    public int getValue() {
        return this.value;
    }
}
