package es.gensin.tripslist.tripsdaylist.dayslist;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.gensin.tripslist.R;
import es.gensin.tripslist.tripsdaylist.TripDaysHelper;
import es.gensin.tripslist.tripsdaylist.triplist.TripAdapter;
import es.gensin.tripslist.tripsdaylist.dummy.Trip;
import es.gensin.tripslist.tripsdaylist.dummy.TripDay;
import rx.functions.Action1;

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
    @BindView(R.id.trip_list)
    RecyclerView tripList;

    private final Context context;

    BigViewHolder(View view) {
        super(view);
        context = view.getContext();
        ButterKnife.bind(this, view);
    }

    void onBindItem(TripDay item, View.OnClickListener onCloseClickListener, Action1<Trip> onTripClickListener) {
        dayNumber.setText(item.day);
        dayText.setText(item.dayTitle);
        tripsNumber.setText(String.valueOf(item.trips.size()));
        closeDetail.setOnClickListener(onCloseClickListener);

        TripDaysHelper.setNotification(notification, item);
        TripDaysHelper.setAchievements(achievementsIcon, item);

        tripList.setAdapter(new TripAdapter(context, item.trips, onTripClickListener));
    }
}
