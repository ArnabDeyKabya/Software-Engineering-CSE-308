public abstract class BaseDecorator implements Passanger{
    protected Passanger passanger;
    BaseDecorator(Passanger passanger)
    {
        this.passanger = passanger;
    }

    @Override
    public void repair() {
        passanger.repair();
    }

    @Override
    public void work() {
        passanger.work();
    }

    @Override
    public void logout() {
        passanger.logout();
    }

    @Override
    public void login() {
        passanger.login();
    }
}
