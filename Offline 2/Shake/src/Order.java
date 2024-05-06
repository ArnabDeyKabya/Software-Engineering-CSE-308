import java.util.LinkedList;

public class Order {
    private Shake shake;
    private double total_order_price;

    public void setTotal_order_price(double total_order_price) {
        this.total_order_price = total_order_price;
    }

    public double getTotal_order_price() {
        return total_order_price;
    }

    public LinkedList<Shake> orders;
    public Order()
    {
        orders=new LinkedList<Shake>();
    }
    public void setOrders(String s)
    {
        if(s.equalsIgnoreCase("Chocolate Shake"))
        {
            shake = new Chocolate_shake();
            orders.add(shake);
        }
        if(s.equalsIgnoreCase("Coffee Shake"))
        {
            shake = new Coffee_shake();
            orders.add(shake);
        }
        if(s.equalsIgnoreCase("Vanilla Shake"))
        {
            shake = new Vanilla_shake();
            orders.add(shake);
        }
        if(s.equalsIgnoreCase("Strawberry Shake"))
        {
            shake = new Strawberry_shake();
            orders.add(shake);
        }
        if(s.equalsIgnoreCase("Zero Shake"))
        {
            shake = new Zero_shake();
            orders.add(shake);
        }
    }

    public double find_price()
    {
        double p=0;
        for(Shake s :orders)
        {
            p += s.getTotal_price();
        }
        return p;
    }
    public void Make_lactose_free()
    {
        shake.make_Lactose_Free();
    }
    public void add_candy()
    {
        shake.add_candy();
    }
    public void add_cookies()
    {
        shake.add_cookies();
    }
    void print_order()
    {
        for(Shake s: orders)
        {
            s.print();
        }
        System.out.println("Total Price of The order: "+ find_price());
    }

}
