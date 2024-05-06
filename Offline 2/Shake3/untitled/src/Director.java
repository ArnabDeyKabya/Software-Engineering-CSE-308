import java.util.Locale;

public class Director {
    public void constructCoffeShake(shakeBuilder builder)
    {
        builder.setType("Coffe Shake");
        builder.setBase_price(250);
        builder.addCofee_zello();
        builder.addSugar();
    }
    public void constructChocolateShake(shakeBuilder builder)
    {
        builder.setType("Chocolate Shake");
        builder.setBase_price(230);
        builder.addChocolate_Syrap_iceCream();
    }
    public void constructStrawberryShake(shakeBuilder builder)
    {
        builder.setType("Strawberry Shake");
        builder.addStrawberry_syrupStrawberry_ice_cream();
        builder.setBase_price(200);
    }
    public void constructVanillaShake(shakeBuilder builder)
    {
        builder.setType("Vanilla Shake");
        builder.setBase_price(190);
        builder.addVanilla_flavor();
    }
    public void constructZeroShake(shakeBuilder builder)
    {
        builder.setType("Zero Shake");
        builder.setBase_price(240);
        builder.addSweetner();
    }
    public void makeLactoseFree(shakeBuilder builder)
    {
        builder.make_Lactose_Free();
    }
    public void addCandy(shakeBuilder builder)
    {
        builder.add_candy();
    }
    public  void addCookies(shakeBuilder builder)
    {
        builder.add_cookies();
    }
}
