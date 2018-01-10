package es.gensin.tripslist.tripslist.dummy;

/**
 * Created on 10/01/2018.
 */

public class DummyItem {
    public String dayNumber;
    public String dayText;
    public String tripNumber;
    public int tripsUnseeing;
    public boolean hasAchievement;
    public boolean isPressed = false;

    public DummyItem(String dayNumber, String dayText, String tripNumber, int tripsUnseeing, boolean hasAchievement) {
        this.dayNumber = dayNumber;
        this.dayText = dayText;
        this.tripNumber = tripNumber;
        this.tripsUnseeing = tripsUnseeing;
        this.hasAchievement = hasAchievement;
    }
}
