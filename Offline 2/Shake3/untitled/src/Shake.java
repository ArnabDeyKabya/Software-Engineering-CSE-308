import java.util.LinkedList;

public class Shake {
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
    public double total_price =base_price;

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public double getTotal_price()
    {
        return  total_price;
    }
    protected LinkedList<Object> elements=new LinkedList<Object>();;


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
    public Shake(double b, double t, String ty, LinkedList<Object> e)
    {
        base_price = b;
        type = ty;
        total_price = t;
        elements = e;
    }

}
