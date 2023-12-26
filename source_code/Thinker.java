package assignmentone;

import java.util.Map;
import java.util.Random;
import java.util.List;

/**
 * Represents the computer's decision-making logic, determining its move based on the game's strategy.
 * It uses a set of move weights, if provided, to make a decision. Otherwise, it randomly selects a move.
 * 
 * @author Nikola Desnica (UNI: ndd2131)
 * @version 3.0
 */
public class Thinker {

    private Map<String, Integer> moveWeights;
    private Random random = new Random();

    /**
     * Constructs a Thinker instance with the provided move weights.
     * 
     * @param weights The map of move names to their respective weights.
     */
    public Thinker(Map<String, Integer> weights) {
        this.moveWeights = weights;
    }

    /**
     * Determines the computer's move. If move weights are defined, the move is selected based on them.
     * Otherwise, a random move is selected.
     * 
     * @return The move chosen by the computer.
     */
    public String getComputerMove() {
        if (moveWeights == null || moveWeights.isEmpty()) {
            List<String> moves = GameConfig.getValidMovesForGame(GameConfig.getCurrentGameType());
            return moves.get(random.nextInt(moves.size()));
        } else {
            int totalWeight = moveWeights.values().stream().mapToInt(Integer::intValue).sum();
            int randomValue = random.nextInt(totalWeight);
            for (Map.Entry<String, Integer> entry : moveWeights.entrySet()) {
                randomValue -= entry.getValue();
                if (randomValue < 0) {
                    return entry.getKey();
                }
            }
            return null; // This should not happen if weights are properly defined
        }
    }
}
