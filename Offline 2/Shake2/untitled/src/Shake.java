import java.util.LinkedList;

abstract class Shake {
    private String type;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    protected double base_price;
    abstract void setBase_price();
    public double getBase_price()
    {
        return base_price;

    }
    public double total_price;

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public double getTotal_price()
    {
        return  total_price;
    }
    protected LinkedList<Object> elements;
    public Shake()
    {
        total_price = base_price;
        elements=new LinkedList<Object>();
    }
    public void addChocolate_Syrap_iceCream()
    {
        elements.add("Chocolate Syrap");
        elements.add("Chocolate Ice Cream");
    }
    public void addCofee_zello()
    {
        elements.add("Cofee");
        elements.add("Zello");
    }
    public void addSugar()
    {
        elements.add("Sugar");
    }
    public void addStrawberry_syrupStrawberry_ice_cream()
    {
        elements.add("Strawberry Syrup");
        elements.add("Strawberry ice cream");
    }
    public void addVanilla_flavor()
    {
        elements.add("Vanilla Flavour");
        elements.add("Zello");
    }
    public void addSweetner()
    {
        elements.add("Sweetner");
        elements.add("Vanilla flavour");
        elements.add("Sugar free Zello");
    }

    public void addPrice(double price)
    {
        total_price += price;
    }

    public void make_Lactose_Free()
    {
        addPrice(60);
        elements.add("Lactose Free");
    }

    public void add_candy()
    {
        addPrice(50);
        elements.add("Candy");
    }
    public void add_cookies()
    {
        addPrice(40);
        elements.add("Cookies");
    }

    void print()
    {
        System.out.println(getType() + ":");
        for(Object item : elements)
        {
            System.out.println(item);
        }
        System.out.println("base price: "+base_price);
        System.out.println("total price: "+total_price);
        System.out.println();
    }

}

class Chocolate_shake extends Shake{
    public Chocolate_shake()
    {
        super();
        setType("Chocolate Shake");
        setBase_price();
        addChocolate_Syrap_iceCream();
        addSugar();
    }


    @Override
    void setBase_price() {
        base_price = 230;
        setTotal_price(base_price);
    }
}

class Coffee_shake extends Shake
{
    public Coffee_shake()
    {
        super();
        setType("Coffee Shake");
        setBase_price();
        addCofee_zello();
        addSugar();
    }

    @Override
    void setBase_price() {
        base_price = 250;
        setTotal_price(base_price);
    }
}
class Strawberry_shake extends Shake
{
    public Strawberry_shake()
    {
        super();
        setType("Strawberry Shake");
        addStrawberry_syrupStrawberry_ice_cream();
        addSugar();
        setBase_price();
    }
    @Override
    void setBase_price() {
        base_price = 200;
        setTotal_price(base_price);
    }
}
class Vanilla_shake extends Shake{
    public Vanilla_shake()
    {
        super();
        setType("Vanilla Shake");
        addVanilla_flavor();
        setBase_price();
    }

    @Override
    void setBase_price() {
        base_price = 190;
        setTotal_price(base_price);
    }
}

class Zero_shake extends Shake
{
    public Zero_shake()
    {
        super();
        setType("Zero Shake");
        setBase_price();
        addVanilla_flavor();
    }

    @Override
    void setBase_price() {
        base_price = 240;
        setTotal_price(base_price);
    }
}
