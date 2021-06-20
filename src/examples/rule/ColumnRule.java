package examples.rule;

import examples.PlayerButton;
import examples.Position;
import java.util.List;


/**
 *
 * @author Nadeen
 */
public class ColumnRule extends Rule {
    /**
     * Implements the applyRule method from the Rule class where it takes the 
     * player's char and the current position and applies the rule to the box
     * in the column
     * @param playerChar
     * @param places
     * @param current 
     */

    @Override
    public void applyRule(String playerChar, List<List<PlayerButton>> places, Position current) {
        int charCounter = 0;
        int saved = 0;
        for (int row = 0; row < places.size(); row++) {
            if (checkElement(places.get(row).get(current.getY()), playerChar)) {
                charCounter++;
            } else {
                if (saved < charCounter) {
                    saved = charCounter;
                }
                charCounter = 0;
            }
        }
        removeElements(saved, retrivePositions(places, current, playerChar), places);
    }

}
