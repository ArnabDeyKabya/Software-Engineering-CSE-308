public class Crewmate implements  Passanger{

    @Override
    public void repair() {
        System.out.println("Repairing The Spaceship");
    }

    @Override
    public void work() {
        System.out.println("Doing Research");
    }

    @Override
    public void logout() {
        System.out.println("Bye Bye crewmate.");
    }

    @Override
    public void login() {
        System.out.println("Welcome Crewmate!");
    }
}
