package assignmentone;

import java.util.List;
import java.util.Map;

/**
 * The main orchestrator for the Rock-Paper-Scissors game and its variants.
 * It provides the main flow and control for game sessions and rounds.
 *
 * @author Nikola Desnica (UNI: ndd2131)
 * @version 4.0
 */
public class Runner {

    /**
     * The main entry point of the program.
     * Initializes and manages the flow of the game sessions, captures user input,
     * manages game rounds, determines the outcome of each round, and provides the final report.
     *
     * @param args Command line arguments not used.
     */
    public static void main(String[] args) {
        Talker talker = new Talker();

        do {
            playGame(talker);
        } while (talker.playAnotherGame());

        System.out.println();
        System.out.println("Thank you for playing!");
        System.out.println();
    }

    /**
     * Plays a single game session, including initialization and rounds.
     *
     * @param talker The Talker instance for displaying messages and capturing user input.
     */
    public static void playGame(Talker talker) {
        talker.displayWelcomeMessage();
        String gameType = talker.promptForGameType();

        GameConfig.setCurrentGameType(gameType);
        int[][] rulesMatrix = GameConfig.getMatrixForGame(gameType);
        List<String> validMoves = GameConfig.getValidMovesForGame(gameType);
        Map<String, Integer> weights = GameConfig.getWeightsForGame(gameType);

        talker.displayRules(gameType, rulesMatrix, validMoves);

        GameKeeper gameKeeper = new GameKeeper(rulesMatrix, validMoves);
        int size = validMoves.size();
        int[][] emptyResultMatrix = new int[size][size];

        Thinker thinker = new Thinker(weights);
        Referee referee = new Referee();
        Reporter reporter = new Reporter(validMoves, emptyResultMatrix);

        int numberOfRounds = talker.getNumberOfRounds();
        playRounds(talker, thinker, referee, gameKeeper, reporter, numberOfRounds, validMoves);

        String report = reporter.generateReport();
        talker.displayReport(report);
    }

    /**
     * Plays multiple rounds within a game session.
     *
     * @param talker The Talker instance for displaying messages and capturing user input.
     * @param thinker The Thinker instance for computer move generation.
     * @param referee The Referee instance for determining round outcomes.
     * @param reporter The Reporter instance for generating the final report.
     * @param numberOfRounds The number of rounds to play.
     * @param validMoves The list of valid moves for the current game.
     */
    public static void playRounds(Talker talker, Thinker thinker, Referee referee, GameKeeper gameKeeper, Reporter reporter, int numberOfRounds, List<String> validMoves) {
        for (int i = 0; i < numberOfRounds; i++) {
            String computerMove = thinker.getComputerMove();
            String playerMove = talker.getPlayerMove(validMoves);

            if ("q".equalsIgnoreCase(playerMove)) {
                break;
            }

            List<String> outcome = referee.determineWinner(playerMove, computerMove, gameKeeper, reporter);
            String message = outcome.get(0);
            String isDraw = outcome.get(1);

            if ("yes".equals(isDraw)) {
                i--;
                reporter.incrementDraws();
            }

            talker.displayRoundOutcome(message);
        }
    }
}