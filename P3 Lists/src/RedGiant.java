//terwi045

public class RedGiant extends Star{

    public RedGiant(String name, double mass, double size) {
        super(name, mass, size);
    }

    public boolean isSuperGiant() {
        return getMass() > 100 && getSize() > 100;
    }

    public boolean isBlackHole() {
        return isSuperGiant();
    }

    public String toString() {
        String kind = "RedGiant";
        if (isSuperGiant())
            kind = "SuperGiant";
        return "<" + getName() + ">: A " + kind + " with mass = <" + getMass() + "> KG and size = <" + getSize() + "> miles";
    }
}
