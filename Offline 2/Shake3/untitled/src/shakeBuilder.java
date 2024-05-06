import java.util.LinkedList;

public class shakeBuilder {
    private String type;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    protected double base_price;
    public void setBase_price(double p)
    {
        base_price = p;
    }
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
    public Shake getShake()
    {
        return new Shake(base_price, total_price, type, elements);

    }

}
