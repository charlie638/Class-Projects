// terwi045

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WeatherRecord {

    private String city, country, longitude, latitude, temperature, minimum, maximum, description;

    public WeatherRecord(String city, String country, String longitude, String latitude, String temperature, String minimum, String maximum, String description) {
        this.city =         city;
        this.country =      country;
        this.longitude =    longitude;
        this.latitude =     latitude;
        this.temperature =  temperature;
        this.minimum =      minimum;
        this.maximum =      maximum;
        this.description =  description;
    }

    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public String getLongitude() {
        return longitude;
    }
    public String getLatitude() {
        return latitude;
    }
    public String getTemperature() {
        return temperature;
    }
    public String getMinimum() {
        return minimum;
    }
    public String getMaximum() {
        return maximum;
    }
    public String getDescription() {
        return description;
    }

    public static HashMap makeHashMap() throws IOException{
        Scanner in = new Scanner(new FileReader("weather_10000.txt"));
        HashMap hm = new HashMap(10000);
        while (in.hasNextLine()) {
            String[] str = in.nextLine().split(";");
            WeatherRecord wr = new WeatherRecord(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7]);
            hm.put(wr.getCity() + ", " + wr.getCountry(), wr.getLongitude() + ", " + wr.getLatitude() + ", " + wr.getTemperature() + ", " + wr.getMinimum() + ", " + wr.getMaximum() + ", " + wr.getDescription());
        }
        return hm;
    }

    public static void main(String[] args) throws IOException{
        System.out.println(makeHashMap().toString());
    }
}
