//terwi045

public class Arrival implements Event{

    // handles when a shipment arrives at a port

    private Vessel v;

    public Arrival(Vessel v) {
        this.v = v;
    }

    public void run(){
        v.setAt(v.getTo().getName());
        v.setSailing(false);
        v.setArrived(Simulation.agenda.getCurrentTime());
        Simulation.agenda.add(new VesselEvent(v, v.getAt()), 0);
    }

}
