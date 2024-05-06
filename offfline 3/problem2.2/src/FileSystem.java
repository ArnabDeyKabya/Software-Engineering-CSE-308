
import java.time.LocalDateTime;
import java.util.LinkedList;

public class FileSystem{
    private String name;
    private String type;
    private float size;
    private String directory;
    private FileSystem parent;
    private int component_count;
    public LinkedList<FileSystem> components;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory() {
        if(type != "Drive")
            directory = parent.getDirectory() +"\\"+ name;
        else
            directory = name;
    }

    public int getComponent_count() {
        return components.size();
    }

    public void setComponent_count(int component_count) {
        this.component_count = component_count;
    }

    public LinkedList<FileSystem> getComponents() {
        return components;
    }

    public void setComponents(LinkedList<FileSystem> components) {
        this.components = components;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileSystem getParent() {
        return parent;
    }

    public void setParent_directory(FileSystem parent) {

        this.parent = parent;
        parent.size += this.size;
        setDirectory();
    }

    public void addComponent(FileSystem f)
    {
        components.add(f);
        System.out.println("A New Component named "+f.getName()+ " Added");
        f.setParent_directory(this);
    }

    LocalDateTime creationTime;
    FileSystem()
    {
        parent = null;
        components = new LinkedList<FileSystem>();
        creationTime = LocalDateTime.now();
        directory =  name;
        name = "";
        type = "";
    }
    FileSystem(String name, String type)
    {
        this.name = name;
        this.type = type;
        components = new LinkedList<FileSystem>();
        creationTime = LocalDateTime.now();
        directory = name;
//        if(type != "Drive")
//        directory = parent.getDirectory() +"\\"+ name;
//        else
//            directory = name;
    }
    public void Details()
    {
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Size: " + Float.toString(size));
        System.out.println("Directory: " + directory);
        System.out.println("Component Count: " +Integer.toString( getComponent_count()));
        System.out.println("Creation Time: " + creationTime);
    }
    public void show_components()
    {
       for (FileSystem f : components)
       {
           System.out.println(f.name + "      "+f.size + "         "+ f.creationTime+ "   "+ f.directory );
       }
    }
    public FileSystem find_child(String n)
    {
        for (FileSystem f: components)
        {
            if(f.name.equalsIgnoreCase(n))
                return f;
        }
        return null;
    }
    public boolean Delete(String n)
    {
        if(parent == null) return true;
        for(FileSystem f: parent.components)
        {
            if(f.name.equals(n))
            {
                parent.components.remove(f);
                return true;
            }
        }
        return false;
    }
    public void recursiveDelete()
    {
        for(FileSystem f: components)
        {
            components.remove(f);
        }
        System.out.println("Recursively Deleted All the components");
    }
}
