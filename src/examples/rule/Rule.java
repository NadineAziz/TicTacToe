/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.rule;

import examples.PlayerButton;
import examples.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;

/**
 *
 * @author l94ti
 */
public abstract class Rule {

    private final Random random = new Random();
    
    /**
     * Checks if the element given (x or o) is equal to the player's char
     * @param element
     * @param playerChar
     * @return 
     */
    protected static boolean checkElement(PlayerButton element, String playerChar) {
        return element.getText().equals(playerChar);
    }
    
    /**
     * Get the position of the player to later check if they are equal through checkElement
     * @param places
     * @param current
     * @param playerChar
     * @return 
     */
    protected List<Position> retrivePositions(List<List<PlayerButton>> places, Position current, String playerChar) {
        List<Position> playerChars = new ArrayList<>();
        for (List<PlayerButton> row : places) {
            for (PlayerButton element : row) {
                if (element.getText().equals(playerChar)) {
                    playerChars.add(element.getPosition());
                }
            }
        }
        playerChars.remove(current);
        return playerChars;
    }

    /**
     * Removing elements randomly from the boxes
     * @param playerChars
     * @param places 
     */
    protected void removeRandomly(List<Position> playerChars, List<List<PlayerButton>> places) {
        int randomValue = random.nextInt(playerChars.size());
        Position position = playerChars.get(randomValue);
        places.get(position.getX()).get(position.getY()).setText("");
        places.get(position.getX()).get(position.getY()).setEnabled(true);
        playerChars.remove(position);
    }

    /**
     * Removes elements from the list according to the 4 or 3 rule
     * @param count
     * @param playerChars
     * @param places 
     */
    protected void removeElements(int count, List<Position> playerChars, List<List<PlayerButton>> places) {
        if (count == 4) {
            removeRandomly(playerChars, places);
            removeRandomly(playerChars, places);
            return;
        }

        if (count == 3) {
            removeRandomly(playerChars, places);
            return;
        }
    }

    /**
     * Applies rule according to the position
     * @param playerChar
     * @param places
     * @param current 
     */
    public abstract void applyRule(String playerChar,
            List<List<PlayerButton>> places,
            Position current
    );

}
