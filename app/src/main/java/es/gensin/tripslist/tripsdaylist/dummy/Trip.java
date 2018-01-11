package es.gensin.tripslist.tripsdaylist.dummy;

import java.util.ArrayList;

/**
 * Created on 11/01/2018.
 */

public class Trip {

    //tid
    public Integer tripId;
    //otid
    public ArrayList<Integer> originalTripId;
    //tt
    public Integer tripType;
    //fr
    public String from;
    //to
    public String to;
    //st
    public String startTrip;
    //et
    public String stropTrip;
    //dis
    public Integer distance;
    //sco
    public Integer score;
    //ssco
    public String smileScore;
    //tiid
    public String tripInsight;
    //se
    public Boolean isSeen;

    public Trip(Integer tripId, ArrayList<Integer> originalTripId, Integer tripType, String from,
                String to, String startTrip, String stropTrip, Integer distance, Integer score,
                String smileScore, String tripInsight, Boolean isSeen) {
        this.tripId = tripId;
        this.originalTripId = originalTripId;
        this.tripType = tripType;
        this.from = from;
        this.to = to;
        this.startTrip = startTrip;
        this.stropTrip = stropTrip;
        this.distance = distance;
        this.score = score;
        this.smileScore = smileScore;
        this.tripInsight = tripInsight;
        this.isSeen = isSeen;
    }
}
