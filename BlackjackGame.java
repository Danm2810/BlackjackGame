import java.util.Scanner;

public class BlackjackGame {
    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;
    private Scanner input;

    public BlackjackGame() {
        deck = new Deck();
        playerHand = new Hand();
        dealerHand = new Hand();
        input = new Scanner(System.in); // Initialize Scanner object
    }

    public void play() {
        initialDeal();
        printInstructions();

        while (true) {
            displayStatus();
            if (playerHand.calculateValue() > 21) {
                System.out.println("You busted! Dealer wins!");
                break;
            }

            String choice = getPlayerChoice(); // Get user input using getPlayerChoice()

            if (choice.equalsIgnoreCase("h")) {
                playerHand.addCard(deck.drawCard());
                System.out.println("You drew a " + playerHand.Card.get(playerHand.Card.size() - 1)); 
            } else if (choice.equalsIgnoreCase("s") || choice.equalsIgnoreCase("d")) {
                // Handle stand or double down logic
                dealerTurn();
                determineWinner();
                break;
            } else {
                System.out.println("Invalid choice. Please enter h, s, or d.");
            }
        }
    }

    private String getPlayerChoice() {
        while (true) {
            System.out.print("Enter your choice (h/s/d): ");
            String choice = input.nextLine();
            System.out.println("DEBUG: User input captured - Choice: " + choice);
            if (choice.equalsIgnoreCase("h") || choice.equalsIgnoreCase("s") || choice.equalsIgnoreCase("d")) {
                return choice; // Valid choice
            } else {
                System.out.println("Invalid choice. Please enter h, s, or d.");
            }
        }
    }


    private void initialDeal() {
        playerHand.addCard(deck.drawCard());
        playerHand.addCard(deck.drawCard());

        dealerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
    }

    private void printInstructions() {
        System.out.println("\nBlackjack!");
        System.out.println("Hit (h) to draw another card.");
        System.out.println("Stand (s) to stay with your current hand.");
        System.out.println("Double Down (d) to double your bet and draw one more card.");
    }

    private void displayStatus() {
        System.out.println("\nDealer's Hand: " + dealerHand.Card.get(0) + ", [Hidden Card]"); // Show one dealer card
        System.out.println("Your Hand: " + playerHand.toString());
        System.out.println("Your hand value: " + playerHand.calculateValue());
    }

    @SuppressWarnings("unused")
    private void playerTurn() {
        String choice = "";
        while (!choice.equalsIgnoreCase("h") && !choice.equalsIgnoreCase("s") && !choice.equalsIgnoreCase("d")) {
            System.out.print("Enter your choice (h/s/d): \n");
            choice = input.nextLine();
        }

        if (choice.equalsIgnoreCase("h")) {
            playerHand.addCard(deck.drawCard());
            System.out.println("You drew a " + playerHand.getCard().get(playerHand.Card.size() - 1)); 
        } else if (choice.equalsIgnoreCase("d")) {
            // Handle double down logic (will need to address betting for this)
            playerHand.addCard(deck.drawCard());
            System.out.println("You drew a " + playerHand.Card.get(playerHand.Card.size() - 1)); 
        } 
        // Else - player stands
    }

    private void dealerTurn() {
        System.out.println("\nDealer's Hand: " + dealerHand.toString()); 
        while (dealerHand.calculateValue() < 17) {
            System.out.println("Dealer hits...");
            dealerHand.addCard(deck.drawCard());
            System.out.println("Dealer drew a " + dealerHand.Card.get(dealerHand.Card.size() - 1)); 
        }
    }

    private void determineWinner() {
        int playerScore = playerHand.calculateValue();
        int dealerScore = dealerHand.calculateValue();

        if (playerScore > 21) {
            System.out.println("You busted! Dealer wins!");
        } else if (dealerScore > 21) {
            System.out.println("Dealer busted! You win!");
        } else if (playerScore > dealerScore) {
            System.out.println("You win!");
        } else if (playerScore < dealerScore) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("Push! (Tie)");
        }
    }

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.play();
    }
} 
