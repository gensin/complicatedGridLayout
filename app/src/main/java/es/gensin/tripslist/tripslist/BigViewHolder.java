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
    private ImageView achievementsIcon;
    @BindView(R.id.close_detail)
    private ImageView closeDetail;
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
    @BindView(R.id.trips_time)
    private TextView tripsTime;
    @BindView(R.id.trips_distance)
    private TextView tripsDistance;
    @BindView(R.id.trips_score)
    private TextView tripsScore;

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
