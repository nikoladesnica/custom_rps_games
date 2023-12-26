package assignmentone;

import java.util.List;

/**
 * Provides formatting utilities for presenting matrices.
 * 
 * @author Nikola Desnica (UNI: ndd2131)
 * @version 3.0
 */
public class Formatter {

    public List<String> validMoves;
    public int[][] matrix;

    public Formatter(List<String> validMoves, int[][] matrix) {
        this.validMoves = validMoves;
        this.matrix = matrix;
    }

    /**
     * Displays the rules matrix for a given game so the user knows how to play.
     *
     * @return String representation of the rules matrix.
     */
    public String displayMatrix() {
        StringBuilder display = new StringBuilder();

        display.append(generateCenteredTitle("Friend"));
        display.append(generateHeader(0));
        display.append(generateSeparatorLine(0));
        display.append(generateRows());

        return display.toString();
    }

    /**
     * Helper method: Centers the given title in the middle of the report.
     * 
     * @param title The title to be centered.
     * @return A string with the centered title.
     */
    protected String generateCenteredTitle(String title) {
        StringBuilder builder = new StringBuilder();
        int headerWidth = 3 * validMoves.size(); // 3 characters per move (want friend centered above moves).
        int paddingSize = (headerWidth - title.length()) / 2 + 13; // 13 for side padding, 3 to center word.

        for (int i = 0; i < paddingSize; i++) {
            builder.append(" ");
        }
        builder.append(title).append("\n");

        return builder.toString();
    }

    /**
     * Helper method: Generates the header of the report with valid moves.
     * 
     * @return A string representation of the header.
     */
    protected String generateHeader(int isResultMatrix) {
        StringBuilder builder = new StringBuilder("            ");
        for (String move : validMoves) {
            builder.append(String.format("%3s", move.toUpperCase().charAt(0)));
        }
        if (isResultMatrix == 1) {
            builder.append("   Tot\n");
        }
        else {
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * Helper method: Generates a separator line for the report.
     * 
     * @return A string representation of the separator line.
     */
    protected String generateSeparatorLine(int isResultMatrix) {
        StringBuilder builder = new StringBuilder("            ");
        int totalWidth = 3 * validMoves.size() + 1;
        if (isResultMatrix == 1) {
            totalWidth += 9;
        }
        for (int i = 0; i < totalWidth; i++) {
            builder.append("-");
        }
        builder.append("\n");
        return builder.toString();
    }

    /**
     * Helper method: Generates the rows of the rules matrix.
     *
     * @return A string representation of the rules matrix rows.
     */
    protected String generateRows() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < matrix.length; i++) {
            if (i == 0) {
                builder.append("Computer ");
            } else {
                builder.append("         ");
            }
            builder.append(validMoves.get(i).toUpperCase().charAt(0)).append(" |");

            for (int j = 0; j < matrix[i].length; j++) {
                builder.append(String.format("%3d", matrix[i][j]));
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
