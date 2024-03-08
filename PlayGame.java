import java.io.IOException;
import java.util.Scanner;

public class PlayGame {

    public static Card deal(BaccaratHand user, Shoe shoe){
        // Deal the cards to the player/banker
        Card card = shoe.deal();
        user.add(card);
        return card;
    }

    public static void output(BaccaratHand banker, BaccaratHand player){
        // Output the value of the player and bankers hands
        System.out.println("Player: " + player.toString() + " = " + player.value());
        System.out.println("Banker: " + banker.toString() + " = " + banker.value());
    }

    public static int checkNatural(BaccaratHand banker, BaccaratHand player){
        //Check to see if either player has a natural number
        if(banker.isNatural() && player.isNatural()){
            System.out.println("Tie!");
            return 0;
        } else if(banker.isNatural()){
            System.out.println("Banker wins!");
            return 2;
        } else if(player.isNatural()){
            System.out.println("Player wins!");
            return 1;
        }
        return 3;
    }

    public static boolean bankersRuleNoPThird(BaccaratHand banker, Shoe shoe){
        // Check whether to deal a third card to the banker
        if(banker.value() < 6){
            deal(banker, shoe);
            return true;
        } else {
            return false;
        }
    }

    public static int playerThirdCardVal(Card.Rank playertval){
        // Find the value of the players third card
        int value = playertval.getSymbol();
        return value;
    }

    public static boolean bankersRulePThird(BaccaratHand banker, int playertval, Shoe shoe){
        // Check whether to deal a third card to the banker
        if(banker.value() < 3){
            deal(banker, shoe);
            return true;
        } else if(banker.value() == 3 && playertval != 8){
            deal(banker, shoe);
            return true;
        } else if(banker.value() == 4 && playertval > 1 && playertval < 8){
            deal(banker, shoe);
            return true;
        } else if(banker.value() == 5 && playertval > 3 && playertval < 8){
            deal(banker, shoe);
            return true;
        } else if(banker.value() == 6 && playertval > 5 && playertval < 8){
            deal(banker, shoe);
            return true;
        }

        return false;
    }

    public static int returnHigher(BaccaratHand banker, BaccaratHand player){
        // Check whether the player or banker win
        if(banker.value() > player.value()){
            System.out.println("Banker wins!");
            return 2;
        } else if(player.value() > banker.value()){
            System.out.println("Player wins!");
            return 1;
        } else{
            System.out.println("Tie!");
            return 0;
        }
    }

    public static int dealThird(BaccaratHand banker, BaccaratHand player, Shoe shoe){
        
        // Initialise variables 
        Card playerthirdcard;
        boolean bthird;
        boolean pthird = false;

        // Check whether to deal a third card to the player
        if(player.value() < 6){
            System.out.println("Dealing third card to player...");
            playerthirdcard = deal(player, shoe);
            int playertval = playerThirdCardVal(playerthirdcard.getRank());
            pthird = true;
            bthird = bankersRulePThird(banker, playertval, shoe);
        } else{
            bthird = bankersRuleNoPThird(banker, shoe);
        }

        // Check whether to output that a third card was dealt to banker
        if(bthird){
            System.out.println("Dealing third card to banker...");
        }

        // If third card was dealt, output new hand values
        if(pthird || bthird){
            output(banker, player);
        }

        // Return the winner of the third round
        int result = returnHigher(banker, player);
        return result;
    }

    public static boolean nextGame(Scanner scan) throws IOException{

        // Ask whether to continue to next round
        System.out.print("Another round? (y/n): ");
        String answer = scan.nextLine(); 

        // Check value of input
        if(answer.charAt(0) == 'Y' || answer.charAt(0) == 'y'){
            return true;
        } else if(answer.charAt(0) == 'n' || answer.charAt(0) == 'N'){
            return false;
        } else{
            // Throw error if input isn't valid
            throw new IOException("Invalid input");
        }
    }

    public static void outputScores(int rounds, int tie, int player, int banker){
        System.out.println();

        // Output win counter values
        if(rounds == 1){
            System.out.println("1 round played");
        } else {
            System.out.println(rounds + " rounds played");
        }

        if(player == 1){
            System.out.println("1 player win");
        } else {
            System.out.println(player + " player wins");
        }

        if(banker == 1){
            System.out.println("1 banker win");
        } else {
            System.out.println(banker + " banker wins");
        }

        if(tie == 1){
            System.out.println("1 tie");
        } else {
            System.out.println(tie + " ties");
        }
    }

    public static void clearHand(BaccaratHand player, BaccaratHand banker){
        // Empty the card hands
        player.discard();
        banker.discard();
    }

    public static boolean checkInteractive(String[] inputs) throws IOException{
        // Check command line input
        if(inputs.length > 0){
            if(inputs[0].equals("--interactive") || inputs[0].equals("-i")){
              return true;
            } else {
                throw new IOException("Invalid command line input");
            }
        }
        return false;
    }

}
