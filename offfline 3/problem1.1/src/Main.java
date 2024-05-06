import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Passanger crewmate = new Crewmate();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        if(str.equalsIgnoreCase("login crew1"))
        {
            crewmate.login();
            str =sc.nextLine();
            if(str.equalsIgnoreCase("repair"))
            {
                crewmate.repair();
            }
            str = sc.nextLine();
            if(str.equalsIgnoreCase("work"))
            {
                crewmate.work();
            }
            str = sc.nextLine();
            if(str.equalsIgnoreCase("logout"))
            {
                crewmate.logout();
            }
        }
        str = sc.nextLine();
        if(str.equalsIgnoreCase("login imp1"))
        {
            Passanger imposter = new Imposter(crewmate);
            imposter.login();
            str = sc.nextLine();
            if(str.equalsIgnoreCase("repair"))
            {
                imposter.repair();
            }
            str = sc.nextLine();
           if (str.equalsIgnoreCase("work"))
           {
               imposter.work();
           }
            str = sc.nextLine();
            if (str.equalsIgnoreCase("logout"))
            {
                imposter.logout();
            }
        }
    }
}
