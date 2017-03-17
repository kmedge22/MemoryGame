package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kelseyedge on 2/27/17.
 */
public class Card extends JButton {
    private Boolean flipped;
    private Color myColor;
    private Boolean isMatched;
    private String stringColor;

    public Card(Color myColor) {
        this.setPreferredSize(new Dimension(200,200));
        this.myColor = myColor;
        this.isMatched = false;
        this.flipped = false;
        this.setBackground(Color.DARK_GRAY);
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.stringColor = "";
    }

    public Card(String cardColor){
        this.setPreferredSize(new Dimension(200,200));
        this.myColor = Color.WHITE;
        this.isMatched = false;
        this.flipped = false;
        this.setBackground(Color.DARK_GRAY);
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.stringColor = cardColor;
        setText("");
    }

    public Color getMyColor() {
        return myColor;
    }

    public String getStringColor() {
        return stringColor;
    }

    public void setMatched(Boolean trueOrFalse) {
        isMatched = trueOrFalse;
    }

    /**
     * tracks whether card has found it's match
     * @return boolean
     */
    public Boolean isMatched() {return isMatched;}

    /**
     * flip to the opposite side of the card
     * If face-down, color is grey
     * If face-up, color is myColor
     */
    public void flipIt() {
        this.flipped = !flipped;
        if (flipped) {
            setBackground(myColor);
            setText(stringColor);
        } else {
            setBackground(Color.DARK_GRAY);
            setText("");
        }
    }

    /**
     * overrides equals method to compare card colors
     * autogenerated
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Card card = (Card) other;

        return myColor.equals(card.myColor) && stringColor.equals(card.stringColor);
    }

}
