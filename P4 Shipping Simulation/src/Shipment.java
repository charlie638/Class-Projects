//terwi045

public class Shipment {

    private int weight; // in KG
    private double timeCreated;
    private String destination;

    public Shipment(int weight, String destination) {
        this.weight = weight;
        this.timeCreated = Simulation.agenda.getCurrentTime();
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }
    public int getWeight() {
        return weight;
    }
    public double getTimeCreated() {
        return timeCreated;
    }

    public String toString() {
        return weight + "KG -> " + destination + ", created: " + timeCreated;
    }

}
