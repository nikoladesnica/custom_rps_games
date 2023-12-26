package assignmentone;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Provides game-specific configurations such as rules matrices, valid moves, and move weights.
 * This class contains static data and methods, serving as a centralized hub for game settings.
 * 
 * @author Nikola Desnica (UNI: ndd2131)
 * @version 2.0
 */
public class GameConfig {

    private static String currentGameType;

    private static final int[][] RPS_RULES = {
        {0, -1, 1},
        {1, 0, -1},
        {-1, 1, 0}
    };

    private static final int[][] RPSKL_RULES = {
        {0, -1, 1, -1, 1},
        {1, 0, -1, 1, -1},
        {-1, 1, 0, -1, 1},
        {1, -1, 1, 0, -1},
        {-1, 1, -1, 1, 0}
    };

    private static final int[][] RPSFW_RULES = {
        {0, -1, 1, -1, 1},
        {1, 0, -1, -1, 1},
        {-1, 1, 0, -1, 1},
        {1, 1, 1, 0, -1},
        {-1, -1, -1, 1, 0}
    };
    private static final int[][] RPMFW_RULES = {
        {0, -1, 1, -1, 1},
        {1, 0, -1, -1, 1},
        {-1, 1, 0, 0, 0},
        {1, 1, 0, 0, -1},
        {-1, -1, 0, 1, 0}
    };

    private static final List<String> RPS_MOVES = Arrays.asList("r", "p", "s");
    private static final List<String> RPSKL_MOVES = Arrays.asList("r", "p", "s", "k", "l");
    private static final List<String> RPSFW_MOVES = Arrays.asList("r", "p", "s", "f", "w");
    private static final List<String> RPMFW_MOVES = Arrays.asList("r", "p", "m", "f", "w");

    private static final Map<String, Integer> RPSFW_WEIGHTS = Map.of(
        "r", 1,
        "p", 1,
        "s", 1,
        "f", 3,
        "w", 3
    );
    private static final Map<String, Integer> RPMFW_WEIGHTS = Map.of(
        "r", 1,
        "p", 1,
        "m", 1,
        "f", 2,
        "w", 2
    );

    /**
     * Sets the current game type.
     * 
     * @param gameType The chosen game type as a string.
     */
    public static void setCurrentGameType(String gameType) {
        currentGameType = gameType;
    }

    /**
     * Retrieves the current game type.
     * 
     * @return The currently selected game type.
     */
    public static String getCurrentGameType() {
        return currentGameType;
    }
    
    /**
     * Fetches the rules matrix for the specified game type.
     * 
     * @param gameName Name of the game type.
     * @return A 2D matrix representing the game rules.
     */
    public static int[][] getMatrixForGame(String gameName) {
        switch (gameName) {
            case "rps":
                return RPS_RULES;
            case "rpskl":
                return RPSKL_RULES;
            case "rpsfw":
                return RPSFW_RULES;
            case "rpmfw":
                return RPMFW_RULES;
            default:
                return null; // Won't happen since gameName validation occurs in Talker.
        }
    }

    /**
     * Provides a list of valid moves for the specified game type.
     * 
     * @param gameName Name of the game type.
     * @return A list containing valid moves for the game or null if the game type is not found.
     */
    public static List<String> getValidMovesForGame(String gameName) {
        switch (gameName) {
            case "rps":
                return RPS_MOVES;
            case "rpskl":
                return RPSKL_MOVES;
            case "rpsfw":
                return RPSFW_MOVES;
            case "rpmfw":
                return RPMFW_MOVES;
            default:
                return null; // Won't happen since gameName validation occurs in Talker.
        }
    }

    /**
     * Retrieves the move weights for a specified game type.
     * 
     * @param gameName Name of the game type.
     * @return A map containing move weights for the game or null if the game type does not have weights.
     */
    public static Map<String, Integer> getWeightsForGame(String gameName) {
        switch (gameName) {
            case "rpsfw":
                return RPSFW_WEIGHTS;
            case "rpmfw":
                return RPMFW_WEIGHTS;
            default:
                return null;
        }
    }
}