
// TODO: Implement the BaccaratCard class in this file
public class BaccaratCard extends Card{

    public BaccaratCard(Rank r, Suit s){
        // Implement the card
        super(r, s);
    }

    public int value(){
        // Get the value of the card
        if(super.value() == 10){
            return 0;
        } else {
            return super.value();
        }
    }
}