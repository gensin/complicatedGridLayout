package es.gensin.tripslist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import es.gensin.tripslist.tripslist.dummy.DummyItem;

/**
 * Created on 11/01/18.
 */

public class TripDaysHelper {

    public static void setAchievements(ImageView achievementsIcon, DummyItem item) {
        if(item.hasAchievement){
            achievementsIcon.setVisibility(View.VISIBLE);
        } else {
            achievementsIcon.setVisibility(View.GONE);
        }
    }

    public static void setNotification(TextView notification, DummyItem item) {
        if (item.tripsUnseeing > 0){
            notification.setVisibility(View.VISIBLE);
            notification.setText(String.valueOf(item.tripsUnseeing));
        } else {
            notification.setVisibility(View.GONE);
        }
    }
}
