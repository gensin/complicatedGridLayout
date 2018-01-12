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

    public boolean hasAchievement;
    public boolean isPressed = false;

    public TripDay(String day, String dayTitle, int score, String smileScore, int distance,
                   int time, int tripsUnseeing, ArrayList<Trip> trips, boolean hasAchievement) {
        this.day = day;
        this.dayTitle = dayTitle;
        this.score = score;
        this.smileScore = smileScore;
        this.distance = distance;
        this.time = time;
        this.tripsUnseeing = tripsUnseeing;
        this.trips = trips;
        this.hasAchievement = hasAchievement;
    }
}


