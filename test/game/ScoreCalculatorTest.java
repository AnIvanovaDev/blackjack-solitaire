package game;

import org.junit.Assert;
import org.junit.Test;

import static game.CardRank.*;
import static game.CardSuit.CLUBS;
import static game.CardSuit.DIAMONDS;
import static game.CardSuit.HEARTS;
import static game.CardSuit.SPADES;

/**
 * @author Anastasiia Ivanova
 * @since 1.0.0
 */
public class ScoreCalculatorTest {

    @Test
    public void blackjackLineCalculationPositiveScenarioTest() {
        int actualValue = ScoreCalculator
                .calculateColumnOfTwo(Card.of(ACE, CLUBS), Card.of(TEN, CLUBS));
        int expectedValue = ScoreCalculator.BLACKJACK_VALUE;
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void lessThenBlackjackWithAceLineCalculationScenarioTest() {
        int actualValue = ScoreCalculator
                .calculateColumnOfTwo(Card.of(ACE, CLUBS), Card.of(NINE, CLUBS));
        int expectedValue = 20;
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void lessThenBlackjackLineCalculationScenarioTest() {
        int actualValue = ScoreCalculator
                .calculateColumnOfTwo(Card.of(FIVE, CLUBS), Card.of(NINE, CLUBS));
        int expectedValue = 14;
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void scoreCalculationTest() {
        Card[] cards = new Card[] {
                Card.of(JACK, SPADES),
                Card.of(NINE, HEARTS),
                Card.of(THREE, CLUBS),
                Card.of(FIVE, DIAMONDS),
                Card.of(JACK, CLUBS),
                Card.of(ACE, HEARTS),
                Card.of(NINE, CLUBS),
                Card.of(FOUR, CLUBS),
                Card.of(SIX, HEARTS),
                Card.of(SIX, CLUBS),
                Card.of(THREE, DIAMONDS),
                Card.of(EIGHT, SPADES),
                Card.of(EIGHT, CLUBS),
                Card.of(TWO, DIAMONDS),
                Card.of(THREE, HEARTS),
                Card.of(ACE, CLUBS),
        };
        int actualValue = ScoreCalculator.calculate(cards);
        int expectedValue = 24;
        Assert.assertEquals(expectedValue,actualValue);
    }

    @Test
    public void scoreCalculationTest2() {
        Card[] cards = new Card[] {
                Card.of(TEN, SPADES),
                Card.of(SEVEN, CLUBS),
                Card.of(SEVEN, SPADES),
                Card.of(SIX, CLUBS),
                Card.of(JACK, CLUBS),
                Card.of(ACE, CLUBS),
                Card.of(NINE, CLUBS),
                Card.of(FOUR, SPADES),
                Card.of(TWO, SPADES),
                Card.of(QUEEN, SPADES),
                Card.of(KING, HEARTS),
                Card.of(KING, SPADES),
                Card.of(FOUR, CLUBS),
                Card.of(FIVE, SPADES),
                Card.of(FIVE, DIAMONDS),
                Card.of(NINE, HEARTS),
        };
        int actualValue = ScoreCalculator.calculate(cards);
        int expectedValue = 26;
        Assert.assertEquals(expectedValue,actualValue);
    }
}
