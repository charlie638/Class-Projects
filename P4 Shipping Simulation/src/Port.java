//terwi045

public class Port {

    private int[] location = new int[2];
    private String name;
    private int spd;    // shipments per day

    public Port(String name) {
        this.name = name;
        switch (name) {
            case "Minneapolis": this.location = new int[] {0, 0};
                                this.spd = 50;                          break;
            case "Saint Paul":  this.location = new int[] {0, 10};
                                this.spd = 50;                          break;
            case "Antarctica":  this.location = new int[] {0, -6000};
                                this.spd = 10;                          break;
            case "Japan":       this.location = new int[] {4000, 4000};
                                this.spd = 100;                         break;
            case "Korea":       this.location = new int[] {6000, 5000};
                                this.spd = 50;                          break;
            case "China":       this.location = new int[] {5000, 6000};
                                this.spd = 1000;                        break;
            case "Moon":        this.location = new int[] {0, 1000000};
                                this.spd = 0;                           break;
            case "Panama":      this.location = new int[] {1000, 3000};
                                this.spd = 50;                          break;
            case "Hawaii":      this.location = new int[] {2000, 2000};
                                this.spd = 50;                          break;
            case "Pirate Town": this.location = new int[] {3000, 3000};
                                this.spd = 100;                         break;
        }
    }

    public String getName() {
        return name;
    }
    public int[] getLocation() {
        return location;
    }
    public int getSpd() {
        return spd;
    }

    public boolean equals(Port other) {
        return this.location[0] == other.getLocation()[0] && this.location[1] == other.getLocation()[1] && this.name.equals(other.getName());
    }

    public String toString() {
        return name;
    }
}
