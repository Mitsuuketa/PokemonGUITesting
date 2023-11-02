package MyModel;
public class ClearScreen {
    /**
     * The function "cls" clears the console screen in Java.
     */
    public static void cls () {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
}
