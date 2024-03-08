// TODO: Implement the BaccaratHand class in the file
public class BaccaratHand extends CardCollection{
    public BaccaratHand(){
        // Implement the hand
        super();
    }

    public int value(){
        // Output the value of the deck, ensuring its < 10
        int val = super.value() % 10;
        return val;
    }

    public Boolean isNatural(){
        // Check if the value of the hand is a natural
        if(size() == 2 && value() == 8){
            return true;
        } else if (size() == 2 && value() == 9){
            return true;
        }

        return false;
    }

    public String toString(){
        // Output the rank and suit of the hand as symbols
        String deckString = "";
        for(int i = 0; i < super.size(); i++){
            String card;
            Card.Rank rank = cards.get(i).getRank();
            Card.Suit suit = cards.get(i).getSuit();
            if(i == super.size()-1){
                card = String.format("%c%c", rank.getSymbol(), suit.getSymbol());
            } else{
                card = String.format("%c%c ", rank.getSymbol(), suit.getSymbol());
            }
            deckString += card;
        }
        return deckString;
    }
}