import java.util.ArrayList;
public class Hand {
    ArrayList<Card> Card; // Private field for encapsulation

    public Hand() {
        Card = new ArrayList<>();
    }

    public void addCard(Card card) {
        Card.add(card);
    }

    public int calculateValue() {
        int value = 0;
        boolean hasAce = false;
    
        for (Card card : Card) {
            value += card.getValue();
            if (card.getValue() == 11) {
                hasAce = true;
            }
        }
    
        // If we have ace and value is over 21, count ace as 1
        if (hasAce && value > 21) {
            value -= 10;
        }
    
        // Default return in case of unexpected scenarios (optional)
        return value; 
    }
    

    public void clearHand() {
        Card.clear();
    }

    // Getter method for Card (returning a copy)
    public ArrayList<Card> getCard() {
        return new ArrayList<>(Card); // Return a copy of the list
    }

    @Override
    public String toString() {
        StringBuilder handString = new StringBuilder();
        for (Card card : Card) {
            handString.append(card).append(", "); // Add each card with a comma and space
        }

        // Remove the trailing comma and space from the last card
        if (handString.length() > 2) {
            handString.deleteCharAt(handString.length() - 1);
            handString.deleteCharAt(handString.length() - 1);
        }

        return handString.toString();
    }
}
