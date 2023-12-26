package assignmentone;

import java.util.Arrays;
import java.util.List;

/**
 * Determines the round's winner based on the game rules.
 * This class works in collaboration with the GameKeeper to fetch the outcome of a round and 
 * then communicates with the Reporter to record the result.
 * 
 * @author Nikola Desnica (UNI: ndd2131)
 * @version 3.0
 */
public class Referee {

    /**
     * Determines the winner of a round based on the player's and computer's moves.
     * 
     * @param playerMove The move chosen by the player.
     * @param computerMove The move chosen by the computer.
     * @param gameKeeper The GameKeeper instance which holds the game rules and validations.
     * @param reporter The Reporter instance to update with the round's outcome.
     * @return A string message indicating the result of the round.
     */
    public List<String> determineWinner(String playerMove, String computerMove, GameKeeper gameKeeper, Reporter reporter) {
        int outcome = gameKeeper.getOutcome(computerMove, playerMove);

        reporter.updateResultsMatrix(computerMove, playerMove, outcome);

        String message;
        String isDraw;

        if (outcome == 1) {
            message = "Computer Wins! " + computerMove + " beats " + playerMove;
            isDraw = "no";
        } else if (outcome == -1) {
            message = "You Win! " + playerMove + " beats " + computerMove;
            isDraw = "no";
        } else {
            message = "It's a Draw! Both chose " + playerMove;
            isDraw = "yes";
        }

        return Arrays.asList(message, isDraw);
    }
}