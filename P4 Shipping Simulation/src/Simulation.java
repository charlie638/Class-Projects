//terwi045

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Simulation {

    public static PQ agenda = new PQ();
    public static Q2[] queue = new Q2[9];
    public static Statistics[] st = new Statistics[0];

    public static int amount;
    public static String type;
    public static Vessel[] v = new Vessel[amount];

    public static double[] stats(int a, String t, double c, int w) {
        amount = a;
        type = t;
        v = new Vessel[amount];

        agenda = new PQ();
        queue = new Q2[9];
        st = new Statistics[0];

        for (int i = 0; i < queue.length; i++) {
            queue[i] = new Q2();
        } // instantiate Q2 array

        Port[] ports = {new Port("Minneapolis"),
                new Port("Saint Paul"),
                new Port("Antarctica"),
                new Port("Japan"),
                new Port("Korea"),
                new Port("China"),
                new Port("Panama"),
                new Port("Hawaii"),
                new Port("Pirate Town")};

        for (int v = 0; v < ports.length; v++) {
            agenda.add(new ShipmentMaker(ports[v]), 0);
        }

        for (int i = 0; i < amount; i++) {
            v[i] = new Vessel(type, c, w, ports[new Random().nextInt(9)]);
        }

        while (agenda.getCurrentTime() < 10000) {
            agenda.remove().run();
        }

        double[] profit = new double[st.length];
        double[] filled = new double[st.length];
        double[] wait = new double[st.length];
        double[] duration = new double[st.length];
        double[] distance = new double[st.length];


        for (int j = 0; j < st.length; j++) {
            profit[j] = st[j].getProfit();
            filled[j] = st[j].getFilled();
            wait[j] = st[j].getWait();
            duration[j] = st[j].getDuration();
            distance[j] = st[j].getDistance();
        }

        Arrays.sort(profit);
        Arrays.sort(filled);
        Arrays.sort(wait);
        Arrays.sort(duration);
        Arrays.sort(distance);
        double pr;
        double fi;
        double wa;
        double du;
        double di;
        int p = profit.length / 2;
        if (profit.length % 2 == 1) {
            pr = Math.round(profit[p]);
        } else {
            pr = Math.round((profit[p - 1] + profit[p]) / 2.0);
        }
        if (filled.length % 2 == 1) {
            fi = Math.round(filled[p]);
        } else {
            fi = Math.round((filled[p - 1] + filled[p]) / 2.0);
        }
        if (wait.length % 2 == 1) {
            wa = Math.round(wait[p]);
        } else {
            wa = Math.round((wait[p - 1] + wait[p]) / 2.0);
        }
        if (duration.length % 2 == 1) {
            du = Math.round(duration[p]);
        } else {
            du = Math.round((duration[p - 1] + duration[p]) / 2.0);
        }
        if (distance.length % 2 == 1) {
            di = Math.round(distance[p]);
        } else {
            di = Math.round((distance[p - 1] + distance[p]) / 2.0);
        }

        System.out.println(amount + " " + type + " W: " + w + ", C: " + c);
        double[] array = {pr, fi, wa, du, di};
        return array;

    }

    public static void csv(String quality, int number, String type, int wStart, int wMax, int wIncrement, double cStart, double cMax, double cIncrement) throws IOException {
        int n = 0;
        switch (quality) {
            case "profit":   n = 0; break;
            case "filled":   n = 1; break;
            case "wait":     n = 2; break;
            case "duration": n = 3; break;
            case "distance": n = 4; break;
        }
        // switch to set n value that sets the return value of the array in stats to return the 'quality'
        double[][] array = new double[((wMax-wStart)/wIncrement) + 1][(int) ((cMax - cStart) / cIncrement) + 1];
        int a = 0;
        int b = 0;
        for (int i = wStart; i < wMax; i += wIncrement) { // iterates through W values
            for (double j = cStart; j < cMax; j += cIncrement) { // iterates through C values
                double p = Simulation.stats(number, type, j, i)[n]; // gets the return value from the stats method
                array[a][b] = p;
                b++;
            }
            a++;
            b = 0;
        }

        BufferedWriter buffered = new BufferedWriter(new FileWriter(type + ".csv"));
        // makes a new comma separated values file named after the vessel
        StringBuilder builder = new StringBuilder();
        for (double element[] : array) {
            for (int k = 0; k < element.length; k++) {
                builder.append(element[k]);
                builder.append(",");
            }
            builder.append("\n");
        }
        buffered.write(builder.toString());
        // writes StringBuilder object to file
        buffered.close();
    }

    public static void main(String[] args) throws IOException {
        csv("distance", 100,"Canoe", 50, 501, 50, .1,.96,.1);
        csv("profit", 10, "Yacht", 2, 60, 2, .1,.95,.05);
        csv("distance", 20, "Galleon", 6000, 15000, 1000, .5,.96,.05);
        csv("profit", 15, "Barge", 4000, 16000, 2000, .3,.91,.1);
        csv("distance", 10, "Freighter", 1000, 5000, 1000, .1,.95,.1);
        csv("profit", 5,  "Airplane", 1000, 10000, 1000, .2,.95,.1);
        csv("profit", 30, "Carrier Pigeon Team", 200, 2001, 200, .1,.96,.05);
        csv("profit", 10, "Rocket", 100, 1001, 100, .1,.96,.1);


    }
}

