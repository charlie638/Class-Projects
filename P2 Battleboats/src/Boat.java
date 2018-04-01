//terwi045

public class Boat {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int x3;
    private int y3;
    private boolean part1 = false;
    private boolean part2 = false;
    private boolean part3 = false;

    public Boat(int xval1, int yval1, int xval2, int yval2, int xval3, int yval3) {
        x1 = xval1;
        y1 = yval1;
        x2 = xval2;
        y2 = yval2;
        x3 = xval3;
        y3 = yval3;
    }

    public boolean sunk(){
        if (part1 && part2 && part3)
            return true;
        return false;
    }

    public void hit(int x, int y) {
        if (x == x1 && y == y1)
            part1 = true;
        else if (x == x2 && y == y2)
            part2 = true;
        else if (x == x3 && y == y3)
            part3 = true;
    }

    public int getX1() {
        return x1;
    }
    public int getX2() {
        return x2;
    }
    public int getX3() {
        return x3;
    }
    public int getY1() {
        return y1;
    }
    public int getY2() {
        return y2;
    }
    public int getY3() {
        return y3;
    }
}
