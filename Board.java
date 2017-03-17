package Game; /**
 * Created by kelseyedge on 2/27/17.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.JOptionPane;

public class Board {
    private final List<Card> cardList;
    private final Rules createGame;
    private final JFrame frame;
    private int rows;
    private int cols;
    private int pairsNumFromUser;

    Board() {
        //menuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        JMenuItem reset = new JMenuItem("Reset");

        //Dialog Box to determine number of pairs to play with
        JFrame dFrame = new JFrame();
        Object numberOfPairs = JOptionPane.showInputDialog(dFrame, "Enter number of pairs you want to play with: ");

        //Sets reaction to clicking reset from the menuBar
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Board();
                frame.dispose();
            }
        });

        //add fileMenu to menuBar
        menuBar.add(fileMenu);
        fileMenu.add(reset);

        //ranks: what will be compared to determine match
        Color[] colorArray = {Color.GREEN, Color.BLUE, Color.MAGENTA, Color.ORANGE, Color.RED, Color.YELLOW};
        String[] stringArray = {"red", "yellow", "green", "blue", "magenta", "orange"};
        cardList = new ArrayList<>();
        List<Color> colors = Arrays.asList(colorArray);

        //determine how many rows and columns
        //take object and turn it to an integer
        determineSquare(Integer.valueOf(Integer.parseInt(numberOfPairs.toString())));
        this.pairsNumFromUser = Integer.valueOf(Integer.parseInt(numberOfPairs.toString()));

        //setUp frame and panel
        frame = new JFrame();//creating instance of JFrame
        frame.setSize(500, 500);//400 width and 500 height
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        createGame = new Rules(frame, pairsNumFromUser);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, cols, 5, 5));

        //builtin shuffle
        Collections.shuffle(cardList);

        //for every rank (color) in array, assign one rank (color) to two separate cards
        for (Color color : colors) {
            cardList.add(new Card(color));
        }
            for (String colorString : stringArray) {
                cardList.add(new Card(colorString));
            }

            //iterate through the list and ensure index doesn't run out of range
            List<Card> game = new ArrayList<>();
            for (int i = 0; i < pairsNumFromUser; i++) {
                if (cardList.get(i%cardList.size()).getMyColor()==Color.white ) {
                    game.add(new Card(cardList.get(i%cardList.size()).getStringColor()));
                    game.add(new Card(cardList.get(i%cardList.size()).getStringColor()));
                }
                else {
                    game.add(new Card(cardList.get(i%cardList.size()).getMyColor()));
                    game.add(new Card(cardList.get(i%cardList.size()).getMyColor()));
                }
            }

            //shuffle
            Collections.shuffle(game);

            //for each card in cardList, set an ActionListener
            game.forEach(e -> e.addActionListener(new Listener()));

            //for every card in game List, add it to panel
            game.forEach(panel::add);

            //place panel and menuBar in frame
            frame.add(panel, BorderLayout.CENTER);
            frame.add(menuBar, BorderLayout.NORTH);
            frame.pack();
            frame.setVisible(true);//making the frame visible
        }
        /**
         * find object of click event. If a isn't empty and is matched
         * then talk to Rules for instruction
         */
        public class Listener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                Card cardCliked = (Card) e.getSource();
                if (cardCliked != null && !cardCliked.isMatched()) {
                    createGame.cardController(cardCliked);
                }
            }
        }

    /**
     * determines number of rows and columns for board based on number of pairs in the game.
     * sets this.rows and this.cols
     *
     * @param numberOfPairs number of pairs of cards player wants to play with
     *
     */
    private void determineSquare(int numberOfPairs){
        numberOfPairs *= 2;
        int cardsPerRow = 5;
        int cardsPerRowC = 5;
        int rowTracker = 5;
        int rows = 2;
        int cols = 2;
        int colTracker = 7;
        while (cardsPerRow < (numberOfPairs)) {
            //if cards that i am comparing to is less than number of cards in the game, add one row
            rows += 1;
            //number of rows changes for every rowTracker number of cards on the board
            cardsPerRow += rowTracker;
            //rowTracker increases by two after every increase in rows
            rowTracker += 2;
        }
        while (cardsPerRowC < numberOfPairs){
            cols += 1;
            cardsPerRowC += colTracker;
            colTracker += 2;
        }
        this.cols = cols;
        this.rows = rows;
    }

    public static void main(String[] args) {
        Board b = new Board();

    }
