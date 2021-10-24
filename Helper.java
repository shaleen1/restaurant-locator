import java.io.*;
import java.util.Scanner;

public class Helper {
    public static double[] extractLatLngFromCsvUser() {
        double[] latLng = new double[2];
        do {
            try{
            Scanner sc = new Scanner(new File("userLocation.csv"));  
            sc.useDelimiter(",");

            latLng[0] = Double.parseDouble(sc.next());
            latLng[1] = Double.parseDouble(sc.next());
            return latLng;
        }
            catch (FileNotFoundException ex){
                System.out.println(ex);
                latLng[0] = 0.0;
                latLng[1] = 0.0;
                return latLng;
            }
        } while (latLng[0] == 0.0 && latLng[1] == 0.0);
        
    }

    public static double[] extractLatLngFromCsvRestaurant() {
        double[] latLng = new double[2];
        do {
            try{
            Scanner sc = new Scanner(new File("restaurantLocation.csv"));  
            sc.useDelimiter(",");

            latLng[0] = Double.parseDouble(sc.next());
            latLng[1] = Double.parseDouble(sc.next());

            return latLng;
            }
            catch (FileNotFoundException ex)  
            {
                System.out.println("Error, file not found.");
                latLng[0] = 0.0;
                latLng[1] = 0.0;
                return latLng;
            }
        } while (latLng[0] == 0.0 && latLng[1] == 0.0);
        
        
    }

    public static void runPyUser(String inputLocation) {
        try{
            Process p = Runtime.getRuntime().exec(String.format("python googleMaps.py \"%s\" user", inputLocation));
        }
        catch (IOException e) {
            System.out.println(",,,");
        }
    }

    public static void runPyRestaurant(String inputLocation) {
        try{
            Process p = Runtime.getRuntime().exec(String.format("python googleMaps.py \"%s\" restaurant", inputLocation));
        }
        catch (IOException e) {
            System.out.println(",,,");
        }
        
    }

    public static double computeDistance(double lat1, double lng1, double lat2, double lng2){
        double lat1Rad = lat1/180*Math.PI;
        double lat2Rad = lat2/180*Math.PI;
        double lng1Rad = lng1/180*Math.PI;
        double lng2Rad = lng2/180*Math.PI;
        return 3963.0*Math.acos(Math.sin(lat1Rad)*Math.sin(lat2Rad) + Math.cos(lat1Rad)*Math.cos(lat2Rad)*Math.cos(lng2Rad-
            lng1Rad));
    }
}
