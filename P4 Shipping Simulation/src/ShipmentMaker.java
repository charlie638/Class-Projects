//terwi045

import java.util.Random;

public class ShipmentMaker implements Event {

    private Port port;

    public ShipmentMaker(Port port) {
        this.port = port;
    }

    public void run() {
        Random r = new Random();
        int number = 0;
        String destination = "";
        switch (port.getName()) { // to index the queue at the correct port
            case "Minneapolis": number = 0; break;
            case "Saint Paul":  number = 1; break;
            case "Antarctica":  number = 2; break;
            case "Japan":       number = 3; break;
            case "Korea":       number = 4; break;
            case "China":       number = 5; break;
            // didn't include moon because no shipments are made at the moon
            case "Panama":      number = 6; break;
            case "Hawaii":      number = 7; break;
            case "Pirate Town": number = 8; break;
        }

        int destinationNum = r.nextInt(1000);
        while (destinationNum >= number*111 && destinationNum < (number*111)+111) {
            destinationNum = r.nextInt(1000); }
        // so that the destination can't be the same as its origin

        if      (destinationNum <  111)                         {destination = "Minneapolis";}
        else if (destinationNum >= 111 && destinationNum < 222) {destination = "Saint Paul";}
        else if (destinationNum >= 222 && destinationNum < 333) {destination = "Antarctica";}
        else if (destinationNum >= 333 && destinationNum < 444) {destination = "Japan";}
        else if (destinationNum >= 444 && destinationNum < 555) {destination = "Korea";}
        else if (destinationNum >= 555 && destinationNum < 666) {destination = "China";}
        else if (destinationNum >= 666 && destinationNum < 777) {destination = "Panama";}
        else if (destinationNum >= 777 && destinationNum < 888) {destination = "Hawaii";}
        else if (destinationNum >= 888 && destinationNum < 999) {destination = "Pirate Town";}
        else if (destinationNum == 999)                         {destination = "Moon";}
        // equal chance of destination (111/1000) ≈ (1/9), while moon has (1/1000)

        int weight = r.nextInt(1001);
        while (weight < 1)
            weight = r.nextInt(1001);

        Shipment sh = new Shipment(weight, destination);
        // new shipment with weight 1-1000 KG

        int i = 0;
        boolean added = false;
        while (i < Simulation.amount && !added) {
            // checks all the vessels and sees if they are at the port that the shipment is made for and tries to add it
            if (Simulation.v[i].getAt() == null) {
                Simulation.v[i].setAt(port.getName());
                // new vessels with no current location
            }
            if (port.getName().equals(Simulation.v[i].getAt().getName()) && !Simulation.v[i].getSailing()) {
                if (Simulation.v[i].add(sh)) {
                    Simulation.agenda.add(new VesselEvent(Simulation.v[i], Simulation.v[i].getTo()), 0);
                    added = true;
                    }
                }

            i++;
        }
        if (!added) { Simulation.queue[number].add(sh); }
        // new shipment added to the queue at that port if it isn't immediately added to a vessel

        double nextShipment = 1440/port.getSpd(); // minutes in a day / shipments per day = (μ)

        int rand = r.nextInt(20);
        if (rand < 2)                       { nextShipment /= 4; }                      // 0,1           (10%)
        else if (rand >= 2 && rand <= 4)    { nextShipment /= 2; }                      // 2,3,4         (15%)
        else if (rand >= 5 && rand <= 8)    { nextShipment = (4 * nextShipment)/5; }    // 5,6,7,8       (20%)
                                                                                        // 9,10          (10%)
        else if (rand >= 11 && rand <= 14)  { nextShipment = (6 * nextShipment)/5; }    // 11,12,13,14   (20%)
        else if (rand >= 15 && rand <= 17)  { nextShipment = (3 * nextShipment)/2; }    // 15,16,17      (15%)
        else if (rand >= 18 && rand <= 19)  { nextShipment = (7 * nextShipment)/4; }    // 18,19         (10%)

        Simulation.agenda.add(new ShipmentMaker(port), nextShipment);
    }
}
