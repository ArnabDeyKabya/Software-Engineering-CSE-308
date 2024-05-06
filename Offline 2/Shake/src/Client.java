import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        while (true)
        {
            System.out.println("press 'o' for start ordering");
            System.out.println("press 'e' for quitting");
            Shake product;
            Order current_order = null;
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if (str.equalsIgnoreCase("o"))
            {
                Order o = new Order();
                current_order = o;
                while(true) {
                    System.out.println("enter shake name:");
                    String shake_name = sc.nextLine();
                    o.setOrders(shake_name);
                    System.out.println("Add Your ingredients. Enter the choice number: ");
                    System.out.println("1. Make Lactose Free");
                    System.out.println("2. Add Candy");
                    System.out.println("3. Add Cookies");
                    Scanner scanner = new Scanner(System.in);
                    int userInput = scanner.nextInt();
                    if(userInput == 1)
                    {
                        o.Make_lactose_free();
                    }
                    if(userInput == 2)
                    {
                        o.add_candy();
                    }
                    if(userInput == 3)
                    {
                        o.add_cookies();
                    }
                    System.out.println("Do you want to order another shake?");
                    System.out.println("If yes..press y to give another order. If not press e to close your order");
                    Scanner sc1 = new Scanner(System.in);
                    String str1 = sc1.nextLine();
                    if(str1.equalsIgnoreCase("Y"))
                    {
                        continue;
                    }
                    else if(str1.equalsIgnoreCase("e"))
                    {
                        if(o.orders.size()<1)
                        {
                            System.out.println("You didn't give any order. Make sure an order");
                            continue;
                        }
                        else
                            break;
                    }
                    else if(str1.equalsIgnoreCase("o"))
                    {
                        System.out.println("An  order is going on");
                    }
                }
                System.out.println("Here are your orders: ");
                o.print_order();

            }
            else if(str.equalsIgnoreCase("e"))
            {

                    System.out.println("You didn't give any order. Make sure an order");
                    continue;
            }
        }
    }
}
