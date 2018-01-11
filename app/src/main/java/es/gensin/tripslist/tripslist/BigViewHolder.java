package es.gensin.tripslist.tripslist;

import android.support.constraint.ConstraintLayout;
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

class BigViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.achievements_icon)
    ImageView achievementsIcon;
    @BindView(R.id.close_detail)
    ImageView closeDetail;
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
    @BindView(R.id.trips_time)
    TextView tripsTime;
    @BindView(R.id.trips_distance)
    TextView tripsDistance;
    @BindView(R.id.trips_score)
    TextView tripsScore;

    BigViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    void onBindItem(DummyItem item, View.OnClickListener onCloseClickListener) {
        dayNumber.setText(item.dayNumber);
        dayText.setText(item.dayText);
        tripsNumber.setText(item.tripNumber);
        closeDetail.setOnClickListener(onCloseClickListener);

        TripDaysHelper.setNotification(notification, item);
        TripDaysHelper.setAchievements(achievementsIcon, item);
    }
}
