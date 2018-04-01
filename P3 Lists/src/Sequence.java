//terwi045

public class Sequence extends Star {

    public Sequence(String name, double mass, double size) {
        super(name, mass, size);
    }

    public boolean isSun() {
        return getMass() == 2 && getSize() == 430;
    }

    public String toString() {
        return "<" + getName() + ">:" + " A Sequence Star with mass = <" + getMass() + "> KG; Size = <" + getSize() + "> miles";
    }

    public boolean isBlackHole() {
        return getMass() > 1000 && getSize() < 50;
    }
}
