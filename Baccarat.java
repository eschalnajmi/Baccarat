import java.io.IOException;
import java.util.Scanner;

public class Baccarat {
  public static void main(String[] args) throws IOException{

    Scanner scan = new Scanner(System.in);

    // Check which mode is chosen
    boolean interactive = PlayGame.checkInteractive(args);

    // Initialise loop variables
    int won;
    boolean anothergame = true;
    
    // Initialise counters
    int tiecounter = 0;
    int playerwins = 0;
    int bankerwins = 0;
    int roundsplayed = 0;

    // Create the shoe
    Shoe shoe = new Shoe(6);
    // Shuffle the cards in the shoe
    shoe.shuffle();

    // Create a card hand for the player and the banker   
    BaccaratHand banker = new BaccaratHand();
    BaccaratHand player = new BaccaratHand();

    while(anothergame && shoe.size() > 5){

      roundsplayed++;

      System.out.println();
      System.out.println("Round " + roundsplayed);

      // Deal cards into the hands
      for(int i = 0; i < 2; i++){
        PlayGame.deal(player, shoe);
        PlayGame.deal(banker, shoe);
      }
    
      // Output the cards in each hand and their values
      PlayGame.output(banker, player);

      // Check if either player has a natural
      won = PlayGame.checkNatural(banker, player);

      // Increment counters or deal third hand
      if(won == 0){
        tiecounter++;
      } else if(won == 1){
        playerwins++;
      } else if(won == 2){
        bankerwins++;
      } else{
        int result = PlayGame.dealThird(banker, player, shoe);
        if(result == 1){
          playerwins++;
        } else if(result == 2){
          bankerwins++;
        } else{
          tiecounter++;
        }
      }

      // Clear the decks
      PlayGame.clearHand(player, banker);

      if(interactive){
        anothergame = PlayGame.nextGame(scan);
      }

    }

    PlayGame.outputScores(roundsplayed, tiecounter, playerwins, bankerwins);

    scan.close();
  }
}
