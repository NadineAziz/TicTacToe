package examples;

import javax.swing.JButton;


/**
 *
 * @author Nadeen
 */
public class PlayerButton extends JButton {
    
    /**
     * Used to access the position
     */
    private final Position position;
    
    public PlayerButton(Position position) {
        super("");
        this.position = position;
    }
    
    public Position getPosition() {
        return this.position;
    }
    
    public String toString() {
        return "Value " + getText();
    }
     
}
