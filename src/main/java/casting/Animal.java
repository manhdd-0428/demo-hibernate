package casting;

public class Animal {
   private String name;
   private String color;

    public Animal() {}

    public Animal(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public void action() {
        System.out.println("Animal running");
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
