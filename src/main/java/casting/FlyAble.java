package casting;

public abstract class FlyAble {
    public String name;
    public Integer heigh;

    public FlyAble () {

    }
    public FlyAble (String name, Integer height) {
        this.name = name;
        this.heigh = height;
    }

    public void FlyWithWings () {
        System.out.println("Fly with wings");
    }

    public abstract void Fly ();

}
