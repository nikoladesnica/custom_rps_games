package assignmentone;

import java.util.List;

/**
 * Maintains the game's rules and move validations.
 * The class facilitates the quick retrieval of game outcomes and move validity checks.
 * 
 * @author Nikola Desnica (UNI: ndd2131)
 * @version 2.0
 */
public class GameKeeper {

    private int[][] rulesMatrix;
    private List<String> validMoves;

    /**
     * Constructs a GameKeeper instance with the provided rules matrix and valid moves.
     * 
     * @param rulesMatrix The matrix that holds the game's rules.
     * @param validMoves The list containing valid moves for the game.
     */
    public GameKeeper(int[][] rulesMatrix, List<String> validMoves) {
        this.rulesMatrix = rulesMatrix;
        this.validMoves = validMoves;
    }

    /**
     * Retrieves the outcome of a round based on the computer's move and player's move.
     * 
     * @param computerMove The move chosen by the computer.
     * @param playerMove The move chosen by the player.
     * @return The outcome value: +1 for computer win, -1 for computer loss, and 0 for a draw.
     */
    public int getOutcome(String computerMove, String playerMove) {
        int computerIndex = validMoves.indexOf(computerMove);
        int playerIndex = validMoves.indexOf(playerMove);

        if (computerIndex != -1 && playerIndex != -1) {
            return rulesMatrix[computerIndex][playerIndex];
        } else {
            return 0;
        }
    }
}
