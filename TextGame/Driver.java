package TextGame;

public class Driver {
   /**
    * The main function creates a new Game object and repeatedly starts the game until the player's
    * choice is not equal to 1.
    */
    public static void main(String[] args) {
        Game game = new Game(1);
        while(game.getChoice() == 1) {
            game.startGame();
        }
    }
}
