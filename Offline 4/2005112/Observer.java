import java.io.IOException;

public interface Observer {
    public void update(String text) throws IOException;
}
