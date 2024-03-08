import java.util.Collections;

// TODO: Implement the Shoe class in this file
public class Shoe extends CardCollection{
    public Shoe(int decks){
        // create cards for a deck of length 6
        if(decks == 6 || decks == 8){
            for(int i = 0; i < decks; i++){
                for(Card.Suit s: Card.Suit.values()) {
                    for(Card.Rank r: Card.Rank.values()){
                        BaccaratCard card = new BaccaratCard(r, s);
                        super.add(card);
                    }
                }
            }
        } else {
            //return exception if deck length is not 6 or 8
            throw new CardException("Invalid deck length");
        }
    }

    public Card deal(){
        // Check the size of the cards list
        if(cards.size() == 0){
            throw new CardException("Invalid number");
        }

        // Selects a card from the list and returns it
        Card card = cards.get(0);
        cards.remove(card);
        return card;
    }

    public void shuffle(){
        // Shuffle the cards in the list 
        Collections.shuffle(cards);
    }
}