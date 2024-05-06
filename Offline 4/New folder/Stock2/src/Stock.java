import java.io.IOException;
import java.util.LinkedList;

public class Stock implements Subject{
    private LinkedList observers;
    private String name;
    private int count;
    private float price;
    public Stock(String name,int count,float price)
    {
        this.name = name;
        this.count = count;
        this.price = price;
        this.observers = new LinkedList<Observer>();
    }
    public String getName() {
        return name;
    }
    public void increase_Price(float amount) {
        this.price += amount;
    }
    public void decrease_Price(float amount) {
        this.price -= amount;
    }
    public void change_Count(int count) {
        this.count = count;
    }
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
    public void registerObserver(Observer o) {
        observers.add(o);
    }
    public void removeObserver(Observer o) {
        int index = observers.indexOf(o);
        if(index>=0)
        {
            observers.remove(index);
        }
    }
    public void notifyObservers(String text) {
        for(int i=0;i<observers.size();i++)
        {
            Observer observer = (Observer)observers.get(i);

            try {
                observer.update(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
