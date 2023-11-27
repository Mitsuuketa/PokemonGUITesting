import java.util.Random;

public class testing {
    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println("Random Number between 1 to 100:");
        for(int i = 0; i < 10; i++)
            System.out.println(rand.nextInt(1,10));
    }
}
