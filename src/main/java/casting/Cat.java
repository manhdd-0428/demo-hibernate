package casting;

public class Cat extends Animal {
    private int age;

    public Cat() {}
    public Cat(String name, String color, Integer age) {
        super(name, color);
        this.age = age;
    }

    @Override
    public void action() {
        System.out.println("Cat running");
    }

    public void mew () {
        System.out.println("Moew moew moew");
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
