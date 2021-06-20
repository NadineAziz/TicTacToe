package examples.rule;

import examples.PlayerButton;
import examples.Position;
import java.util.List;

/**
 *
 * @author Nadeen
 */
public class DiagonalRule extends Rule {

     /**
     * Implements the applyRule method from the Rule class where it takes the 
     * player's char and the current position and applies the rule to the box
     * in the main diagonal
     * @param playerChar
     * @param places
     * @param current 
     */
    @Override
    public void applyRule(String playerChar, List<List<PlayerButton>> places, Position current) {
        // checking the main diagonal
        int charCounter = 0;
        int saved = 0;
        for (int index = 0; index < places.size(); index++) {
            PlayerButton element = places.get(index).get(index);
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
        /*
        //  secondary diagonal
       
        counter = 0;
        for(int index = places.size() - 1; index >= 0; index--) {
            if (checkElement(element, playerChar)) {
                charCounter++;
                if (charCounter >= 4) {
                    removeElements(charCounter, retrivePositions(places, current, playerChar), places);
                    break;
                }
            } else {
                charCounter = 0;
            }
        }
         */
    }

}
