package es.gensin.tripslist.tripsdaylist.dummy;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import es.gensin.tripslist.DateUtils;

public class TripDummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public final List<Trip> ITEMS = new ArrayList<>();

    public TripDummyContent() {
        // Add some sample items.
        for (int i = 1; i <= getItemCount(); i++) {
            addItem(createDummyItem(i));
        }
    }

    private  int getItemCount() {
        Random rand = new Random();
        return rand.nextInt(10);
    }

    private void addItem(Trip item) {
        ITEMS.add(item);
    }

    private  Trip createDummyItem(int position) {
        String dayNumber = String.valueOf(position);

        return new Trip(getTripId(), null, getType(), getFrom(), getTo(), getDate(),
                getDate(), makeDistance(), makeScore(), "", "", true);
    }

    private  String getDate() {
        // Get a new random instance, seeded from the clock
        Random rnd = new Random();

        // Get an Epoch value roughly between 1940 and 2010
        // -946771200000L = January 1, 1940
        // Add up to 70 years to it (using modulus on the next long)
        long ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));

// Construct a date
        Date dt = new Date(ms);
        return DateUtils.getStringFromDate(dt);
    }

    private  String getTo() {
        return null;
    }

    private  String getFrom() {
        return null;
    }

    private  Integer getTripId() {
        Random rand = new Random();
        int  n = rand.nextInt(5);
        if (n < 2){
            return null;
        }
        return n;
    }

    private  int getType() {
        Random rand = new Random();
        int  n = rand.nextInt(4) + 1;
        if (n == 1) {
            n = 2;
        }
        if (n == 3) {
            n = 4;
        }
        return n;
    }

    private  int makeDistance() {
        Random rand = new Random();
        int  n = rand.nextInt(200) + 10;
        return n;
    }

    private  int makeScore() {
        Random rand = new Random();
        int  n = rand.nextInt(101);
        return n;
    }

    public ArrayList<Trip> getItems() {
        return (ArrayList<Trip>) ITEMS;
    }
}
