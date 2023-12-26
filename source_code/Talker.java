package assignmentone;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Manages all user interactions, including displaying messages and capturing inputs.
 * Ensures user-friendly interfaces and prompt handling.
 * 
 * @author Nikola Desnica (UNI: ndd2131)
 * @version 9.0
 */
public class Talker {

    private Scanner scanner = new Scanner(System.in);
    private Map<String, String> moveLabels = Map.of(
            "r", "( r ) - Rock",
            "p", "( p ) - Paper",
            "s", "( s ) - Scissors",
            "k", "( k ) - Spock",
            "l", "( l ) - Lizard",
            "f", "( f ) - Fire",
            "w", "( w ) - Water",
            "m", "( m ) - Monkey"
    );

    /**
     * Displays a welcome message with a design suited for retro gaming.
     */
    public void displayWelcomeMessage() {
        System.out.println();
        System.out.println("============================================");
        System.out.println("~                                          ~");
        System.out.println("~         WELCOME TO THE RPS GAME!         ~");
        System.out.println("~                                          ~");
        System.out.println("============================================");
    }

    /**
     * Displays the rules of the selected game type.
     * 
     * @param gameType The type of game the player has chosen.
     * @param rulesMatrix The matrix detailing the game's rules.
     * @param validMoves A list of valid moves for the game.
     */
    public void displayRules(String gameType, int[][] rulesMatrix, List<String> validMoves) {
        System.out.println();
        System.out.println("### RULES for " + gameType.toUpperCase() + " ###");
        System.out.println();
        System.out.println("# 1 = computer win # -1 = player win, # 0 = draw");
        System.out.println();
        Formatter formatter = new Formatter(validMoves, rulesMatrix);
        String rulesDisplay = formatter.displayMatrix();
        System.out.println(rulesDisplay);
        }

    /**
     * Prompts the user to choose the game type they wish to play.
     * 
     * @return The selected game type.
     */
    public String promptForGameType() {
        List<String> validGameTypes = List.of("rps", "rpskl", "rpsfw", "rpmfw");
        String choice;
        System.out.println();
        do {
            System.out.println("Choose a game type:");
            System.out.println("( rps ) - Rock Paper Scissors");
            System.out.println("( rpskl ) - Rock Paper Scissors Lizard Spock");
            System.out.println("( rpsfw ) - Rock Paper Scissors Fire Water");
            System.out.println("( rpmfw ) - Rock Paper Monkey Fire Water");
            System.out.println();

            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();
            if (!validGameTypes.contains(choice)) {
                displayErrorMessage("Invalid game type! Please choose a valid game type.");
            }
        } while (!validGameTypes.contains(choice));
        return choice;
    }

    /**
     * Prompts the user for the number of rounds they would like to play.
     *
     * @return The selected number of rounds
     */
    public int getNumberOfRounds() {
        System.out.print("Enter the number of rounds you want to play: ");
        int rounds;
        while (true) {
            try {
                rounds = Integer.parseInt(scanner.nextLine());
                if (rounds <= 0) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a valid number: ");
            }
        }
        return rounds;
    }

    /**
     * Prompts the player to select their move based on the game type.
     * 
     * @return The move selected by the player.
     */
    public String getPlayerMove(List<String> validMoves) {
        System.out.println();
        String move;
        do {
            System.out.println("Choose your move:");
            for (String mv : validMoves) {
                System.out.println(moveLabels.get(mv));
            }
            System.out.println("( q ) - Quit Early...");
            System.out.println();

            System.out.print("Enter your move: ");
            move = scanner.nextLine();
            if (!validMoves.contains(move) && !move.equals("q")) {
                displayErrorMessage("Invalid move! Please choose a valid move.");
            }
        } while (!validMoves.contains(move) && !move.equals("q"));
        return move;
    }

    /**
     * Displays the outcome of the round.
     * 
     * @param message The result of the round.
     */
    public void displayRoundOutcome(String message) {
        System.out.println();
        System.out.println("### ROUND RESULT ###");
        System.out.println();
        System.out.println(message);
    }

    /**
     * Displays any error messages ensuring the user is informed of issues.
     * 
     * @param error The error message to be displayed.
     */
    public void displayErrorMessage(String error) {
        System.out.println("ERROR: " + error);
    }

    /**
     * Displays the report of the game in a formatted manner.
     * 
     * @param report The game report.
     */
    public void displayReport(String report) {
        System.out.println();
        System.out.println("### GAME REPORT ###");
        System.out.println();
        System.out.println(report);
    }

    /**
     * Asks the user if they want to play another game.
     * 
     * @return {@code true} if the user wants to play another game, {@code false} otherwise.
     */
    public boolean playAnotherGame() {
        String choice;
        do {
            System.out.print("Play another game? (y/n): ");
            choice = scanner.nextLine();
            if (!List.of("y", "n").contains(choice.toLowerCase())) {
                displayErrorMessage("Invalid input! Please enter 'y' or 'n'.");
            }
        } while (!List.of("y", "n").contains(choice.toLowerCase()));
        return choice.equalsIgnoreCase("y");
    }
}
