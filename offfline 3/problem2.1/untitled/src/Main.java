import java.nio.file.FileSystem;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        FileSystem current = new FileSystem();
        FileSystem root = new FileSystem();
        while (true) {
            Scanner sc = new Scanner(System.in);
            String cmd = sc.nextLine();

            List<String> commands = Arrays.stream(cmd.split("\\s+"))
                    .collect(Collectors.toList());
            if (commands.get(0).equalsIgnoreCase("mkdrive")) {
                FileSystem f = new FileSystem(commands.get(1), "Drive");
                root = f;
                current = f;
                System.out.println("Created a new Drive named " + f.getName());
            }
            if (commands.get(0).equalsIgnoreCase("cd") && commands.get(1).equalsIgnoreCase("~")) {
                current = root;
                System.out.println("Changed The current Directory to root " + root.getName());

            } else if (commands.get(0).equalsIgnoreCase("cd")) {
                if (current.getName().equals(commands.get(1))) {
                    System.out.println("Changed the current directory to " + commands.get(1));
                } else {
                    FileSystem f = current.find_child(commands.get(1));
                    if (f == null) {
                        System.out.println("Doesn't exists in current Directory");
                    } else {
                        current = f;
                        System.out.println("Changed the current directory to " + commands.get(1));
                    }
                }
            }
            if (commands.get(0).equalsIgnoreCase("ls")) {
                if (current.getName().equals(commands.get(1))) {
                    current.Details();
                } else {
                    FileSystem f = current.find_child(commands.get(1));
                    if (f == null) {
                        System.out.println("Doesn't exists in current Directory");
                    } else {
                        f.Details();
                    }
                }
            }
            if (commands.get(0).equalsIgnoreCase("list")) {
                if (current.components.size() == 0) System.out.println("There is no Component");
                current.show_components();
            }
            if (commands.get(0).equalsIgnoreCase("delete") && commands.get(1).equals("-r")) {
                if (current.getName().equals(commands.get(2))) {
                   // System.out.println("Hello 2");
                    if (current.getType().equalsIgnoreCase("File")) {
                        if (current.Delete(current.getName())) {
                            // System.out.println("Hello 3");
                            System.out.println("A file " + current.getName() + " Deleted");
                        }
                    }else {
                            //System.out.println("Hello 4");
                            if (current.getComponent_count() == 0) {
                                boolean flag = current.Delete(current.getName());
                                if (flag) System.out.println(current.getName() + " deleted");
                            }
                            else
                            {
                                //System.out.println("Hello 1");
                                current.recursiveDelete();
                                boolean flag = current.Delete(current.getName());
                                if (flag) System.out.println(current.getName() + " deleted");
                            }
                        }

                }
                else
                {
                    FileSystem f = current.find_child(commands.get(1));
                    if (f == null) {
                        System.out.println("Doesn't exists in current Directory");
                    } else {
                        if (f.getType().equalsIgnoreCase("File")) {
                            boolean flag = f.Delete(f.getName());
                            System.out.println("A File "+f.getName() + " Deleted");
                        } else {
                            if (f.getComponent_count() == 0) {
                                boolean flag = f.Delete(f.getName());
                                if (flag) System.out.println(f.getName() + " Deleted");
                            } else {
                                f.recursiveDelete();
                                boolean flag = f.Delete(f.getName());
                                if (flag) System.out.println(f.getName() + " deleted");
                            }
                        }
                    }

                }
            }else if (commands.get(0).equalsIgnoreCase("delete")) {
                    if (current.getName().equals(commands.get(1))) {
                        if (current.getType().equalsIgnoreCase("File")) {
                            if (current.Delete(current.getName())) {
                                System.out.println(current.getName() + " Deleted");
                            }
                        } else {
                            if (current.getComponent_count() == 0) {
                                boolean flag = current.Delete(current.getName());
                                if (flag) System.out.println(current.getName() + " deleted");
                            } else {
                                System.out.println("Can't delete");
                            }
                        }
                    } else {
                        FileSystem f = current.find_child(commands.get(1));
                        if (f == null) {
                            System.out.println("Doesn't exists in current Directory");
                        } else {
                            if (f.getType().equalsIgnoreCase("File")) {
                                boolean flag = f.Delete(f.getName());
                                System.out.println(f.getName() + " Deleted");
                            } else {
                                if (f.getComponent_count() == 0) {
                                    boolean flag = f.Delete(f.getName());
                                    if (flag) System.out.println(f.getName() + " Deleted");
                                } else {
                                    System.out.println("Can't delete");
                                }
                            }
                        }
                    }
                }
                if (commands.get(0).equalsIgnoreCase("mkdir")) {
                    if (current.getType().equalsIgnoreCase("Drive")) {
                        FileSystem f = new FileSystem(commands.get(1), "Folder");
                        current.addComponent(f);
                    } else {
                        FileSystem f = current;
                        if (f.getParent() == null) System.out.println("You are not under any Drive");
                        else {
                            while (f.getParent() != null) {
                                if (f.getType().equalsIgnoreCase("Drive")) {
                                    break;
                                }
                                f = f.getParent();
                            }
                            if (f == null) System.out.println("You are not under any Drive");
                            else {
                                FileSystem file = new FileSystem(commands.get(1), "Folder");
                                current.addComponent(file);
                            }
                        }
                    }
                }
                if (commands.get(0).equalsIgnoreCase("touch")) {
                    if (current.getType().equalsIgnoreCase("Drive") || current.getType().equalsIgnoreCase("Folder")) {
                        FileSystem f = new FileSystem(commands.get(1), "File");
                        f.setSize(Float.parseFloat(commands.get(2)));
                        current.addComponent(f);
                    }
                }
            }
        }

}