package es.gensin.tripslist.tripsdaylist.dayslist;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.gensin.tripslist.R;
import es.gensin.tripslist.tripsdaylist.TripDaysHelper;
import es.gensin.tripslist.tripsdaylist.dummy.TripDay;

/**
 * Created on 11/01/18.
 */

class NormalViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.achievements_icon)
    ImageView achievementsIcon;
    @BindView(R.id.day_text)
    TextView dayText;
    @BindView(R.id.day_number)
    TextView dayNumber;
    @BindView(R.id.trips_number)
    TextView tripsNumber;
    @BindView(R.id.notification)
    TextView notification;
    @BindView(R.id.item_parent)
    ConstraintLayout tripDayItem;

    NormalViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    void onBindItem(Context context, TripDay item, int position, Integer selectedPosition, View.OnClickListener onCloseListener) {
        dayNumber.setText(item.day);
        dayText.setText(item.dayTitle);
        tripsNumber.setText(String.valueOf(item.trips.size()));

        tripDayItem.setOnClickListener(onCloseListener);

        if (selectedPosition != null && item.isPressed && position == selectedPosition){
            tripDayItem.setBackground(ContextCompat.getDrawable(context, R.drawable.button_background_pressed));
        } else {
            item.isPressed = false;
            tripDayItem.setBackground(ContextCompat.getDrawable(context, R.drawable.button_background));
        }

        TripDaysHelper.setNotification(notification, item);
        TripDaysHelper.setAchievements(achievementsIcon, item);
    }
}
