
package examples;

import examples.rule.Rule;
import examples.rule.RuleUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Nadeen
 */
public class TicTacToe {

    private JFrame frame;
    private final JPanel bPanel;
    private final JPanel fPanel;
    private final List<List<PlayerButton>> places;
    private int rows;
    private int cols;
    private JPanel tPanel;
    private JTextField current;
    private int playerCounter = 0; // 0 - x, 1 - o
    private final List<Rule> rules;

    public TicTacToe(int rowNumber, int colNumber) {

        rules = RuleUtil.createRulesList();

        cols = colNumber;
        rows = rowNumber;
        places = new ArrayList<>();
        frame = new JFrame("Connect Four");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(450, 650));
        bPanel = new JPanel();
        bPanel.setLayout(new BoxLayout(bPanel, BoxLayout.X_AXIS));
        fPanel = new JPanel();
        fPanel.setLayout(new GridLayout(rows, cols));
        current = new JTextField(650);
        current.setText("Player X");
        tPanel = new JPanel();
        tPanel.setLayout(new BoxLayout(tPanel, BoxLayout.X_AXIS));
        tPanel.add(current);

        for (int i = 0; i < rows; i++) {
            places.add(new ArrayList<PlayerButton>());

            for (int j = 0; j < cols; j++) {
                PlayerButton button = new PlayerButton(new Position(i, j));
                button.addActionListener(new PlayerActionListener());
                button.setPreferredSize(new Dimension(25, 25));
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                fPanel.add(button);
                places.get(i).add(button);
            }
        }

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gridMenu = new JMenu("Grids");
        menuBar.add(gridMenu);
        JMenuItem eight = new JMenuItem("6x6");
        gridMenu.add(eight);
        eight.addActionListener((ActionEvent e) -> {
            new TicTacToe(6, 6);
            frame.setVisible(false);
        });

        JMenuItem ten = new JMenuItem("10x10");
        gridMenu.add(ten);
        ten.addActionListener((ActionEvent e) -> {
            new TicTacToe(10, 10);
            frame.setVisible(false);
        });
        JMenuItem twelve = new JMenuItem("14X14");
        gridMenu.add(twelve);
        twelve.addActionListener((ActionEvent e) -> {
            new TicTacToe(14, 14);
            frame.setVisible(false);
        });

        frame.getContentPane().add(BorderLayout.NORTH, tPanel);
        frame.getContentPane().add(BorderLayout.CENTER, bPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, fPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private class PlayerActionListener implements ActionListener {
//  0 - X, 1 - O
        /**
         * switches between player X and player O and updates the positions
         * @param e 
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Position currentPos = ((PlayerButton) e.getSource()).getPosition();

            switch (TicTacToe.this.playerCounter) {
                case 0:
                    playerCounter = 1;
                    ((PlayerButton) e.getSource()).setText("X");
                    ((PlayerButton) e.getSource()).setEnabled(false);

                    if (!isWin("X", currentPos)) {
                        for (Rule rule : rules) {
                            rule.applyRule("X", places, currentPos);
                        }
                        TicTacToe.this.current.setText("Player O");
                    }
                    break;
                case 1:
                    playerCounter = 0;
                    ((PlayerButton) e.getSource()).setText("O");
                    ((PlayerButton) e.getSource()).setEnabled(false);
                    if (!isWin("O", currentPos)) {
                        for (Rule rule : rules) {
                            rule.applyRule("O", places, currentPos);
                        }
                        TicTacToe.this.current.setText("Player X");
                    }
                    break;
            }
        }
        /**
         * refreshes the panel when a player wins or no one wins
         * @param playerChar 
         */
        private void refreshPanel(String playerChar) {
            frame.setVisible(false);
            if (playerChar.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Deal" + playerChar, "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "The winner is : " + playerChar, "", JOptionPane.INFORMATION_MESSAGE);
            }
            new TicTacToe(6, 6);
        }

        /**
         * Checks if the table is full to clear it or checks if actually
         * a player wins through a row, column or a diagonal
         * @param playerChar
         * @param current
         * @return 
         */
        private boolean isWin(String playerChar, Position current) {

            //  checks if the table is full
            int counter = 0;
            for (List<PlayerButton> row : places) {
                for (PlayerButton element : row) {
                    if (element.getText().isEmpty()) {
                        counter++;
                    }
                }
            }

            if (counter == 0) {
                refreshPanel("");
                return true;
            }

            //  checks the main diagonal
            int diagonalCounter = 0;
            for (int index = 0; index < places.size(); index++) {
                if (places.get(index).get(index).getText().equals(playerChar) && (diagonalCounter < 5)) {
                    diagonalCounter++;
                    if (isWin(diagonalCounter)) {
                        refreshPanel(playerChar);
                        return true;
                    }
                } else {
                    diagonalCounter = 0;
                }
            }

            //  checks the rows
            int rowCounter = 0;
            for (PlayerButton element : places.get(current.getX())) {
                if (element.getText().equals(playerChar) && (rowCounter < 5)) {
                    rowCounter++;
                    if (isWin(rowCounter)) {
                        refreshPanel(playerChar);
                        return true;
                    }
                } else {
                    rowCounter = 0;
                }
            }

            //  checks the current row
            int columnCounter = 0;
            for (List<PlayerButton> row : places) {
                if (row.get(current.getY()).getText().equals(playerChar) && (columnCounter < 5)) {
                    columnCounter++;
                    if (isWin(columnCounter)) {
                        refreshPanel(playerChar);
                        return true;
                    }
                } else {
                    columnCounter = 0;
                }
            }
            return false;
        }

        private boolean isWin(int diagonalCounter) {
            return diagonalCounter >= 5;
        }

    }

}
