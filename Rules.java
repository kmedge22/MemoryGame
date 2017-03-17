package Game;

/**
 * Created by kelseyedge on 3/2/17.
 */

import javax.swing.*;


public class Rules {
    private int numberOfMoves = 0;
    private final Card[] cardsPerTurn = new Card[2];
    private int howManyMatches = 0;
    private int selectNum = 0;
    private JFrame parentFrame;
    private int numberOfPairs;

    /**
     * @param other pass in Jframe object from board setUp
     */
    public Rules(JFrame other, int numberOfPairs) {
        parentFrame = other;
        this.numberOfPairs = numberOfPairs;
    }

    /**
     * Controls number of moves before flip and cards to be compared
     * Tracks number of matches a player has
     * Tracks number of moves a player has made
     * Controls when a game ends based on number of matches
     * creates a timer to delay a moment when mismatched cards are flipped
     *
     * @param aCard Card selected
     */
    public void cardController(Card aCard) {
        if (selectNum == 2) {
            return;
        }
        aCard.flipIt();
        selectNum++;

        if (selectNum == 1) {
            cardsPerTurn[0] = aCard;
        } else if (selectNum == 2) {
            cardsPerTurn[1] = aCard;
            numberOfMoves++;
            if (cardsPerTurn[0].equals(cardsPerTurn[1])) {
                cardsPerTurn[0].isMatched();
                cardsPerTurn[1].isMatched();
                selectNum = 0;
                howManyMatches++;
                if (howManyMatches == numberOfPairs) {
                    gameOver();
                }
            } else {
                Timer time = new Timer(500, null);
                time.addActionListener(e -> {
                    //for cards to be compared, flip all cards
                    for (Card card : cardsPerTurn) {
                        card.flipIt();
                    }
                    selectNum = 0;
                    time.stop();
                });
                time.start();
            }
        }
    }

    /**
     * Handles when game is over
     * print statement in console and popup dialog box
     */
    private void gameOver() {
        String winner = ("You Win! You made " + numberOfMoves + " moves.");
        System.out.println(winner);
        JOptionPane.showMessageDialog(parentFrame, winner);


    }

}
