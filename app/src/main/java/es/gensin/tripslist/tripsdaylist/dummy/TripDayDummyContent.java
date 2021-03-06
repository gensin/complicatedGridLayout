package es.gensin.tripslist.tripsdaylist.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TripDayDummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<TripDay> ITEMS = new ArrayList<TripDay>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, TripDay> ITEM_MAP = new HashMap<String, TripDay>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(TripDay item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.day, item);
    }

    private static TripDay createDummyItem(int position) {
        String dayNumber = String.valueOf(position);

        return new TripDay(dayNumber, makeDay(position), makeScore(), "", makeDistance(),
                makeTime(), makeUnseeing(), makeTrips(), makeAchievements());
    }

    private static int makeTime() {
        Random rand = new Random();
        int  n = rand.nextInt(78) + 5;
        return n;
    }

    private static int makeDistance() {
        Random rand = new Random();
        int  n = rand.nextInt(200) + 10;
        return n;
    }

    private static int makeScore() {
        Random rand = new Random();
        int  n = rand.nextInt(101);
        return n;
    }

    private static boolean makeAchievements() {
        Random rand = new Random();
        int  n = rand.nextInt(2);
        return n == 0;
    }

    private static int makeUnseeing() {
        Random rand = new Random();
        int  n = rand.nextInt(4);
        return n;
    }

    private static String makeDay(int position) {
        String dayText = "Monday";
        switch (position % 7){
            case 0:
                dayText = "Monday";
                break;
            case 1:
                dayText = "Tuesday";
                break;
            case 2:
                dayText = "Wednesday";
                break;
            case 3:
                dayText = "Thursday";
                break;
            case 4:
                dayText = "Friday";
                break;
            case 5:
                dayText = "Saturday";
                break;
            case 6:
                dayText = "Sunday";
                break;
        }
        return dayText;
    }

    private static ArrayList<Trip> makeTrips() {
        Random rand = new Random();
        int n = rand.nextInt(50);
        return new TripDummyContent().getItems();
    }
}
