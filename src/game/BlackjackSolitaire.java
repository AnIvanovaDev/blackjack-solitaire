package game;

import java.io.IOException;
import java.util.Scanner;

/**
 * The model of Blackjack Game.
 *
 * @author Anastasiia Ivanova
 */
public class BlackjackSolitaire {

    private static final int WORKING_CELLS_CAPACITY = 20;
    private static final int FIRST_ROW_END_INDEX = 5;
    private static final int SECOND_ROW_END_INDEX = 10;
    private static final int THIRD_ROW_END_INDEX = 13;
    private static final int BIN_CAPACITY = 4;
    private static final int FIRST_BIN_INDEX = 16;
    private static final String INPUT_OUTPUT_ERROR_MESSAGE = "Something went wrong. Please restart the game.";
    private Card[] workingCells;
    private Deck deck;
    private int freeBinSpace;
    private int freeWorkingCells;

    public BlackjackSolitaire() {
        workingCells = new Card[WORKING_CELLS_CAPACITY];
        deck = Deck.getInstance();
        freeBinSpace = BIN_CAPACITY;
        freeWorkingCells = WORKING_CELLS_CAPACITY - BIN_CAPACITY;
    }

    /**
     * Entry point for the Game. Starts the game.
     */
    public void play() {
        drawTable();
        System.out.println("**************************");
        try (Scanner sc = new Scanner(System.in)) {
            while (freeWorkingCells > 0) {
                Card card = deck.nextCard();
                nextMove(sc, card);
                drawTable();
            }
        } catch (IOException ex) {
            System.out.println(INPUT_OUTPUT_ERROR_MESSAGE);
        }
        int result = ScoreCalculator.calculate(workingCells);
        System.out.println("**************************");
        System.out.printf("Your score is: %d%n", result);
    }

    private void binInfo() {
        System.out.printf("Free slots in bin left: %d%n", freeBinSpace);
    }

    private void drawTable() {
        for (int i = 0; i < workingCells.length - BIN_CAPACITY; i++) {
            if (i == FIRST_ROW_END_INDEX || i == SECOND_ROW_END_INDEX || i == THIRD_ROW_END_INDEX) {
                System.out.println();
            }
            if (i == SECOND_ROW_END_INDEX || i == THIRD_ROW_END_INDEX) {
                System.out.print(" \t");
            }
            if (workingCells[i] == null) {
                System.out.print(i + 1 + "\t");
            } else {
                System.out.print(workingCells[i] + "\t");
            }
        }
        System.out.println();
        binInfo();
    }

    private void nextMove(Scanner sc, Card card) throws IOException {
        System.out.printf("The next card is %s. Where do you want to place it?", card);
        String str = sc.nextLine();
        int workingCell = stringToIntConverter(str);
        if (checkWorkingCellIndex(workingCell)) {
            putCardToWorkingCell(workingCell, card);
        } else {
            System.out.println("Please choose an empty cell.");
            nextMove(sc, card);
        }
    }

    private int stringToIntConverter(String str) {
        try {
            return Integer.valueOf(str.trim());
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    /**
     * This method checks state of the cell of playing table.
     *
     * @param workingCell cell index, which was picked.
     * @return {@code true} if cell index is in the range from 1 to 20 and this cell isn't occupied by another card
     * otherwise {@code false}.
     */
    private boolean checkWorkingCellIndex(int workingCell) {
        int index = workingCell - 1;
        if (index < 0 || index >= workingCells.length) {
            System.out.println("The format of working cell should be a number from 1 to 20.");
            return false;
        }
        if (workingCells[index] != null) {
            if (index >= FIRST_BIN_INDEX && freeBinSpace <= 0) {
                System.out.println("The bin is already full.");
            } else {
                System.out.printf("The cell is already occupied by %s%n", workingCells[index]);
            }
            return false;
        }
        return true;
    }

    private void putCardToWorkingCell(int workingCell, Card card) {
        int index = workingCell - 1;
        workingCells[index] = card;
        if (index >= FIRST_BIN_INDEX) {
            --freeBinSpace;
        } else {
            --freeWorkingCells;
        }
    }
}
