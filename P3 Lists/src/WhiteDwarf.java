//terwi045

public class WhiteDwarf extends Star {

    public WhiteDwarf(String name, double mass, double size) {
        super(name, mass, size);
    }

    public boolean isBlackHole() {
        return false;
    }

    public String toString() {
        return "<" + getName() + ">: A WhiteDwarf with mass = <" + getMass() + "> KG and size = <" + getSize() + "> miles";
    }
}
