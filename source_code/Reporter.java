package assignmentone;

import java.util.Arrays;
import java.util.List;

/**
 * Manages the recording and reporting of round outcomes in the game.
 * This class maintains a matrix structure that tallies the results for each possible move combination.
 * The class also produces a summary report of the game outcomes.
 *
 * @author Nikola Desnica (UNI: ndd2131)
 * @version 7.0
 */
public class Reporter extends Formatter {

    public int draws = 0;


    /**
     * Constructor that initializes the reporter with a list of valid moves and sets up the results matrix.
     *
     * @param validMoves A list containing valid moves for the game.
     * @param resultMatrix The matrix storing the results of moves.
     */
    public Reporter(List<String> validMoves, int[][] resultMatrix) {
        super(validMoves, resultMatrix);
    }

    /**
     * Updates the results matrix based on the outcome of a game round.
     *
     * @param computerMove The move chosen by the computer.
     * @param playerMove The move chosen by the player.
     * @param result The outcome of the round (+1 for a computer win, -1 for a computer loss, 0 for a draw).
     */
    public void updateResultsMatrix(String computerMove, String playerMove, int result) {
        int computerIndex = validMoves.indexOf(computerMove);
        int playerIndex = validMoves.indexOf(playerMove);

        if (computerIndex != -1 && playerIndex != -1) {
            matrix[computerIndex][playerIndex] += result;
        }
    }

    /**
     * Calculates the total number of rounds played based on the results matrix.
     * Simply adding all the absolute values together gives us this information.
     *
     * @return The total number of rounds played.
     */
    public int getTotalRoundsPlayed() {
        int total = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                total += Math.abs(matrix[i][j]);
            }
        }
        return total;
    }

    /**
     * Calculates the number of rounds the computer won based on the results matrix.
     * All the positive values in the results matrix indicate a computer win.
     *
     * @return The number of computer wins.
     */
    public int getComputerWins() {
        int wins = 0;
        for (int[] row : matrix) {
            for (int val : row) {
                if (val > 0) {
                    wins += val;
                }
            }
        }
        return wins;
    }

    /**
     * Calculates the number of rounds the player won.
     * total rounds played - computer wins = player wins.
     *
     * @return The number of player wins.
     */
    public int getPlayerWins() {
        return getTotalRoundsPlayed() - getComputerWins();
    }

    /**
     * Sets the draws variable to the number of rounds that resulted in a draw.
     *
     * @return A String representation of the number of draws.
     */
    public void incrementDraws() {
         this.draws += 1;
    }

    /**
     * Generates the full report of the game outcome as a results matrix with a total,
     * and two statements indicating how many rounds each participant won.
     *
     * @return The fully generated report as a String.
     */
    public String generateReport() {
        StringBuilder report = new StringBuilder();

        report.append(super.generateCenteredTitle("Friend"));
        report.append(super.generateHeader(1));
        report.append(super.generateSeparatorLine(1));
        report.append(generateRows());
        report.append(generateTotalRow()).append("\n");
        report.append("Computer wins: " + getComputerWins() + "/" + getTotalRoundsPlayed() + "\n");
        report.append("Friend wins: " + getPlayerWins() + "/" + getTotalRoundsPlayed() + "\n");
        report.append("Number of draws: " + this.draws + "\n");
        report.append("Number of throws: " + (getTotalRoundsPlayed() + this.draws) + "\n");

        return report.toString();
    }

    /**
     * Generates the result matrix values of the report and calculates the total column.
     *
     * @return A string representation of the result matrix rows.
     */
    @Override
    public String generateRows() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < matrix.length; i++) {
            int rowTotal = 0;
            if (i == 0) {
                builder.append("Computer ");
            } else {
                builder.append("         ");
            }
            builder.append(validMoves.get(i).toUpperCase().charAt(0)).append(" |");

            for (int j = 0; j < matrix[i].length; j++) {
                builder.append(String.format("%3d", matrix[i][j]));
                rowTotal += matrix[i][j];
            }
            builder.append(String.format("%5d\n", rowTotal));
        }

        return builder.toString();
    }

    /**
     * Helper method: Generates the total row which sums the outcomes for each move.
     *
     * @return A string representation of the total row.
     */
    public String generateTotalRow() {
        StringBuilder builder = new StringBuilder();
        int grandTotal = Arrays.stream(matrix)
                .mapToInt(row -> Arrays.stream(row).sum())
                .sum();

        builder.append("           |\n");
        builder.append("       Tot |");
        for (int i = 0; i < validMoves.size(); i++) {
            final int index = i;
            builder.append(String.format("%3d", Arrays.stream(matrix).mapToInt(row -> row[index]).sum()));
        }
        builder.append(String.format("%5d", grandTotal));
        builder.append("/" + getTotalRoundsPlayed() + "\n");

        return builder.toString();
    }
}