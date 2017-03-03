package Game; /**
 * Created by kelseyedge on 2/27/17.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Board {
    private final List<Card> cardList;
    private final Rules createGame;
    private final JFrame frame;

    Board() {
        //menuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        JMenuItem reset = new JMenuItem("Reset");

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
        cardList = new ArrayList<>();
        List<Color> colors = Arrays.asList(colorArray);

        //setUp frame and panel
        frame = new JFrame();//creating instance of JFrame
        frame.setSize(400, 500);//400 width and 500 height
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        createGame = new Rules(frame);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,4,5,5));

        //for every rank (color) in array, assign one rank (color) to two separate cards
        for (Color color : colors) {
            cardList.add(new Card(color));
            cardList.add(new Card(color));
        }

        //for each card in cardList, set an ActionListener
        cardList.forEach(e->e.addActionListener(new Listener()));

        //builtin shuffle
        Collections.shuffle(cardList);

        //for every card in cardList, add it to panel
        cardList.forEach(panel::add);

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
    public class Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Card cardCliked = (Card)e.getSource();
            if (cardCliked != null && !cardCliked.isMatched()) {
                createGame.cardController(cardCliked);
            }
        }
    }

    public static void main(String[] args) {
            Board b = new Board();
        }
}

