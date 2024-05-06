import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Director director = new Director();
        while(true)
        {
            System.out.println("press 'o' for start ordering");
            System.out.println("press 'e' for quitting");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if (str.equalsIgnoreCase("o"))
            {
                while (true)
                {
                    System.out.println("enter shake name:");
                    String shake_name = sc.nextLine();
                    shakeBuilder builder = new shakeBuilder();
                    if(shake_name.equalsIgnoreCase("Chocolate")) director.constructChocolateShake(builder);
                    if(shake_name.equalsIgnoreCase("Coffe"))director.constructCoffeShake(builder);
                    if(shake_name.equalsIgnoreCase("Vanilla"))director.constructVanillaShake(builder);
                    if(shake_name.equalsIgnoreCase("Strawberry")) director.constructStrawberryShake(builder);
                }
            }
        }
    }
}
