//terwi045

public class Statistics {

    private double profit;      // to calculate the average profit
    private double filled;      // to calculate avg filled percent
    private double wait;        // to calculate avg/max wait time
    private double time;        // to calculate statistics per day
    private double duration;    // to calculate the time a vessel takes
    private double distance;    // to calculate the distance a vessel goes

    public Statistics(double profit, double filled, double wait, double duration, double distance, double time) {
        this.profit = profit;
        this.filled = filled;
        this.wait = wait;
        this.time = time;
        this.duration = duration;
        this.distance = distance;
    }

    public double getTime() {
        return time;
    }
    public double getProfit() {
        return profit;
    }
    public double getFilled() {
        return filled;
    }
    public double getWait() {
        return wait;
    }
    public double getDuration() {
        return duration;
    }
    public double getDistance() {
        return distance;
    }
    public void setProfit(double p) {
        profit = p;
    }

    public static void add(Statistics stat) {
        Statistics[] temp = new Statistics[Simulation.st.length+1];
        for (int i = 0; i < Simulation.st.length; i++) {
            temp[i] = Simulation.st[i];
        }
        temp[temp.length-1] = stat;
        Simulation.st = temp;
    }

    public String toString() {
        return "$" + profit + " at " + time;
    }
}
