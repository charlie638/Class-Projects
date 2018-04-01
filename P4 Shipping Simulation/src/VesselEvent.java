//terwi045

// unloads the vessel at the port
// loads the vessel with new shipments from the port
// creates new vesselEvent when C or W are satisfied

import java.util.Random;

public class VesselEvent implements Event {

    private Port port;
    private Vessel vessel;

    public VesselEvent(Vessel vessel, Port port) {
        this.port = port;
        this.vessel = vessel;
    }

    public void run() {

        vessel.remove();

        int number = 0;
        switch (vessel.getAt().getName()) {
            case "Minneapolis": number = 0; break;
            case "Saint Paul":  number = 1; break;
            case "Antarctica":  number = 2; break;
            case "Japan":       number = 3; break;
            case "Korea":       number = 4; break;
            case "China":       number = 5; break;
            case "Panama":      number = 6; break;
            case "Hawaii":      number = 7; break;
            case "Pirate Town": number = 8; break;
        }

        for (int i = 0; i < Simulation.queue[number].length(); i++) {
            Shipment s = (Shipment) Simulation.queue[number].remove();
            if (!vessel.add(s))
                Simulation.queue[number].add(s);
                // if it cant add the shipment to the vessel, add it back to the queue
        }


        if (vessel.depart()) {
            double x = (vessel.getTo().getLocation()[0] - vessel.getAt().getLocation()[0]);
            double y = (vessel.getTo().getLocation()[1] - vessel.getAt().getLocation()[1]);
            double distance = Math.hypot(x, y);
            double time = distance / (vessel.getSpeed()); // distance in a straight line / speed in KM/minute
            double cost = distance * vessel.getCost();

            double money = 0;
            for (int i = 0; i <vessel.getShipments().length; i++) {
                money += vessel.getShipments()[i].getWeight() * 10; // calculates the money made from each shipment
            }
            if (vessel.getTo().getName().equals("Pirate Town")) // random chance for pirates to steal shipments
                if (new Random().nextInt(10) == 0) {
                    money = 0;
                }

            //System.out.println(vessel.getName() + ": distance: " + distance + ", time: " + time + ", cost: " + cost + ", made: " + money + ", profit: " + (money-cost) + ", from: " + vessel.getAt() + ", to: " + vessel.getTo());
            if (distance > 0) {// otherwise the vessel will just stay at the port until a shipment comes in
                Statistics.add(new Statistics(money - cost, (vessel.getCapacity() - vessel.getAvailableCapacity())/vessel.getCapacity(), 20, time, distance, Simulation.agenda.getCurrentTime()));
            }
            vessel.setSailing(true); // so that it knows not to try to add shipments to it
            Simulation.agenda.add(new Arrival(vessel), time);
        }
        else {Simulation.agenda.add(new VesselEvent(vessel, vessel.getAt()), vessel.getW());}
        // sets the event to depart if it reaches its w value

    }
}
