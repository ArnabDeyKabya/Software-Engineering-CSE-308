import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;

public class User implements  Observer{
    int id;
    public LinkedList<Stock> subscribedStocks;
    private DataInputStream dis;
    private DataOutputStream dos;

    public User(int id,DataInputStream dis, DataOutputStream dos){
        this.id = id;
        this.dis = dis;
        this.dos = dos;
        subscribedStocks = new LinkedList<Stock>();
    }

    @Override
    public void update(String text) throws IOException {
        dos.writeUTF(text);
        dos.flush();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public void setDis(DataInputStream dis) {
        this.dis = dis;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }
}
