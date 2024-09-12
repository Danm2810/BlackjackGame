public class Card {
    public enum Suit { HEARTS, DIAMONDS, CLUBS, SPADES }
    public enum Rank { ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING }

    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getValue() {
        if (rank == Rank.ACE) {
            return 11; // Initially assume Ace is 11
        } else if (rank.ordinal() >= 10) {  // Face cards
            return 10;
        } else {
            return rank.ordinal() + 1;
        }
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
