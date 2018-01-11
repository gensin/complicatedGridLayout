package es.gensin.tripslist.tripsdaylist.dummy;

import java.util.ArrayList;

/**
 * Created on 10/01/2018.
 */

public class TripDay {

    //d
    public String day;
    //dt
    public String dayTitle;
    //sco
    public int score;
    //ssco
    public String smileScore;
    //dis
    public int distance;
    //tt
    public int time;
    //sen
    public int tripsUnseeing;
    //tr
    public ArrayList<Trip> trips;

    public String tripNumber;
    public boolean hasAchievement;
    public boolean isPressed = false;

    public TripDay(String day, String dayTitle, String tripNumber, int tripsUnseeing, boolean hasAchievement) {
        this.day = day;
        this.dayTitle = dayTitle;
        this.tripNumber = tripNumber;
        this.tripsUnseeing = tripsUnseeing;
        this.hasAchievement = hasAchievement;
    }
}


