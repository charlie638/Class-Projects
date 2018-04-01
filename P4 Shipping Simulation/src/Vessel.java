//terwi045

public class Vessel {

    private String name;        // Vessel Type
    private double capacity;    // KGs
    private double speed;       // KM/H
    private int cost;           // per KM

    private double C;           // Capacity percent needed for departure
    private int W;              // Days needed for Departure
    private double arrived;     // time the vessel arrived
    private Port at;            // where the vessel is currently
    private Port to;            // where the vessel is going to
    private boolean sailing;    // at port or sailing

    private Shipment[] shipments = new Shipment[0]; // array of shipments on vessel

    public Vessel(String name, double C, int W, Port at) {
        this.name = name;
        this.C = C;
        this.W = W;
        this.at = at;
        switch (name) {
            case "Canoe":
                this.capacity = 1000;
                this.speed = 10;
                this.cost = 1;
                break;
            case "Yacht":
                this.capacity = 2000;
                this.speed = 60;
                this.cost = 2000;
                break;
            case "Galleon":
                this.capacity = 15000;
                this.speed = 15;
                this.cost = 100;
                break;
            case "Barge":
                this.capacity = 1000000;
                this.speed = 10;
                this.cost = 1000;
                break;
            case "Freighter":
                this.capacity = 2000000;
                this.speed = 5;
                this.cost = 1000;
                break;
            case "Airplane":
                this.capacity = 50000;
                this.speed = 850;
                this.cost = 10000;
                break;
            case "Carrier Pigeon Team":
                this.capacity = 1000;
                this.speed = 10;
                this.cost = 0;
                break;
            case "Rocket":
                this.capacity = 10000;
                this.speed = 2000;
                this.cost = 100000;
                break;
        }
    }

    // getters and setters
    public String getName() {
        return name;
    }
    public double getCapacity() {
        return capacity;
    }
    public double getSpeed() {
        return speed;
    }
    public int getCost() {
        return cost;
    }
    public double getC() {
        return C;
    }
    public int getW() {
        return W;
    }
    public double getArrived() {
        return arrived;
    }
    public Port getAt() {
        return at;
    }
    public Port getTo() {
        return to;
    }
    public boolean getSailing() {
        return sailing;
    }
    public Shipment[] getShipments() {
        return shipments;
    }

    public void setAt(String s) {
        at = new Port(s);
    }
    public void setTo(String s) {
        to = new Port(s);
    }
    public void setSailing(boolean s) {
        sailing = s;
    }
    public void setArrived(double a) {
        arrived = a;
    }

    public void remove() {

        if (shipments.length > 0) {

            if (shipments[0].getDestination().equals(at.getName())) {

                while (shipments.length > 0) {
                    if (to == null) {
                        setTo(oldestShipment(at.getName()).getDestination());
                    }
                    if (shipments[0].getDestination().equals(at.getName())) {
                        // if the shipment is at its port

                        for (int j = 0; j < shipments.length - 1; j++) {
                            shipments[j] = shipments[j + 1]; // moves shipment down one
                        }
                        Shipment[] temp = new Shipment[shipments.length - 1];
                        for (int k = 0; k < temp.length; k++) { // makes new array without removed shipment
                            temp[k] = shipments[k];
                        }
                        shipments = temp;
                    }
                }
            }
        }
    }


    public boolean add(Shipment s) {
        if (s == null)
            return false;
        if (to != null && at != null) {
            if (to.equals(at)) {
                // if to == at then it has arrived at the port
                Shipment goingTo = oldestShipment(at.getName());
                if (goingTo != null) {
                    if (s.getTimeCreated() < goingTo.getTimeCreated())
                        setTo(s.getDestination());
                    else setTo(goingTo.getDestination());
                    // sets the next to destination from the oldest shipment at current port
                }
            }
        }
        if (to == null || at == null) { // if the vessel is new
            setTo(s.getDestination());
        }
        if (s.getDestination().equals(to.getName())) {
            // checks if shipment destination is the same as vessel destination

            if (getAvailableCapacity() - s.getWeight() > 0) {
                // checks if there is room on the vessel
                Shipment[] temp = new Shipment[shipments.length + 1];
                for (int i = 0; i < shipments.length; i++) {
                    temp[i] = shipments[i];
                }
                temp[temp.length - 1] = s;
                shipments = temp;
                // creates a new array of shipments with one more shipment (s) at the end
                return true;
            }
        }
        return false;
    }

    public double getAvailableCapacity() {
        double onVessel = capacity;
        for (int i = 0; i < shipments.length; i++) { // totals up all the shipments on the vessel
            onVessel -= shipments[i].getWeight();
        }
        return onVessel;
    }

    public boolean depart() { // whether to depart or not
        return (((capacity - getAvailableCapacity())/capacity >= C) || Simulation.agenda.getCurrentTime() - arrived >= W);
    }

    public Shipment oldestShipment(String port) {
        int number = 0;
        switch (port) {
            case "Minneapolis": number = 0; break;
            case "Saint Paul":  number = 1; break;
            case "Antarctica":  number = 2; break;
            case "Japan":       number = 3; break;
            case "Korea":       number = 4; break;
            case "China":       number = 5; break;
            // no shipments at moon
            case "Panama":      number = 6; break;
            case "Hawaii":      number = 7; break;
            case "Pirate Town": number = 8; break;
        } // turns the port name into a number so it can be referenced in the queues
        
        Shipment oldest = null;

        for (int i = 0; i < Simulation.queue[number].length(); i++) {
            Shipment s = (Shipment) Simulation.queue[number].remove();
            if (oldest == null) {
                oldest = s;
            }
            else if (s.getTimeCreated() < oldest.getTimeCreated()) {
                oldest = s;
            }
            Simulation.queue[number].add(s); // add shipment back to queue regardless
        }
        if (oldest != null)
            return oldest;
        else return null;
    }

    public String toString(boolean fullContents){
        // return Shipment contents of vessel
        // just for testing purposes
        if (fullContents) {
            String str = "";
            for (int i = 0; i < shipments.length; i++) {
                if (i == shipments.length-1)
                    str += shipments[i].toString();
                else str += shipments[i].toString() + "\n";
            }
            return str;
        }
        else return name;
    }
    public String toString() {  // return type of vessel
        return name;
    }
}