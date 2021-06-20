package examples.rule;

import examples.PlayerButton;
import examples.Position;
import static examples.rule.Rule.checkElement;
import java.util.List;


/**
 *
 * @author Nadeen
 */
public class RowRule extends Rule {

     /**
     * Implements the applyRule method from the Rule class where it takes the 
     * player's char and the current position and applies the rule to the box
     * in the row
     * @param playerChar
     * @param places
     * @param current 
     */
    @Override
    public void applyRule(String playerChar, List<List<PlayerButton>> places, Position current) {

        List<PlayerButton> row = places.get(current.getX());

        int saved = 0;
        int charCounter = 0;
        for (PlayerButton element : row) {
            if (checkElement(element, playerChar)) {
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
