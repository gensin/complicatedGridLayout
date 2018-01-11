package es.gensin.tripslist.tripslist;

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
import es.gensin.tripslist.TripDaysHelper;
import es.gensin.tripslist.tripslist.dummy.DummyItem;

/**
 * Created on 11/01/18.
 */

class NormalViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.achievements_icon)
    private ImageView achievementsIcon;
    @BindView(R.id.day_text)
    private TextView dayText;
    @BindView(R.id.day_number)
    private TextView dayNumber;
    @BindView(R.id.trips_number)
    private TextView tripsNumber;
    @BindView(R.id.notification)
    private TextView notification;
    @BindView(R.id.item_parent)
    private ConstraintLayout tripDayItem;

    NormalViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    void onBindItem(Context context, DummyItem item, int position, Integer selectedPosition, View.OnClickListener onCloseListener) {
        dayNumber.setText(item.dayNumber);
        dayText.setText(item.dayText);
        tripsNumber.setText(item.tripNumber);

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
