import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Runner class.
 * @author CNS
 * @version 1.2
 */
public class Runner {
    private static String localTime() {
        LocalTime time = LocalTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm");
        String timeVal = time.format(format);
        return timeVal;
    }

    private static String compareTime(Restaurant r) {
        LocalTime time1 = LocalTime.parse(localTime());
        LocalTime time2 = LocalTime.parse(r.getOpenTime());
        LocalTime time3 = LocalTime.parse(r.getCloseTime());

        if ((time1.compareTo(time2)) >= 0) {
            if ((time1.compareTo(time3)) <= 0) {
                return "open";
            }
            else {
                return "closed";
            }
        } else {
            return "closed";
        }
    }

    private static String getCurrencyType() {
        Scanner currencyObj = new Scanner(System.in);
        System.out.println("Do you want to pay in dining, buzz, or other? (dining/buzz/other)");
        String currencyType = currencyObj.nextLine();
        return currencyType;
    }

    private static String getPriceRange() {
        Scanner priceObj = new Scanner(System.in);
        System.out.println("What price range would you like your meal? ($/$$/$$$) ");
        String priceRange = priceObj.nextLine();
        return priceRange;
    }

    private static String getCuisineType() {
        Scanner cuisineObj = new Scanner(System.in);
        System.out.println("What cuisine type would you like?"); //Add cuisine types
        String cuisineType = cuisineObj.nextLine();
        return cuisineType;
    }

    public static void main(String[] args) {
        // Restaurant(String type, String priceRating, String currency, String openTime, String closeTime)
        // Input open and close times in hh:mm format in 24-hour time.
        // Use setLatitudeLongitude(double lat, double long) to set those

        String[] currencyTypes = {"dining", "buzz" , "other"};
        String currencyType = "";
        boolean iterator = true;
        boolean b = false;
        while (iterator) {
            currencyType = getCurrencyType();
            for (String c : currencyTypes) {
                if (currencyType.equals(c)) {
                    iterator = false;
                    b = false;
                    break;
                } else {
                    b = true;
                }
            }
            if (b) {
                System.out.println("Please input a currency type as specified by the options.");
            }
        }

        String[] priceRanges = {"$","$$","$$$"};
        iterator = true;
        b = false;
        String priceRange = "";
        while (iterator) {
            priceRange = getPriceRange();
            for (String p : priceRanges) {
                if (priceRange.equals(p)) {
                    iterator = false;
                    b = false;
                    break;
                } else {
                    b = true;
                }
            }
            if (b) {
                System.out.println("Please input a price range as specified by the options.");
            }
        }

        String[] cuisines = {"American", "Mexican", "Italian", "Mediterranean", "Asian", 
            "Indian", "Fast Food", "Caribbean"};

        String cuisineType = "";
        iterator = true;
        b = false;
        while (iterator) {
            cuisineType = getCuisineType();
            for (String c : cuisines) {
                if (cuisineType.equals(c)) {
                    iterator = false;
                    b = false;
                    break;
                } else {
                    b = true;
                }
            }
            if (b) {
                System.out.println("Please input a cuisine type as specified by the options. (American, Mexican, Italian, Mediterranean, Asian, Indian, Fast Food, Caribbean");
            }
        }

        Scanner input = new Scanner(System.in);
        System.out.println("Where are you close to on campus?");
        String userInputLocation = input.nextLine();


        Helper.runPyUser(userInputLocation);

        double[] userLatLng = Helper.extractLatLngFromCsvUser();
        double userLat = userLatLng[0];
        double userLng = userLatLng[1];

        boolean terminate = false;
        while (terminate) { 
        System.out.println("Here are your preferences: ");
        System.out.println(currencyType);
        System.out.println(priceRange);
        System.out.println(cuisineType);
        Scanner userInput = new Scanner(System.in);
        System.out.println("Would you like to change any of your preferences? (Y/N)");
            if (userInput.nextLine().toUpperCase().equals("Y")) {
                System.out.print("Which one would you like to change? (currency, price range, cusine type)");
                String selection = userInput.nextLine();
                switch(selection) {
                    case("currency"): {
                        while (true) {
                        String currencyType1 = getCurrencyType();
                            for (String c : currencyTypes) {
                                if (currencyType1.equals(c)) {
                                    break;
                                } else {
                                    System.out.println("Please input a currency type as specified by the options.");
                                }
                            }
                        }                       
                         
                    }
                    case("price range"): {
                         while (true) {
                         String priceRange1 = getPriceRange();
                            for (String p : priceRanges) {
                                if (priceRange1.equals(p)) {
                                    break;
                                } else {
                                    System.out.println("Please input a price range as specified by the options.");
                                }
                            }
                        }
                    }
                    case ("cuisine type"): {
                        while (true) {
                        String cuisineType1 = getCuisineType();
                            for (String c : cuisines) {
                                if (cuisineType1.equals(c)) {
                                    break;
                                } else {
                                    System.out.println("Please input a cuisine type as specified by the options.");
                                }
                            }
                        }   

                    }
                }
            } else {
                terminate = true;
            } 
        }

        Restaurant[] restaurants = Restaurants.restaurants;
        for (Restaurant r: restaurants) {
            Helper.runPyRestaurant(r.getName());
            double[] latLng = Helper.extractLatLngFromCsvRestaurant();
            r.setLatitudeLongitude(latLng[0], latLng[1]);
        }

        ArrayList<Restaurant> matches = new ArrayList<Restaurant>();
        for (int i=0; i<restaurants.length; i++) {
            if (restaurants[i].getType().equals(cuisineType) && restaurants[i].getPriceRating().equals(priceRange) && 
                restaurants[i].getCurrency().equals(currencyType) && compareTime(restaurants[i]).equals("open")) {
                restaurants[i].setIsMatch();
                matches.add(restaurants[i]);
            }
        }

        HashMap<Double, String> distToName = new HashMap<Double, String>();
        for (Restaurant m: matches){
            distToName.put((Double)Helper.computeDistance(m.getLatitude(), m.getLongitude(), userLat, userLng), m.getName());
        }

        Set<Double> dists = distToName.keySet();
        Double[] distsArr = dists.toArray(new Double[dists.size()]);
        Double[] distsSorted = distsArr.clone(); 
        Arrays.sort(distsSorted);

        String[] namesSorted = new String[distsSorted.length];
        for (int i=0; i<namesSorted.length; i++) {
            namesSorted[i] = distToName.get(distsSorted[i]);
            System.out.print(namesSorted[i]);
        }

    }

}

