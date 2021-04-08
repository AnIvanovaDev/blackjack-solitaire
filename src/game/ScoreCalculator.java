package game;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class that encapsulates the logic of score calculation.
 *
 * @author Anastasiia Ivanova
 */
public final class ScoreCalculator {

    /**
     * The best value for card combination.
     */
    public static final int BEST_VALUE = 21;
    /**
     * The value which needs to be added for extra {@link CardRank#ACE} value.
     */
    public static final int ADDITIONAL_ACE_VALUE = 10;
    /**
     * The value for Blackjack combination. It will be assigned for line of two cards if value result is 21.
     */
    public static final int BLACKJACK_VALUE = -21;
    /**
     * Score table represents the accordance of cards combination value and score for it, that defines in rules of the
     * game.
     */
    private static final Map<Integer, Integer> SCORE_TABLE = new HashMap<>();
    private static final String ILLEGAL_INPUT_VALUE_OF_CALCULATED_CARDS = "Illegal input value of calculated cards.";
    private static final int MINIMUM_CARDS_CALCULATED_VALUE = 4;
    private static final int MINIMUM_DEFINED_CALCULATED_VALUE = 16;
    private static final int MINIMUM_SCORE = 1;
    private static final int BUST = 0;

    static {
        SCORE_TABLE.put(BLACKJACK_VALUE, 10);
        SCORE_TABLE.put(BEST_VALUE, 7);
        SCORE_TABLE.put(20, 5);
        SCORE_TABLE.put(19, 4);
        SCORE_TABLE.put(18, 3);
        SCORE_TABLE.put(17, 2);
        SCORE_TABLE.put(MINIMUM_DEFINED_CALCULATED_VALUE, MINIMUM_SCORE);
    }

    private ScoreCalculator() {
    }

    /**
     * The method, which calculates the whole score of the game.
     *
     * @param workingCells cards that have been placed on the gambling table.
     * @return the whole score of the game.
     */
    public static int calculate(Card[] workingCells) {
        if (workingCells == null) {
            return 0;
        }
        int totalScore = 0;
        //1st line: indexes->0,1,2,3,4
        totalScore += convertValueToScore(
                calculateLine(workingCells[0], workingCells[1], workingCells[2], workingCells[3], workingCells[4]));
        //2nd line: indexes-> 5,6,7,8,9
        totalScore += convertValueToScore(
                calculateLine(workingCells[5], workingCells[6], workingCells[7], workingCells[8], workingCells[9]));
        //3rd line: indexes->10,11,12
        totalScore += convertValueToScore(calculateLine(workingCells[10], workingCells[11], workingCells[12]));
        //4th line: indexes->13,14,15
        totalScore += convertValueToScore(calculateLine(workingCells[13], workingCells[14], workingCells[15]));
        //1st column: indexes-> 0,5
        totalScore += convertValueToScore(calculateColumnOfTwo(workingCells[0], workingCells[5]));
        //2nd column: indexes-> 1,6,10,13
        totalScore += convertValueToScore(
                calculateLine(workingCells[1], workingCells[6], workingCells[10], workingCells[13]));
        //3rd column: indexes-> 2,7,11,14
        totalScore += convertValueToScore(
                calculateLine(workingCells[2], workingCells[7], workingCells[11], workingCells[14]));
        //4th column: indexes-> 3,8,12,15
        totalScore += convertValueToScore(
                calculateLine(workingCells[3], workingCells[8], workingCells[12], workingCells[15]));
        //5th column: indexes-> 4,9
        totalScore += convertValueToScore(calculateColumnOfTwo(workingCells[4], workingCells[9]));
        return totalScore;
    }

    /**
     * Calculates the sum of cards value in one line.
     *
     * @param cards line of cards, the sum of which will be calculated.
     * @return the sum of cards value.
     */
    public static int calculateLine(Card... cards) {
        int sum = 0;
        boolean isAce = false;
        for (Card card : cards) {
            if (card.getCardRank() == CardRank.ACE) {
                isAce = true;
            }
            sum += card.getCardValue();
        }

        if (isAce && sum + ADDITIONAL_ACE_VALUE <= BEST_VALUE) {
            return sum + ADDITIONAL_ACE_VALUE;
        } else {
            return sum;
        }
    }

    /**
     * Calculates the sum of cards value for line of two elements.
     *
     * @param top    the card, which is on the top of the column
     * @param bottom the card which is on the bottom of the column
     * @return the sum of cards value or Blackjack cards value if sum is 21.
     */
    public static int calculateColumnOfTwo(Card top, Card bottom) {
        int sum = top.getCardValue() + bottom.getCardValue();
        int additional_sum = sum;

        if (top.getCardRank() == CardRank.ACE || bottom.getCardRank() == CardRank.ACE) {
            additional_sum += ADDITIONAL_ACE_VALUE;
        }
        if (sum == BEST_VALUE || additional_sum == BEST_VALUE) {
            return BLACKJACK_VALUE;
        } else {
            return additional_sum;
        }
    }

    /**
     * Takes the score from score table, which is accorded to cards value.
     *
     * @param value the cards value of the line.
     * @return the calculated score of line of cards.
     * @throws IllegalStateException from the rules minimum value of calculated cards is 4. If sum of cards value is
     *                               less then 4 the exception will be thrown.
     */
    public static int convertValueToScore(int value) {
        return SCORE_TABLE.computeIfAbsent(value, key -> {
            if (key >= MINIMUM_CARDS_CALCULATED_VALUE && key < MINIMUM_DEFINED_CALCULATED_VALUE) {
                return MINIMUM_SCORE;
            } else if (key > BEST_VALUE) {
                return BUST;
            } else {
                throw new IllegalStateException(ILLEGAL_INPUT_VALUE_OF_CALCULATED_CARDS);
            }
        });
    }
}
