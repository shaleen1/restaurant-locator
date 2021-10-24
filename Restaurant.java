import java.util.Scanner;
/**
 * Restaurant object.
 * @author tanay
 * @version 1.1
 */
public class Restaurant{
    private String name;
    private String type;
    private String priceRating;
    private String currency;
    private String openTime;
    private String closeTime;
    private double latitude;
    private double longitude;
    private boolean isMatch;
    private double distFromUser;

    public Restaurant(String name, String type, String priceRating, String currency, String openTime, String closeTime) {
        this.name = name;
        this.type = type;
        this.priceRating = priceRating;
        this.currency = currency;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.isMatch = false;

    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setLatitudeLongitude(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double[] getLatitudeLongitude() {
        double[] latitudeLongitude = new double[2];
        latitudeLongitude[0] = latitude;
        latitudeLongitude[1] = longitude;
        return latitudeLongitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public boolean getIsMatch() {
        return isMatch;
    }

    public void setIsMatch() {
        isMatch = true;
    }


    public String getPriceRating() {
        return priceRating;
    }

    public String getCurrency() {
        return currency;
    }

    public String getOpenTime() {
        return openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }
}
