public class Imposter extends BaseDecorator {

    Imposter(Passanger passanger) {
        super(passanger);

    }
    @Override
    public void repair()
    {
        passanger.repair();
        Damage(passanger);

    }
    @Override
    public void work()
    {
        passanger.work();
        Killing(passanger);
    }

    @Override
    public void login() {
        passanger.login();
        login_imposter(passanger);
    }

    @Override
    public void logout() {
        passanger.logout();
        logout_imposter(passanger);

    }

    public void Damage(Passanger passanger)
    {
        System.out.println("Damaging The spaceship");
    }
    public  void Killing(Passanger passanger)
    {
        System.out.println("Trying to kill a crewmate.\n" +
                "Successfully killed a crewmate.");
    }
    public void login_imposter(Passanger passanger)
    {
        System.out.println("We won’t tell anyone; you are an imposter");
    }
    public void logout_imposter(Passanger passanger)
    {
        System.out.println("See you again Comrade Imposter");
    }
}
