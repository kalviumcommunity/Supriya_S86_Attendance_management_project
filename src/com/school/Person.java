public class Person {
    private static int nextIdCounter = 1;
    protected int id;
    protected String name;

    // Constructor
    public Person(String name) {
        this.id = nextIdCounter++;
        this.name = name;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    // Method
    public void displayDetails() {
        System.out.println("ID: " + id + ", Name: " + name);
    }
}
